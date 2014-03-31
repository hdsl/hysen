/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.ServiceModelComponent;
import hysen.ejb.services.CrudService;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author HYSEN SOFTWARE DEPT
 */
@Named(value = "modelComponentView")
@ViewScoped
public class ModelComponentView implements Serializable {

    @Inject
    private CrudService crudService;

    private List<ServiceModelComponent> modelComponentList;

    @PostConstruct
    public void init() {
        modelComponentList = crudService.findAll(ServiceModelComponent.class, false);

    }

    public List<ServiceModelComponent> getModelComponentList() {
        return modelComponentList;
    }

}
