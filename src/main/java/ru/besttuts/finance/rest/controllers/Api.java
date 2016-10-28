package ru.besttuts.finance.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;
import spark.Response;
import su.gidroizolyaciya.site.models.Form;
import su.gidroizolyaciya.site.models.Type;
import su.gidroizolyaciya.site.util.Constants;

import java.io.IOException;

/**
 * @author romanchekashov
 * @since 14.03.2016
 */
public class Api {

    public static class FormManager {

        private Request request;
        private Response response;

        public FormManager(Request request, Response response) {
            this.request = request;
            this.response = response;
        }

        public Object saveForm(Type type) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            Form creation = mapper.readValue(request.body(), Form.class);
            creation.setType(type);
            if (!creation.isValid()) {
                response.status(Constants.HTTP_BAD_REQUEST);
                return "";
            }
            long id = creation.save();
            response.status(Constants.HTTP_OK);
            response.type("application/json");
            return id;
        }
    }

    public static FormManager use(Request request, Response response) {
        return new FormManager(request, response);
    }
}
