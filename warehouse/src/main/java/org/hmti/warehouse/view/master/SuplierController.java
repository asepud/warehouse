/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.view.master;

import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hmti.warehouse.domain.master.model.Suplier;
import org.hmti.warehouse.dto.master.model.SuplierPutPostParam;
import org.hmti.warehouse.dto.master.model.projection.SuplierMini;
import org.hmti.warehouse.service.master.SuplierService;
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
@RequestMapping("/suplier")
public class SuplierController {

    @Autowired
    private SuplierService suplierService;

    @GetMapping("/all")
    public List<SuplierMini> findAll() {
        return suplierService.findAll();
    }

    @GetMapping
    public Page<SuplierMini> findAllMini(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC")
            @ApiParam(defaultValue = "ASC", allowableValues = "ASC, DESC",
                    example = "ASC") Sort.Direction direction,
            @RequestParam String[] properties) {
        return suplierService.findAllMini(page, size, direction, properties);
    }

    @GetMapping("/lite")
    public List<SuplierMini> findAllLite() {
        return suplierService.findAllLite();
    }

    @GetMapping("/lite/search")
    public List<SuplierMini> searchLite(@RequestParam String searchValue) {
        return suplierService.searchLite(searchValue);
    }

    @GetMapping("/name")
    public Suplier findByName(String name) {
        return suplierService.findByName(name);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('modify')")
    public void add(@RequestBody SuplierPutPostParam categoryPutPostParam, HttpServletRequest httpServletRequest) {
        suplierService.add(categoryPutPostParam, httpServletRequest);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('modify')")
    public void edit(@RequestBody SuplierPutPostParam categoryPutPostParam, HttpServletRequest httpServletRequest) {

        suplierService.edit(categoryPutPostParam, httpServletRequest);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('modify')")
    public void delete(String name) {
        suplierService.delete(name);
    }
}
