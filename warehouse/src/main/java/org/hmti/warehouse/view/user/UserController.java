/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.view.user;

import org.hmti.warehouse.dto.user.model.LoginResponse;
import org.hmti.warehouse.dto.user.model.UserLogin;
import org.hmti.warehouse.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asdin
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public LoginResponse authenticate(@RequestBody UserLogin userLogin) throws Exception {
        return userService.authenticate(userLogin);
    }
}
