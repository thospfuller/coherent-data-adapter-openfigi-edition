package com.coherentlogic.coherent.data.adapter.openfigi.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Tuplizer;

import com.coherentlogic.coherent.data.adapter.openfigi.core.hibernate.CGLIBEnhancedObjectTuplizer;
import com.coherentlogic.coherent.data.model.core.annotations.Changeable;
import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;

@Entity
@Table(name=DataEntry.OPEN_FIGI_DATA_ENTRY)
@Tuplizer(impl=CGLIBEnhancedObjectTuplizer.class)
public class DataEntry extends SerializableBean<DataEntry> {

    private static final long serialVersionUID = 7961878075475785809L;

    public static final String OPEN_FIGI_DATA_ENTRY = "openFIGIDataEntry";

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

//    public DataEntry () {
//        new Exception ("getDataEntry called!").printStackTrace();
//    }

    public String getFigi() {
        return figi;
    }

    public static final String FIGI = "figi";

    public void setFigi(@Changeable(FIGI) String figi) {
        this.figi = figi;
    }

    public String getName() {
        return name;
    }

    public static final String NAME = "name";

    public void setName(@Changeable(NAME) String name) {
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public static final String TICKER = "ticker";

    public void setTicker(@Changeable(TICKER) String ticker) {
        this.ticker = ticker;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public static final String EXCHANGE_CODE = "exchangeCode";

    public void setExchangeCode(@Changeable(EXCHANGE_CODE) String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public String getCompositeFIGI() {
        return compositeFIGI;
    }

    public static final String COMPOSITE_FIGI = "compositeFIGI";

    public void setCompositeFIGI(@Changeable(COMPOSITE_FIGI) String compositeFIGI) {
        this.compositeFIGI = compositeFIGI;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public static final String UNIQUE_ID = "uniqueID";

    public void setUniqueID(@Changeable(UNIQUE_ID) String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getSecurityType() {
        return securityType;
    }

    public static final String SECURITY_TYPE = "securityType";

    public void setSecurityType(@Changeable(SECURITY_TYPE) String securityType) {
        this.securityType = securityType;
    }

    public String getMarketSector() {
        return marketSector;
    }

    public static final String MARKET_SECTOR = "marketSector";

    public void setMarketSector(@Changeable(MARKET_SECTOR) String marketSector) {
        this.marketSector = marketSector;
    }

    public String getShareClassFIGI() {
        return shareClassFIGI;
    }

    public static final String SHARE_CLASS_FIGI = "shareClassFIGI";

    public void setShareClassFIGI(@Changeable(SHARE_CLASS_FIGI) String shareClassFIGI) {
        this.shareClassFIGI = shareClassFIGI;
    }

    public String getUniqueIDForFutureOption() {
        return uniqueIDForFutureOption;
    }

    public static final String UNIQUE_ID_FOR_FUTURE_OPTION = "uniqueIDForFutureOption";

    public void setUniqueIDForFutureOption(@Changeable(UNIQUE_ID_FOR_FUTURE_OPTION) String uniqueIDForFutureOption) {
        this.uniqueIDForFutureOption = uniqueIDForFutureOption;
    }

    @Override
    public String toString() {
        return "DataEntry [figi=" + figi + ", name=" + name + ", ticker=" + ticker + ", exchangeCode=" + exchangeCode
            + ", compositeFIGI=" + compositeFIGI + ", uniqueID=" + uniqueID + ", securityType=" + securityType
            + ", marketSector=" + marketSector + ", shareClassFIGI=" + shareClassFIGI + ", uniqueIDForFutureOption="
            + uniqueIDForFutureOption + ", toString()=" + super.toString() + "]";
    }
}
