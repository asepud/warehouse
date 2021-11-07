/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.domain.master.model;

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
import org.hmti.warehouse.domain.master.key.SuplierPK;

/**
 *
 * @author asdin
 */
@Entity
@Table(name = "suplier")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Suplier implements Serializable {

    @EmbeddedId
    private SuplierPK suplierPK;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "contact_number")
    private String contactNumber;
    @Embedded
    private Audith audith;

    public Suplier(SuplierPK suplierPK) {
        this.suplierPK = suplierPK;
    }

}
