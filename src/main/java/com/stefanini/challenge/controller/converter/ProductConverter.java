package com.stefanini.challenge.controller.converter;

import com.stefanini.challenge.model.Product;
import com.stefanini.challenge.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Component
@FacesConverter(value = "productConverter", forClass = Product.class)
public class ProductConverter implements Converter {

    @Autowired
    private IProductService service;


    @Override
    public Product getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            return service.getByName(value);
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return value.toString();
        } else {
            return null;
        }
    }
}
