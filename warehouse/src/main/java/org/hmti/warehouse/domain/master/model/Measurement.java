/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.domain.master.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hmti.warehouse.domain.Audith;
import org.hmti.warehouse.domain.master.key.MeasurementPK;

/**
 *
 * @author asdin
 */
@Entity
@Table(name = "measurement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Measurement implements Serializable {

    @EmbeddedId
    private MeasurementPK measurementPK;
    @Basic(optional = false)
    @Column(name = "nick_name", unique = true, length = 5)
    private String nickName;
    @Column(name = "description")
    private String description;
    @Embedded
    private Audith audith;

    public Measurement(MeasurementPK measurementPK) {
        this.measurementPK = measurementPK;
    }

}
