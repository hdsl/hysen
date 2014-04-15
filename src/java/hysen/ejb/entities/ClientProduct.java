/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.ejb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author HYSEN SOFTWARE DEPT
 */
@Entity
@Table(name = "client_product")
@NamedQueries({
    @NamedQuery(name = "ClientProduct.findAll", query = "SELECT c FROM ClientProduct c")})
public class ClientProduct extends CommonEntity{

    @ManyToOne
    @JoinColumn(name = "client_detail")
    private ClientDetail clientDetail;
    
    @ManyToOne
    @JoinColumn(name = "product_model")
    private ProductTypesModel productTypeModel;    
  
    @ManyToOne
    @JoinColumn(name = "regions")
    private Regions regions;
   
    @Column(name = "product_location")
    private String productLocation;
    
    @Column(name = "serial_number")
    private String serialNumber;

    public ClientProduct() {
    }

    public String getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(String productLocation) {
        this.productLocation = productLocation;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public ClientDetail getClientDetail() {
        return clientDetail;
    }

    public void setClientDetail(ClientDetail clientDetail) {
        this.clientDetail = clientDetail;
    }

    public Regions getRegions() {
        return regions;
    }

    public void setRegions(Regions regions) {
        this.regions = regions;
    }

    public ProductTypesModel getProductTypeModel() {
        return productTypeModel;
    }

    public void setProductTypeModel(ProductTypesModel productTypeModel) {
        this.productTypeModel = productTypeModel;
    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (commonId != null ? commonId.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof ClientProduct)) {
//            return false;
//        }
//        ClientProduct other = (ClientProduct) object;
//        if ((this.commonId == null && other.commonId != null) || (this.commonId != null && !this.commonId.equals(other.commonId))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "hysen.ejb.entities.ClientProduct[ commonId=" + commonId + " ]";
//    }

}
