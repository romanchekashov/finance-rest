package ru.besttuts.finance.rest.dto;

import ru.besttuts.finance.rest.domain.Code;

/**
 * @author romanchekashov
 * @since 13.03.2016
 */

public class QuoteLastTradeDateDto {
    private String symbol;
    private Code code;
    private long lastTradeDate;

    public QuoteLastTradeDateDto() {}

    public QuoteLastTradeDateDto(String symbol, Code code, long lastTradeDate) {
        this.symbol = symbol;
        this.code = code;
        this.lastTradeDate = lastTradeDate;
    }

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

    public long getLastTradeDate() {
        return lastTradeDate;
    }

    public void setLastTradeDate(long lastTradeDate) {
        this.lastTradeDate = lastTradeDate;
    }
}
