/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.domain.user.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hmti.warehouse.domain.Audith;
import org.hmti.warehouse.domain.user.key.PrivilegePK;

/**
 *
 * @author asdin
 */
@Table(name = "privilege")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Privilege implements Serializable {

    @EmbeddedId
    private PrivilegePK privilegePK;
    @Column(name = "description")
    private String description;
    @Embedded
    private Audith audith;

    public Privilege(PrivilegePK privilegePK) {
        this.privilegePK = privilegePK;
    }

}
