/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.repository.master;

import java.util.List;
import org.hmti.warehouse.domain.master.key.SuplierPK;
import org.hmti.warehouse.domain.master.model.Suplier;
import org.hmti.warehouse.dto.master.model.projection.SuplierMini;
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
public interface SuplierRepository extends JpaRepository<Suplier, SuplierPK> {

    List<SuplierMini> findBy();

    @Query("SELECT sp FROM Suplier sp")
    Page<SuplierMini> findAllMini(Pageable pageable);

    @Query("SELECT sp FROM Suplier sp")
    List<SuplierMini> findAllLite();

    @Query("SELECT sp FROM Suplier sp where sp.suplierPK.name like %?1%")
    List<SuplierMini> searchLite(String searchValue);
}
