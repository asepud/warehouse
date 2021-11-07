/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.dto.user.model.projection;

import org.hmti.warehouse.dto.user.key.projection.PrivilegePKLite;

/**
 *
 * @author asdin
 */
public interface PrivilegeLite {

    PrivilegePKLite getPrivilegePK();

    String getDescription();
}
