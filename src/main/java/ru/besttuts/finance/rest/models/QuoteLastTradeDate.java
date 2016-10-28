package ru.besttuts.finance.rest.models;

import org.sql2o.Connection;

import java.util.Date;
import java.util.List;

/**
 * @author romanchekashov
 * @since 13.03.2016
 */

public class QuoteLastTradeDate implements Model<QuoteLastTradeDate>, Validable {
    private String symbol;
    private Code code;
    private Date lastTradeDate;

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String save() {
        try (Connection conn = Sql2oSingleton.getSql2o().open()) {
            return (String) conn.createQuery("insert into quote_last_trade_date(symbol, code, last_trade_date) " +
                    "VALUES (:symbol, :code, :last_trade_date)", true)
                    .addParameter("symbol", symbol)
                    .addParameter("code", code)
                    .addParameter("last_trade_date", lastTradeDate)
                    .executeUpdate().getKeys()[0];
        }
    }

    public List<QuoteLastTradeDate> findByLastTradeDateGreaterThanOrderByLastTradeDate(Date lastTradeDate) {
        try (Connection conn = Sql2oSingleton.getSql2o().open()) {
            return conn.createQuery("select * from quote_last_trade_date where last_trade_date > :last_trade_date " +
                    "order by last_trade_date", true)
                    .addParameter("last_trade_date", lastTradeDate)
                    .executeAndFetch(QuoteLastTradeDate.class);
        }
    }

    public QuoteLastTradeDate() {}

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public Date getLastTradeDate() {
        return lastTradeDate;
    }

    public void setLastTradeDate(Date lastTradeDate) {
        this.lastTradeDate = lastTradeDate;
    }
}
