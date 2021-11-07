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
import org.hmti.warehouse.domain.master.key.MeasurementPK;
import org.hmti.warehouse.domain.master.model.Measurement;
import org.hmti.warehouse.dto.master.model.MeasurementPutPostParam;
import org.hmti.warehouse.dto.master.model.projection.MeasurementMini;
import org.hmti.warehouse.repository.master.MeasurementRepository;
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
public class MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    public List<MeasurementMini> findAll() {
        return measurementRepository.findBy();
    }

    public Page<MeasurementMini> findAllMini(int page, int size, Sort.Direction direction, String[] properties) {
        return measurementRepository.findAllMini(PageRequest.of(page, size, direction, properties));
    }

    public List<MeasurementMini> findAllLite() {
        return measurementRepository.findAllLite();
    }

    public List<MeasurementMini> searchLite(String searchValue) {
        return measurementRepository.searchLite(searchValue);
    }

    public Measurement findByName(String name) {
        MeasurementPK measurementPK = new MeasurementPK(name);
        Optional<Measurement> optional = measurementRepository.findById(measurementPK);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No measurement has " + name + " name");
        }
    }

    public void add(MeasurementPutPostParam measurementPutPostParam, HttpServletRequest httpServletRequest) {
        Optional<Measurement> optional = measurementRepository.findById(measurementPutPostParam.getMeasurementPK());
        if (optional.isPresent()) {
            log.log(Level.SEVERE, "Bad request");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Measurement with "
                    + measurementPutPostParam.getMeasurementPK().getName() + " is already present");
        }
        measurementRepository.save(measurementPutPostParam.convertToMeasurement(httpServletRequest.getHeader("Authorization")));
    }

    public void edit(MeasurementPutPostParam measurementPutPostParam, HttpServletRequest httpServletRequest) {
        Optional<Measurement> optional = measurementRepository.findById(measurementPutPostParam.getMeasurementPK());
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Measurement with "
                    + measurementPutPostParam.getMeasurementPK().getName() + " is not exist");
        }
        measurementRepository.save(measurementPutPostParam.convertToMeasurement(httpServletRequest.getHeader("Authorization")));
    }

    public void delete(String name) {
        MeasurementPK measurementPK = new MeasurementPK(name);
        measurementRepository.deleteById(measurementPK);
    }
}
