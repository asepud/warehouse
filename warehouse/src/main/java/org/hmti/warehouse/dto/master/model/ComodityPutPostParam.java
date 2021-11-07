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
import org.hmti.warehouse.domain.master.key.CategoryPK;
import org.hmti.warehouse.domain.master.key.ComodityPK;
import org.hmti.warehouse.domain.master.key.MeasurementPK;
import org.hmti.warehouse.domain.master.model.Category;
import org.hmti.warehouse.domain.master.model.Comodity;
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
public class ComodityPutPostParam {

    private final ComodityPK comodityPK;
    private final String unit;
    private final String measurementName;
    private final String category;

    public Comodity convertToCategory(String jwtToken) {
        ModelMapper mapper = new ModelMapper();
        Comodity comodity = mapper.map(this, Comodity.class);
        JWTUtil jWTUtil = new JWTUtil();
        String username = jWTUtil.getUsernameFromToken(jwtToken);
        comodity.setAudith(new Audith(new User(new UserPK(username))));
        comodity.setMeasurement(new Measurement(new MeasurementPK(measurementName)));
        comodity.setCategory(new Category(new CategoryPK(category)));
        return comodity;
    }
}
