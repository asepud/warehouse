/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.domain.master.key;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author asdin
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuplierPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "name")
    private String name;
}
