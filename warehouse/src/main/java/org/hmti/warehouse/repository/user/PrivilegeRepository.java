/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.repository.user;

import org.hmti.warehouse.domain.user.key.PrivilegePK;
import org.hmti.warehouse.domain.user.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asdin
 */
@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, PrivilegePK> {
    
}
