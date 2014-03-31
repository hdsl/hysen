/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hysen.ejb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author HYSEN SOFTWARE DEPT
 */
@Entity
@Table(name = "service_model_component")
@NamedQueries({
    @NamedQuery(name = "ServiceModelComponent.findAll", query = "SELECT s FROM ServiceModelComponent s")})
public class ServiceModelComponent extends CommonEntity {
  
    @JoinColumn(name = "product_types")
    private ProductTypes productTypes;
  
    @Column(name = "component_name")
    private String componentName;
    
    @Column(name = "component_description")
    private String componentDescription;

    public ServiceModelComponent() {
    }

    public ProductTypes getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(ProductTypes productTypes) {
        this.productTypes = productTypes;
    }

    public String getComponentDescription() {
        return componentDescription;
    }

    public void setComponentDescription(String componentDescription) {
        this.componentDescription = componentDescription;
    }
    
    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

}
