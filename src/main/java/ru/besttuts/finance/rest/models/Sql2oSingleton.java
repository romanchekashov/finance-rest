package ru.besttuts.finance.rest.models;

import org.sql2o.Sql2o;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author romanchekashov
 * @since 13.03.2016
 */
public class Sql2oSingleton {
    private static Sql2o sql2o;
    private Sql2oSingleton(){}
    public static Sql2o getSql2o(){
        if (null == sql2o){
            Properties props = getProperties();
            sql2o = new Sql2o(props.getProperty("url"), props.getProperty("username"),
                    props.getProperty("password"));
        }
        return sql2o;
    }

    private static Properties getProperties(){
        String resourceName = "liquibase/liquibase.properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();

        try {
            try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
                props.load(resourceStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return props;
    }
}
