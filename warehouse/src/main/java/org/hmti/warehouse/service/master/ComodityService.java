/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hmti.warehouse.service.master;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.hmti.warehouse.domain.master.key.ComodityPK;
import org.hmti.warehouse.domain.master.model.Comodity;
import org.hmti.warehouse.dto.master.model.ComodityPutPostParam;
import org.hmti.warehouse.dto.master.model.projection.ComodityMini;
import org.hmti.warehouse.repository.master.ComodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author asdin
 */
@Service
public class ComodityService {

    @Autowired
    private ComodityRepository comodityRepository;

    public List<ComodityMini> findAll() {
        return comodityRepository.findBy();
    }

    public Comodity findByName(String name) {
        ComodityPK comodityPK = new ComodityPK(name);
        Optional<Comodity> optional = comodityRepository.findById(comodityPK);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No measurement has " + name + " name");
        }
    }

    public void add(ComodityPutPostParam comodityPutPostParam, HttpServletRequest httpServletRequest) {
        Optional<Comodity> optional = comodityRepository.findById(comodityPutPostParam.getComodityPK());
        if (optional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category with"
                    + comodityPutPostParam.getComodityPK().getName() + "Is already present");
        }
        comodityRepository.save(comodityPutPostParam.convertToCategory(httpServletRequest.getHeader("Authorization")));
    }

    public void edit(ComodityPutPostParam comodityPutPostParam, HttpServletRequest httpServletRequest) {
        Optional<Comodity> optional = comodityRepository.findById(comodityPutPostParam.getComodityPK());
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Measurement with "
                    + comodityPutPostParam.getComodityPK().getName() + " is not exist");
        }
        comodityRepository.save(comodityPutPostParam.convertToCategory(httpServletRequest.getHeader("Authorization")));
    }

    public void delete(String name) {
        ComodityPK comodityPK = new ComodityPK(name);
        comodityRepository.deleteById(comodityPK);
    }
}
