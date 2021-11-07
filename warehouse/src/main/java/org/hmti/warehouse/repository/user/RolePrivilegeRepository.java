/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.repository.user;

import java.util.List;
import org.hmti.warehouse.domain.user.key.RolePrivilegePK;
import org.hmti.warehouse.domain.user.model.RolePrivilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asdin
 */
@Repository
public interface RolePrivilegeRepository extends JpaRepository<RolePrivilege, RolePrivilegePK> {

    public List<RolePrivilege> findByRolePrivilegePKRoleRolePKName(String name);
}
