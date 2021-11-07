/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hmti.warehouse.domain.user.model.User;

/**
 *
 * @author asdin
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Audith implements Serializable {

    @ManyToOne(optional = false)
    @JoinColumn(name = "updated_by", referencedColumnName = "username")
    private User user;
    @Basic(optional = false)
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    public Audith(User user) {
        this.user = user;
        updatedDate = new Date();

    }

}
