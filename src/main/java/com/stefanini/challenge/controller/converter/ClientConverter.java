package com.stefanini.challenge.controller.converter;

import com.stefanini.challenge.model.Client;
import com.stefanini.challenge.service.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Component
@FacesConverter(value = "clientConverter", forClass = Client.class)
public class ClientConverter implements Converter {

    @Autowired
    private IClientService service;

    @Override
    public Client getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            String[] split = value.split(" \\|");
            return service.getById(split[0]);
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
