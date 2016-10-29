package ru.besttuts.finance.rest.dao;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.besttuts.finance.rest.domain.QuoteLastTradeDate;
import ru.besttuts.finance.rest.domain.Sql2oSingleton;

import java.util.Date;
import java.util.List;

/**
 * @author romanchekashov
 * @since 13.03.2016
 */

public class QuoteLastTradeDateDaoDb implements QuoteLastTradeDateDao {

    private Sql2o sql2o;

    public QuoteLastTradeDateDaoDb() {
        sql2o = Sql2oSingleton.getSql2o();
    }

    @Override
    public String save(QuoteLastTradeDate quoteLastTradeDate) {
        try (Connection conn = sql2o.open()) {
            return (String) conn.createQuery("insert into quote_last_trade_date(symbol, code, last_trade_date) " +
                    "VALUES (:symbol, :code, :last_trade_date)", true)
                    .addParameter("symbol", quoteLastTradeDate.getSymbol())
                    .addParameter("code", quoteLastTradeDate.getCode())
                    .addParameter("last_trade_date", quoteLastTradeDate.getLastTradeDate())
                    .executeUpdate().getKeys()[0];
        }
    }

    @Override
    public String[] save(QuoteLastTradeDate[] quoteLastTradeDates) {
        try (Connection conn = sql2o.open()) {
            int LEN = quoteLastTradeDates.length;
            String[] ids = new String[LEN];
            for (int i = 0; i < LEN; i++){
                ids[i] = (String) conn.createQuery("insert into quote_last_trade_date(symbol, code, last_trade_date) " +
                        "VALUES (:symbol, :code, :last_trade_date)", true)
                        .addParameter("symbol", quoteLastTradeDates[i].getSymbol())
                        .addParameter("code", quoteLastTradeDates[i].getCode())
                        .addParameter("last_trade_date", quoteLastTradeDates[i].getLastTradeDate())
                        .executeUpdate().getKeys()[0];
            }

            return ids;
        }
    }

    @Override
    public List<QuoteLastTradeDate> findByLastTradeDateGreaterThanOrderByLastTradeDate(Date lastTradeDate) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("select * from quote_last_trade_date where last_trade_date > :lastTradeDate " +
                    "order by last_trade_date")
                    .addParameter("lastTradeDate", lastTradeDate)
                    .addColumnMapping("last_trade_date", "lastTradeDate")
                    .executeAndFetch(QuoteLastTradeDate.class);
        }
    }

}
