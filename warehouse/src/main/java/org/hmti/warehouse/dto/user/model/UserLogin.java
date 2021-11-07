/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.dto.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author asdin
 */
@Getter
@AllArgsConstructor
public class UserLogin {

    private final String username;
    private final String password;

}
