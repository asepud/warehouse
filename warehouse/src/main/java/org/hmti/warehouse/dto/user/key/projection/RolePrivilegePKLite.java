/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.dto.user.key.projection;

import org.hmti.warehouse.dto.user.model.projection.PrivilegeLite;
import org.hmti.warehouse.dto.user.model.projection.RoleLite;

/**
 *
 * @author asdin
 */
public interface RolePrivilegePKLite {
     RoleLite getRole();
    
     PrivilegeLite getPrivilege();
}
