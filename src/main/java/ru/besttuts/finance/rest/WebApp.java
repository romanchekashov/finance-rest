package ru.besttuts.finance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.besttuts.finance.rest.dao.QuoteLastTradeDateDao;
import ru.besttuts.finance.rest.dao.QuoteLastTradeDateDaoDb;
import ru.besttuts.finance.rest.domain.QuoteLastTradeDate;
import ru.besttuts.finance.rest.dto.QuoteLastTradeDateDto;
import ru.besttuts.finance.rest.util.Constants;
import spark.servlet.SparkApplication;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
            List<QuoteLastTradeDate> quotes = quoteLastTradeDateDao.findByLastTradeDateGreaterThanOrderByLastTradeDate(new Date(10));
            List<QuoteLastTradeDateDto> quoteDtos = new ArrayList<QuoteLastTradeDateDto>(quotes.size());
            quotes.stream().forEach(quote -> {
                quoteDtos.add(new QuoteLastTradeDateDto(quote.getSymbol(), quote.getCode(), quote.getLastTradeDate().getTime()));
            });
            return dataToJson(quoteDtos);
        });

        post(UrlQuoteLastTradeDates, (request, response) -> {
            ObjectMapper mapper = new ObjectMapper();
            QuoteLastTradeDate[] quoteLastTradeDates = mapper.readValue(request.body(), QuoteLastTradeDate[].class);
            quoteLastTradeDateDao.deleteAll();
            String[] ids = quoteLastTradeDateDao.save(quoteLastTradeDates);
            response.status(Constants.HTTP_OK);
            response.type("application/json");
            String idsJson = dataToJson(ids);
            LOG.info("idsJson = " + idsJson);
            return idsJson;
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
