/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.dto.user.model.projection;

import org.hmti.warehouse.dto.user.key.projection.UserPKLite;

/**
 *
 * @author asdin
 */
public interface UserMini {

    UserPKLite getUserPK();

    String getPassword();

    RoleMini getRole();
}
