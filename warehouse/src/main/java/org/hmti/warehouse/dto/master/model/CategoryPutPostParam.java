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
import org.hmti.warehouse.domain.master.model.Category;
import org.hmti.warehouse.domain.user.key.UserPK;
import org.hmti.warehouse.domain.user.model.User;
import org.modelmapper.ModelMapper;

/**
 *
 * @author asdin
 */
@Getter
@AllArgsConstructor
public class CategoryPutPostParam {

    private final CategoryPK categoryPK;
    private final String description;

    public Category convertToCategory(String jwtToken) {
        ModelMapper mapper = new ModelMapper();
        Category category = mapper.map(this, Category.class);
        JWTUtil jWTUtil = new JWTUtil();
        String username = jWTUtil.getUsernameFromToken(jwtToken);
        category.setAudith(new Audith(new User(new UserPK(username))));
        return category;
    }
}
