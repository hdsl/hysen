/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.ClientContact;
import hysen.ejb.entities.Department;
import hysen.ejb.services.CrudService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author HDSL_MUMIN
 */
@Named(value = "companyContactView")
@ViewScoped
public class CompanyContactView implements Serializable {

    @Inject
    CrudService crudService;

    List<ClientContact> clientContactList;

    @PostConstruct
    public void init() {
        
        clientContactList = crudService.findAll(ClientContact.class, false, "companyDetail.companyName");
        
    }

    public CompanyContactView() {
    }

    public CrudService getCrudService() {
        return crudService;
    }

    public void setCrudService(CrudService crudService) {
        this.crudService = crudService;
    }

    public List<ClientContact> getClientContactList() {
        return clientContactList;
    }

}
