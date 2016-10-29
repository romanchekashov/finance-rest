package ru.besttuts.finance.rest.dao;

import ru.besttuts.finance.rest.domain.QuoteLastTradeDate;

import java.util.Date;
import java.util.List;

/**
 * @author romanchekashov
 * @since 13.03.2016
 */
public interface QuoteLastTradeDateDao extends Model<QuoteLastTradeDate, String> {

    String save(QuoteLastTradeDate quoteLastTradeDate);

    String[] save(QuoteLastTradeDate[] quoteLastTradeDates);

    List<QuoteLastTradeDate> findByLastTradeDateGreaterThanOrderByLastTradeDate(Date lastTradeDate);

}
