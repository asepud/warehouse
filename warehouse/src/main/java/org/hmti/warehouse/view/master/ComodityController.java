/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.view.master;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hmti.warehouse.domain.master.model.Comodity;
import org.hmti.warehouse.dto.master.model.ComodityPutPostParam;
import org.hmti.warehouse.dto.master.model.projection.ComodityMini;
import org.hmti.warehouse.service.master.ComodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asdin
 */
@RestController
@RequestMapping("/comodity")
public class ComodityController {

    @Autowired
    private ComodityService comodityService;

    @GetMapping("/all")
    public List<ComodityMini> findAll() {
        return comodityService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('modify')")
    public void add(@RequestBody ComodityPutPostParam comodityPutPostParam, HttpServletRequest httpServletRequest) {
        comodityService.add(comodityPutPostParam, httpServletRequest);
    }

    @GetMapping("/name")
    public Comodity findByName(String name) {
        return comodityService.findByName(name);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('modify')")
    public void edit(@RequestBody ComodityPutPostParam comodityPutPostParam, HttpServletRequest httpServletRequest) {

        comodityService.edit(comodityPutPostParam, httpServletRequest);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('modify')")
    public void delete(String name) {
        comodityService.delete(name);
    }
}
