/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.view.master;

import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hmti.warehouse.domain.master.model.Category;
import org.hmti.warehouse.dto.master.model.CategoryPutPostParam;
import org.hmti.warehouse.dto.master.model.projection.CategoryMini;
import org.hmti.warehouse.service.master.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asdin
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public List<CategoryMini> findAll() {
        return categoryService.findAll();
    }

    @GetMapping
    public Page<CategoryMini> findAllMini(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC")
            @ApiParam(defaultValue = "ASC", allowableValues = "ASC, DESC",
                    example = "ASC") Sort.Direction direction,
            @RequestParam String[] properties) {
        return categoryService.findAllMini(page, size, direction, properties);
    }

    @GetMapping("/lite")
    public List<CategoryMini> findAllLite() {
        return categoryService.findAllLite();
    }

    @GetMapping("/lite/search")
    public List<CategoryMini> searchLite(@RequestParam String searchValue) {
        return categoryService.searchLite(searchValue);
    }

    @GetMapping("/name")
    public Category findByName(String name) {
        return categoryService.findByName(name);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('modify')")
    public void add(@RequestBody CategoryPutPostParam categoryPutPostParam, HttpServletRequest httpServletRequest) {
        categoryService.add(categoryPutPostParam, httpServletRequest);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('modify')")
    public void edit(@RequestBody CategoryPutPostParam categoryPutPostParam, HttpServletRequest httpServletRequest) {

        categoryService.edit(categoryPutPostParam, httpServletRequest);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('modify')")
    public void delete(String name) {
        categoryService.delete(name);
    }

}
