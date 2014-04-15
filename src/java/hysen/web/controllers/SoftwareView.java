/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.ServiceSoftware;
import hysen.ejb.services.CrudService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author AbdulMumin
 */
@Named
@ViewScoped
public class SoftwareView implements Serializable {

    @Inject
    private CrudService crudService;

    private List<ServiceSoftware> softwareList;

    @PostConstruct
    public void init() {
        softwareList = crudService.findAll(ServiceSoftware.class, false);
    }

    public List<ServiceSoftware> getSoftwareList() {
        return softwareList;
    }

}
