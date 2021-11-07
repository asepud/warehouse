/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.java.Log;
import org.hmti.warehouse.config.util.JWTUtil;
import org.hmti.warehouse.domain.user.key.UserPK;
import org.hmti.warehouse.domain.user.model.RolePrivilege;
import org.hmti.warehouse.domain.user.model.User;
import org.hmti.warehouse.dto.user.model.LoginResponse;
import org.hmti.warehouse.dto.user.model.UserLogin;
import org.hmti.warehouse.repository.user.RolePrivilegeRepository;
import org.hmti.warehouse.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author asdin
 */
@Service
@Log
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolePrivilegeRepository rolePrivilegeRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jWTUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findById(new UserPK(username));
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("user not found");
        }
        User user = optional.get();
        List<SimpleGrantedAuthority> simpleGrantedAuthoritys = new ArrayList<>();
        for (RolePrivilege rolePrivilege : rolePrivilegeRepository.findByRolePrivilegePKRoleRolePKName(user.getRole().getRolePK().getName())) {
            simpleGrantedAuthoritys.add(new SimpleGrantedAuthority(rolePrivilege.getRolePrivilegePK().getPrivilege().getPrivilegePK().getName()));
        }
        simpleGrantedAuthoritys.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRolePK().getName()));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserPK().getUsername())
                .password(user.getPassword())
                .authorities(simpleGrantedAuthoritys)
                .build();
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    public LoginResponse authenticate(UserLogin userLogin) throws Exception {
        authenticate(userLogin.getUsername(), userLogin.getPassword());
        UserDetails userDetails = loadUserByUsername(userLogin.getUsername());
        return new LoginResponse(userDetails, jWTUtil.generateToken(userDetails));
    }
}
