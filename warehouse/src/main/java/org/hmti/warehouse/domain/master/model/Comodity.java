/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.domain.master.model;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hmti.warehouse.domain.Audith;
import org.hmti.warehouse.domain.master.key.ComodityPK;

/**
 *
 * @author asdin
 */
@Entity
@Table(name = "comodity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comodity implements Serializable {

    @EmbeddedId
    private ComodityPK comodityPK;
    private String unit;
    @ManyToOne(optional = false)
    @JoinColumn(name = "measurement_name", referencedColumnName = "name")
    private Measurement measurement;
    @ManyToOne(optional = false)
    @JoinColumn(name = "category", referencedColumnName = "name")
    private Category category;

    @Embedded
    private Audith audith;

    public Comodity(ComodityPK comodityPK) {
        this.comodityPK = comodityPK;
    }

}
