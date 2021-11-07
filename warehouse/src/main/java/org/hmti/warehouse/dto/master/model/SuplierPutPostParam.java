/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.dto.master.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hmti.warehouse.config.util.JWTUtil;
import org.hmti.warehouse.domain.Audith;
import org.hmti.warehouse.domain.master.key.SuplierPK;
import org.hmti.warehouse.domain.master.model.Suplier;
import org.hmti.warehouse.domain.user.key.UserPK;
import org.hmti.warehouse.domain.user.model.User;
import org.modelmapper.ModelMapper;

/**
 *
 * @author asdin
 */
@Getter
@AllArgsConstructor
public class SuplierPutPostParam {

    private final SuplierPK suplierPK;
    private final String address;
    private final String phoneNumber;
    private final String contactNumber;

    public Suplier convertToSuplier(String jwtToken) {
        ModelMapper mapper = new ModelMapper();
        Suplier suplier = mapper.map(this, Suplier.class);
        JWTUtil jWTUtil = new JWTUtil();
        String username = jWTUtil.getUsernameFromToken(jwtToken);
        suplier.setAudith(new Audith(new User(new UserPK(username))));
        return suplier;
    }
}
