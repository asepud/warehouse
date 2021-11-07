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
import org.hmti.warehouse.domain.master.key.SuplierPK;
import org.hmti.warehouse.domain.master.model.Suplier;
import org.hmti.warehouse.dto.master.model.SuplierPutPostParam;
import org.hmti.warehouse.dto.master.model.projection.SuplierMini;
import org.hmti.warehouse.repository.master.SuplierRepository;
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
public class SuplierService {

    @Autowired
    private SuplierRepository suplierRepository;

    public List<SuplierMini> findAll() {
        return suplierRepository.findBy();
    }

    public Page<SuplierMini> findAllMini(int page, int size, Sort.Direction direction, String[] properties) {
        return suplierRepository.findAllMini(PageRequest.of(page, size, direction, properties));
    }

    public List<SuplierMini> findAllLite() {
        return suplierRepository.findAllLite();
    }

    public List<SuplierMini> searchLite(String searchValue) {
        return suplierRepository.searchLite(searchValue);
    }

    public Suplier findByName(String name) {
        SuplierPK suplierPK = new SuplierPK(name);
        Optional<Suplier> optional = suplierRepository.findById(suplierPK);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No measurement has " + name + " name");
        }
    }

    public void add(SuplierPutPostParam categoryPutPostParam, HttpServletRequest httpServletRequest) {
        Optional<Suplier> optional = suplierRepository.findById(categoryPutPostParam.getSuplierPK());
        if (optional.isPresent()) {
            log.log(Level.SEVERE, "Bad Request");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category with"
                    + categoryPutPostParam.getSuplierPK().getName() + "Is already present");
        }
        suplierRepository.save(categoryPutPostParam.convertToSuplier(httpServletRequest.getHeader("Authorization")));
    }

    public void edit(SuplierPutPostParam categoryPutPostParam, HttpServletRequest httpServletRequest) {
        Optional<Suplier> optional = suplierRepository.findById(categoryPutPostParam.getSuplierPK());
        if (optional.isEmpty()) {
            log.log(Level.SEVERE, "Bad Request");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category with"
                    + categoryPutPostParam.getSuplierPK().getName() + "Is not exist");
        }
        suplierRepository.save(categoryPutPostParam.convertToSuplier(httpServletRequest.getHeader("Authorization")));
    }

    public void delete(String name) {
        SuplierPK suplierPK = new SuplierPK(name);
        suplierRepository.deleteById(suplierPK);
    }

}
