/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.domain.user.key;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hmti.warehouse.domain.user.model.Privilege;
import org.hmti.warehouse.domain.user.model.Role;

/**
 *
 * @author asdin
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePrivilegePK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "name")
    @JsonIgnore
    private Role role;
    @ManyToOne
    @JoinColumn(name = "privilege", referencedColumnName = "name")
    private Privilege privilege;
}
