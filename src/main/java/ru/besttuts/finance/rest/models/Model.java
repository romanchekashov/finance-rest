package ru.besttuts.finance.rest.models;

import java.util.Date;
import java.util.List;

/**
 * @author romanchekashov
 * @since 13.03.2016
 */
public interface Model<T> {

    String save();

    List<QuoteLastTradeDate> findByLastTradeDateGreaterThanOrderByLastTradeDate(Date lastTradeDate);

}
