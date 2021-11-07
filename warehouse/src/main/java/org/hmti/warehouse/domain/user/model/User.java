/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.domain.user.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hmti.warehouse.domain.user.key.UserPK;

/**
 *
 * @author asdin
 */
@Table(name = "user")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @EmbeddedId
    private UserPK userPK;
    private String password;
    @JoinColumn(name = "role", referencedColumnName = "name")
    @ManyToOne(optional = false)
    private Role role;

    public User(UserPK userPK) {
        this.userPK = userPK;
    }
    
}
