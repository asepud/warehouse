/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.dto.user.model.projection;

import java.util.List;
import org.hmti.warehouse.dto.user.key.projection.RolePKLite;

/**
 *
 * @author asdin
 */
public interface RoleMini {
    RolePKLite getRolePK();

    List<RolePrivilegeLite> getRolePrivileges();

    String getDescription();
}
