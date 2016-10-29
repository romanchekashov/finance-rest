package ru.besttuts.finance.rest.domain;

import java.util.Date;

/**
 * @author romanchekashov
 * @since 13.03.2016
 */

public class QuoteLastTradeDate implements Validable {
    private String symbol;
    private Code code;
    private Date last_trade_date;

    @Override
    public boolean isValid() {
        return true;
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

    public Date getLast_trade_date() {
        return last_trade_date;
    }

    public void setLast_trade_date(Date last_trade_date) {
        this.last_trade_date = last_trade_date;
    }
}
