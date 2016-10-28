package ru.besttuts.finance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.handlebars.HandlebarsTemplateEngine;
import su.gidroizolyaciya.site.controllers.Api;
import su.gidroizolyaciya.site.models.Type;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

/**
 * @author romanchekashov
 * @since 05.03.2016
 */
public class WebApp implements SparkApplication {

    private static final Logger LOG = LoggerFactory.getLogger(WebApp.class);

    @Override
    public void init() {
        LOG.info("init");
        staticFileLocation("/public");
        threadPool(8);

        // get all post (using HTTP get method)
        get("/api/parse-yahoo-for-quote-last-trade-date", (request, response) -> {
            response.status(200);
            response.type("application/json");
            return dataToJson(model.getAllPosts());
        });

        post("/api/callbacks", (request, response) -> {
            return Api.use(request, response).saveForm(Type.CALLBACK);
        });
        post("/api/targets", (request, response) -> {
            return Api.use(request, response).saveForm(Type.TARGET);
        });
        post("/api/questions", (request, response) -> {
            return Api.use(request, response).saveForm(Type.QUESTION);
        });
        post("/api/comments", (request, response) -> {
            return Api.use(request, response).saveForm(Type.COMMENT);
        });
    }

    @Override
    public void destroy() {
        LOG.info("destroy");
    }

    public static String dataToJson(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);
            return sw.toString();
        } catch (IOException e){
            throw new RuntimeException("IOException from a StringWriter?");
        }
    }


}
