/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.repository.master;

import java.util.List;
import org.hmti.warehouse.domain.master.key.MeasurementPK;
import org.hmti.warehouse.domain.master.model.Measurement;
import org.hmti.warehouse.dto.master.model.projection.MeasurementMini;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asdin
 */
@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, MeasurementPK> {

    List<MeasurementMini> findBy();

    @Query("SELECT ms FROM Measurement ms")
    Page<MeasurementMini> findAllMini(Pageable pageable);

    @Query("SELECT ms FROM Measurement ms")
    List<MeasurementMini> findAllLite();

    @Query("SELECT ms FROM Measurement ms where ms.measurementPK.name like %?1%")
    List<MeasurementMini> searchLite(String searchValue);

}
