/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.dto.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author asdin
 */
@Getter
@AllArgsConstructor
public class LoginResponse {

    private UserDetails userdetail;
    private String jWTToken;

}
