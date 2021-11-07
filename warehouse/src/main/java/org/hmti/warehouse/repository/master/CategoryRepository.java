/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.repository.master;

import java.util.List;
import org.hmti.warehouse.domain.master.key.CategoryPK;
import org.hmti.warehouse.domain.master.model.Category;
import org.hmti.warehouse.dto.master.model.projection.CategoryMini;
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
public interface CategoryRepository extends JpaRepository<Category, CategoryPK> {

    List<CategoryMini> findBy();

    @Query("SELECT ct FROM Category ct")
    Page<CategoryMini> findAllMini(Pageable pageable);

    @Query("SELECT ct FROM Category ct")
    List<CategoryMini> findAllLite();

    @Query("SELECT ct FROM Category ct where ct.categoryPK.name like %?1%")
    List<CategoryMini> searchLite(String searchValue);
}
