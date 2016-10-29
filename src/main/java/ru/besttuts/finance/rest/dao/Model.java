package ru.besttuts.finance.rest.dao;

import ru.besttuts.finance.rest.domain.QuoteLastTradeDate;

import java.util.Date;
import java.util.List;

/**
 * @author romanchekashov
 * @since 13.03.2016
 */
public interface Model<T, I> {

    I save(T t);

    I[] save(T[] t);

}
