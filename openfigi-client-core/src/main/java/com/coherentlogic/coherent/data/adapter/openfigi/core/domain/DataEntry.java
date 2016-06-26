package com.coherentlogic.coherent.data.adapter.openfigi.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;

@Entity
@Table(name=DataEntry.OPEN_FIGI_DATA_ENTRY)
public class DataEntry extends SerializableBean {

    private static final long serialVersionUID = 6929758074820720468L;

    public static final String OPEN_FIGI_DATA_ENTRY = "openFigiDataEntry";

    private String figi;
    private String name;
    private String ticker;
    private String exchangeCode;
    private String compositeFIGI;
    private String uniqueID;
    private String securityType;
    private String marketSector;
    private String shareClassFIGI;
    private String uniqueIDForFutureOption;

    public String getFigi() {
        return figi;
    }

    public void setFigi(String figi) {
        this.figi = figi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public String getCompositeFIGI() {
        return compositeFIGI;
    }

    public void setCompositeFIGI(String compositeFIGI) {
        this.compositeFIGI = compositeFIGI;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public String getMarketSector() {
        return marketSector;
    }

    public void setMarketSector(String marketSector) {
        this.marketSector = marketSector;
    }

    public String getShareClassFIGI() {
        return shareClassFIGI;
    }

    public void setShareClassFIGI(String shareClassFIGI) {
        this.shareClassFIGI = shareClassFIGI;
    }

    public String getUniqueIDForFutureOption() {
        return uniqueIDForFutureOption;
    }

    public void setUniqueIDForFutureOption(String uniqueIDForFutureOption) {
        this.uniqueIDForFutureOption = uniqueIDForFutureOption;
    }

    @Override
    public String toString() {
        return "DataEntry [figi=" + figi + ", name=" + name + ", ticker=" + ticker + ", exchangeCode=" + exchangeCode
            + ", compositeFIGI=" + compositeFIGI + ", uniqueID=" + uniqueID + ", securityType=" + securityType
            + ", marketSector=" + marketSector + ", shareClassFIGI=" + shareClassFIGI + ", uniqueIDForFutureOption="
            + uniqueIDForFutureOption + "]";
    }
}
