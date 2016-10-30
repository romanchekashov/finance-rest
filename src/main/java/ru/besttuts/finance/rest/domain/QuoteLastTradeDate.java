package ru.besttuts.finance.rest.domain;

import java.util.Date;

/**
 * @author romanchekashov
 * @since 13.03.2016
 */

public class QuoteLastTradeDate {
    private String symbol;
    private Code code;
    private Date lastTradeDate;

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
