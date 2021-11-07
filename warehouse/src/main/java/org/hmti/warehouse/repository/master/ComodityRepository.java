/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.repository.master;

import java.util.List;
import org.hmti.warehouse.domain.master.key.ComodityPK;
import org.hmti.warehouse.domain.master.model.Comodity;
import org.hmti.warehouse.dto.master.model.projection.ComodityMini;
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
public interface ComodityRepository extends JpaRepository<Comodity, ComodityPK> {

    List<ComodityMini> findBy();

    @Query("SELECT ct FROM Category ct")
    Page<ComodityMini> findAllMini(Pageable pageable);

    @Query("SELECT ct FROM Category ct")
    List<ComodityMini> findAllLite();

    @Query("SELECT ct FROM Category ct where ct.categoryPK.name like %?1%")
    List<ComodityMini> searchLite(String searchValue);
}
