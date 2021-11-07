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
import org.hmti.warehouse.domain.master.key.MeasurementPK;
import org.hmti.warehouse.domain.master.model.Measurement;
import org.hmti.warehouse.domain.user.key.UserPK;
import org.hmti.warehouse.domain.user.model.User;
import org.modelmapper.ModelMapper;

/**
 *
 * @author asdin
 */
@Getter
@AllArgsConstructor
public class MeasurementPutPostParam {

    private final MeasurementPK measurementPK;
    private final String nickName;
    private final String description;

    public Measurement convertToMeasurement(String jwtToken) {
        ModelMapper mapper = new ModelMapper();
        Measurement measurement = mapper.map(this, Measurement.class);
        JWTUtil jWTUtil = new JWTUtil();
        String username = jWTUtil.getUsernameFromToken(jwtToken);
        measurement.setAudith(new Audith(new User(new UserPK(username))));
        return measurement;
    }
}
