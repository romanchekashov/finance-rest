package ru.besttuts.finance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.besttuts.finance.rest.dao.QuoteLastTradeDateDao;
import ru.besttuts.finance.rest.dao.QuoteLastTradeDateDaoDb;
import ru.besttuts.finance.rest.domain.QuoteLastTradeDate;
import ru.besttuts.finance.rest.util.Constants;
import spark.servlet.SparkApplication;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.threadPool;

/**
 * @author romanchekashov
 * @since 05.03.2016
 */
public class WebApp implements SparkApplication {

    private static final Logger LOG = LoggerFactory.getLogger(WebApp.class);

    private static final String UrlQuoteLastTradeDates = "/api/quote-last-trade-dates";

    @Override
    public void init() {
        LOG.info("init");
        threadPool(8);

        QuoteLastTradeDateDao quoteLastTradeDateDao = new QuoteLastTradeDateDaoDb();

        // get all post (using HTTP get method)
        get(UrlQuoteLastTradeDates, (request, response) -> {
            response.status(200);
            response.type("application/json; charset=utf-8");
            return dataToJson(quoteLastTradeDateDao.findByLastTradeDateGreaterThanOrderByLastTradeDate(new Date(10)));
        });

        post(UrlQuoteLastTradeDates, (request, response) -> {
            ObjectMapper mapper = new ObjectMapper();
            QuoteLastTradeDate[] quoteLastTradeDates = mapper.readValue(request.body(), QuoteLastTradeDate[].class);
            String[] ids = quoteLastTradeDateDao.save(quoteLastTradeDates);
            response.status(Constants.HTTP_OK);
            response.type("application/json");
            return ids;
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
