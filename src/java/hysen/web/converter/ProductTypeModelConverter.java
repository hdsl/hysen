/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.converter;

import hysen.ejb.entities.ProductTypesModel;
import hysen.ejb.services.CrudService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author HYSEN SOFTWARE DEPT
 */
@FacesConverter(forClass = ProductTypesModel.class)
public class ProductTypeModelConverter implements javax.faces.convert.Converter {

    @Inject
    private CrudService crudService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("object,,,,,,,,,,,,,,,,,,,,,"+crudService.find(ProductTypesModel.class, value));
        return crudService.find(ProductTypesModel.class, value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        ProductTypesModel ptm = (ProductTypesModel) value;
        System.out.println("string..................."+ptm);
        return ptm.getCommonId();
    }

}
