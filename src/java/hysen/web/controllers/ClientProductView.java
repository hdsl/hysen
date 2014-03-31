/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.controllers;

import hysen.ejb.entities.ClientProduct;
import hysen.ejb.services.CrudService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author HYSEN SOFTWARE DEPT
 */
@Named
@ViewScoped
public class ClientProductView implements Serializable {

    @Inject
    private CrudService crudService;

    private List<ClientProduct> clientProductList;

    @PostConstruct
    public void init() {
        clientProductList = crudService.findAll(ClientProduct.class, false);
    }

    public List<ClientProduct> getClientProductList() {
        return clientProductList;
    }

}
