package au.edu.unsw.infs3634_lab.api;

import java.util.ArrayList;

public class Crypto {

    private String id;
    private String symbol;
    private String name;
    private String nameid;
    private Integer rank;
    private String priceUsd;
    private String percentChange24h;
    private String percentChange1h;
    private String percentChange7d;
    private String priceBtc;
    private String marketCapUsd;
    private Double volume24;
    private Double volume24a;
    private String csupply;
    private String tsupply;
    private String msupply;

    public Crypto(String symbol, String name, String nameid, Integer rank, String priceUsd, String percentChange24h, String percentChange1h, String percentChange7d, String priceBtc,
                  String marketCapUsd, Double volume24, Double volume24a, String csupply, String tsupply, String msupply) {
        this.symbol = symbol;
        this.name = name;
        this.nameid = nameid;
        this.rank = rank;
        this.priceUsd = priceUsd;
        this.percentChange24h = percentChange24h;
        this.percentChange1h = percentChange1h;
        this.percentChange7d = percentChange7d;
        this.priceBtc = priceBtc;
        this.marketCapUsd = marketCapUsd;
        this.volume24 = volume24;
        this.volume24a = volume24a;
        this.csupply = csupply;
        this.tsupply = tsupply;
        this.msupply = msupply;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameid() {
        return nameid;
    }

    public void setNameid(String nameid) {
        this.nameid = nameid;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(String priceUsd) {
        this.priceUsd = priceUsd;
    }

    public String getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(String percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public String getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(String percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public String getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(String percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public String getPriceBtc() {
        return priceBtc;
    }

    public void setPriceBtc(String priceBtc) {
        this.priceBtc = priceBtc;
    }

    public String getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(String marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public Double getVolume24() {
        return volume24;
    }

    public void setVolume24(Double volume24) {
        this.volume24 = volume24;
    }

    public Double getVolume24a() {
        return volume24a;
    }

    public void setVolume24a(Double volume24a) {
        this.volume24a = volume24a;
    }

    public String getCsupply() {
        return csupply;
    }

    public void setCsupply(String csupply) {
        this.csupply = csupply;
    }

    public String getTsupply() {
        return tsupply;
    }

    public void setTsupply(String tsupply) {
        this.tsupply = tsupply;
    }

    public String getMsupply() {
        return msupply;
    }

    public void setMsupply(String msupply) {
        this.msupply = msupply;
    }

    public static ArrayList<Crypto> getCryptoCurrencies() {
        ArrayList<Crypto> currencies = new ArrayList<>();
        currencies.add(new Crypto("BTC","Bitcoin","bitcoin",1,"26531.04","0.72","0.15","-1.78","1.00","514453255891.73",9132544844.26387,13094690453.92319,"19390615.00","19390615","21000000"));
        currencies.add(new Crypto("ETH","Ethereum", "ethereum", 2,"1839.96","0.19","0.14","-1.85","0.069358","225166036445.31",4218726862.1129327,5263481401.962947, "122375302.00","122375302",""));
        currencies.add(new Crypto("USDT","Tether","tether",3,"1.00", "0.13","0.08", "0.17","0.000038","68404671800.37", 16567785501.13163,22907281853.669888,"68319442037.00","73141766321.234",""));
        currencies.add(new Crypto("BNB","Binance Coin","binance-coin",4,"259.83","0.20","-0.37","-14.98","0.009794","43340477980.66",422341982.7054159,809248600.1687386,"166801148.00","192443301","200000000"));
        currencies.add(new Crypto("USDC","USD Coin","usd-coin", 5, "0.999965", "0.00", "0.01", "0.03", "0.000038", "41299437198.98",899739870.9017171,1112386527.8138921, "41300868946.00", "41300868946.621", ""));
        currencies.add(new Crypto("XRP","XRP","ripple",6,"0.523878","0.96","-0.22","3.20","0.000020","22479356435.34",759884200.087552,1213997187.6931505,"42909539227.00","99991841593","100000000000"));
        currencies.add(new Crypto("BUSD","Binance USD","binance-usd",7,"0.999803","0.02","0.00","0.02","0.000038","16145731107.97",882269304.1307173,1269337655.236371,"16148914845.00","16148914845.973",""));
        currencies.add(new Crypto("ADA","Cardano","cardano",8,"0.317272","-2.13","-0.46","-13.51","0.000012","10984634620.40",172266139.55350745,277280467.29432404,"34622161734.00","35467426506.415","45000000000"));
        currencies.add(new Crypto("DOGE","Dogecoin","dogecoin",9,"0.067973","0.51","0.02","-5.79","0.000003","9488773162.83",127896692.9516128,218266298.60235,"139596546384.00","139596546384",""));
        currencies.add(new Crypto("TRX","TRON","tron",10,"0.077616","1.17","0.28","3.20","0.000003","7114984432.31",165954869.45410913,195690199.8632755,"91668462019.00","100000000000",""));

        return currencies;
    }

    // To-Do
    // Implement findCrypto method
    public static Crypto findCrypto(String symbol) {
        ArrayList<Crypto> currencies = Crypto.getCryptoCurrencies();
        for (final Crypto crypto : currencies) {
            if (crypto.getSymbol().toLowerCase().equals(symbol.toLowerCase())) {
                return crypto;
            }
        }
        return null;
    }

}
