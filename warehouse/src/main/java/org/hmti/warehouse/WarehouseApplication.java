package org.hmti.warehouse;

import java.util.Date;
import lombok.extern.java.Log;
import org.hmti.warehouse.domain.Audith;
import org.hmti.warehouse.domain.user.key.PrivilegePK;
import org.hmti.warehouse.domain.user.key.RolePK;
import org.hmti.warehouse.domain.user.key.RolePrivilegePK;
import org.hmti.warehouse.domain.user.key.UserPK;
import org.hmti.warehouse.domain.user.model.Privilege;
import org.hmti.warehouse.domain.user.model.Role;
import org.hmti.warehouse.domain.user.model.RolePrivilege;
import org.hmti.warehouse.domain.user.model.User;
import org.hmti.warehouse.repository.user.PrivilegeRepository;
import org.hmti.warehouse.repository.user.RolePrivilegeRepository;
import org.hmti.warehouse.repository.user.RoleRepository;
import org.hmti.warehouse.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Log
public class WarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "DELETE", "PUT");
            }
        };
    }

    @Bean
    CommandLineRunner run(@Autowired UserRepository userRepository,
            @Autowired RoleRepository roleRepository,
            @Autowired PrivilegeRepository privilegeRepository,
            @Autowired RolePrivilegeRepository rolePrivilegeRepository) {

        return (String[] args) -> {
            Role admin = new Role(new RolePK("admin"));
            Role cashier = new Role(new RolePK("cashier"));
            Role staff = new Role(new RolePK("staff"));
            roleRepository.save(admin);
            roleRepository.save(cashier);
            roleRepository.save(staff);
            User admin1 = new User(new UserPK("admin1"), passwordEncoder().encode("admin"), admin);
            User admin2 = new User(new UserPK("admin2"), passwordEncoder().encode("admin"), admin);
            User cashier1 = new User(new UserPK("cashier1"), passwordEncoder().encode("cashier"), cashier);
            User staff1 = new User(new UserPK("staff1"), passwordEncoder().encode("staff"), staff);
            userRepository.save(admin1);
            userRepository.save(admin2);
            userRepository.save(cashier1);
            userRepository.save(staff1);
            Privilege cashierPrivilege = new Privilege(new PrivilegePK("cashier"));
            cashierPrivilege.setDescription("cashier privilege");
            cashierPrivilege.setAudith(new Audith(admin1, new Date()));
            Privilege staffPrivilege = new Privilege(new PrivilegePK("staff"));
            staffPrivilege.setDescription("staff privilege");
            staffPrivilege.setAudith(new Audith(admin1, new Date()));
            Privilege modifyPrivilege = new Privilege(new PrivilegePK("modify"));
            modifyPrivilege.setDescription("modify privilege");
            modifyPrivilege.setAudith(new Audith(admin1, new Date()));
            privilegeRepository.save(cashierPrivilege);
            privilegeRepository.save(staffPrivilege);
            privilegeRepository.save(modifyPrivilege);
            RolePrivilege adminCashier = new RolePrivilege(new RolePrivilegePK(admin, cashierPrivilege), new Audith(admin1, new Date()));
            RolePrivilege adminStaff = new RolePrivilege(new RolePrivilegePK(admin, staffPrivilege), new Audith(admin1, new Date()));
            RolePrivilege adminModify = new RolePrivilege(new RolePrivilegePK(admin, modifyPrivilege), new Audith(admin1, new Date()));
            RolePrivilege cashierRP = new RolePrivilege(new RolePrivilegePK(cashier, cashierPrivilege), new Audith(admin1, new Date()));
            RolePrivilege staffRp = new RolePrivilege(new RolePrivilegePK(staff, staffPrivilege), new Audith(admin1, new Date()));
            rolePrivilegeRepository.save(adminCashier);
            rolePrivilegeRepository.save(adminStaff);
            rolePrivilegeRepository.save(adminModify);
            rolePrivilegeRepository.save(cashierRP);
            rolePrivilegeRepository.save(staffRp);
        };
    }
}
