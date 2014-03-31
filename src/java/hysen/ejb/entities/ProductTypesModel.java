/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hysen.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author AbdulMumin
 */
@Entity
@Table(name = "product_types_model")
@NamedQueries({
    @NamedQuery(name = "ProductTypesModel.findAll", query = "SELECT p FROM ProductTypesModel p")})
public class ProductTypesModel extends CommonEntity {
    
    @Column(name = "product_desc")
    private String productDesc;
   
    @Column(name = "product_model")
    private String productModel;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_types")
    private ProductTypes productTypes;

    public ProductTypesModel() {
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public ProductTypes getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(ProductTypes productTypes) {
        this.productTypes = productTypes;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }
    
}
