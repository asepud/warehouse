/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.view.master;

import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hmti.warehouse.domain.master.model.Measurement;
import org.hmti.warehouse.dto.master.model.MeasurementPutPostParam;
import org.hmti.warehouse.dto.master.model.projection.MeasurementMini;
import org.hmti.warehouse.service.master.MeasurementService;
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
@RequestMapping("/measurement")

public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

    @GetMapping("/all")
    public List<MeasurementMini> findAll() {
        return measurementService.findAll();
    }

    @GetMapping
    public Page<MeasurementMini> findAllMini(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC")
            @ApiParam(defaultValue = "ASC", allowableValues = "ASC, DESC",
                    example = "ASC") Sort.Direction direction,
            @RequestParam String[] properties) {
        return measurementService.findAllMini(page, size, direction, properties);
    }

    @GetMapping("/lite")
    public List<MeasurementMini> findAllLite() {
        return measurementService.findAllLite();
    }

    @GetMapping("/lite/search")
    public List<MeasurementMini> searchLite(@RequestParam String searchValue) {
        return measurementService.searchLite(searchValue);
    }

    @GetMapping("/name")

    public Measurement findByName(String name) {
        return measurementService.findByName(name);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('modify')")
    public void add(@RequestBody MeasurementPutPostParam measurementPutPostParam, HttpServletRequest httpServletRequest) {
        measurementService.add(measurementPutPostParam, httpServletRequest);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('modify')")
    public void edit(@RequestBody MeasurementPutPostParam measurementPutPostParam, HttpServletRequest httpServletRequest) {
        measurementService.edit(measurementPutPostParam, httpServletRequest);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('modify')")
    public void delete(@RequestParam String name) {
        measurementService.delete(name);
    }
}
