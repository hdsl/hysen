/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.ClientDetail;
import hysen.ejb.services.CrudService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author AbdulMumin
 */
@Named(value = "companyDetailView")
@ViewScoped
public class CompanyDetailView implements Serializable {

    @Inject
    CrudService crudService;

    List<ClientDetail> clientDetailList;   

    @PostConstruct
    public void init() {

        clientDetailList = crudService.findAll(ClientDetail.class, false);
//  
    }

    public List<ClientDetail> getClientDetailList() {
        return clientDetailList;
    }

}
