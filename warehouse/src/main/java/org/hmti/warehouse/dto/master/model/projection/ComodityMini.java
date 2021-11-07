/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.dto.master.model.projection;

import org.hmti.warehouse.dto.master.key.projection.ComodityPKProjection;

/**
 *
 * @author asdin
 */
public interface ComodityMini {

    ComodityPKProjection getComodityPK();

//    String getName();
    String getUnit();

    MeasurementLite getMeasurement();

    CategoryLite getCategory();
}
