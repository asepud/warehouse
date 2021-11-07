/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.service.master;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import org.hmti.warehouse.domain.master.key.CategoryPK;
import org.hmti.warehouse.domain.master.model.Category;
import org.hmti.warehouse.dto.master.model.CategoryPutPostParam;
import org.hmti.warehouse.dto.master.model.projection.CategoryMini;
import org.hmti.warehouse.repository.master.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author asdin
 */
@Service
@Log
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryMini> findAll() {
        return categoryRepository.findBy();
    }

    public Page<CategoryMini> findAllMini(int page, int size, Sort.Direction direction, String[] properties) {
        return categoryRepository.findAllMini(PageRequest.of(page, size, direction, properties));
    }

    public List<CategoryMini> findAllLite() {
        return categoryRepository.findAllLite();
    }

    public List<CategoryMini> searchLite(String searchValue) {
        return categoryRepository.searchLite(searchValue);
    }

    public Category findByName(String name) {
        CategoryPK categoryPK = new CategoryPK(name);
        Optional<Category> optional = categoryRepository.findById(categoryPK);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No measurement has " + name + " name");
        }
    }

    public void add(CategoryPutPostParam categoryPutPostParam, HttpServletRequest httpServletRequest) {
        Optional<Category> optional = categoryRepository.findById(categoryPutPostParam.getCategoryPK());
        if (optional.isPresent()) {
            log.log(Level.SEVERE, "Bad Request");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category with"
                    + categoryPutPostParam.getCategoryPK().getName() + "Is already present");
        }
        categoryRepository.save(categoryPutPostParam.convertToCategory(httpServletRequest.getHeader("Authorization")));
    }

    public void edit(CategoryPutPostParam categoryPutPostParam, HttpServletRequest httpServletRequest) {
        Optional<Category> optional = categoryRepository.findById(categoryPutPostParam.getCategoryPK());
        if (optional.isEmpty()) {
            log.log(Level.SEVERE, "Bad Request");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category with "
                    + categoryPutPostParam.getCategoryPK().getName() + " Is not exist");
        }
        categoryRepository.save(categoryPutPostParam.convertToCategory(httpServletRequest.getHeader("Authorization")));
    }

    public void delete(String name) {
        CategoryPK categoryPK = new CategoryPK(name);
        categoryRepository.deleteById(categoryPK);
    }
}
