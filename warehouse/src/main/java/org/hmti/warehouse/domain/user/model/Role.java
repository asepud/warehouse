/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.domain.user.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hmti.warehouse.domain.user.key.RolePK;

/**
 *
 * @author asdin
 */
@Table(name = "role")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    @EmbeddedId
    private RolePK rolePK;
    @Column(name = "description")
    private String description;

    public Role(RolePK rolePK) {
        this.rolePK = rolePK;
    }

}
