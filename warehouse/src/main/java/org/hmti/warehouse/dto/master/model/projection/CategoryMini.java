/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.dto.master.model.projection;

import org.hmti.warehouse.dto.master.key.projection.CategoryPKProjection;

/**
 *
 * @author asdin
 */
public interface CategoryMini {

    CategoryPKProjection getCategoryPK();

//    String getName();
    String getDescription();
}