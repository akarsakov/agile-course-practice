package ru.unn.agile.CurrencyConverter.Provider;

import java.io.InputStream;
import java.net.URL;

public class CBR_XMLSource implements IXMLSource {
    private static final String CBR_URL = "http://www.cbr.ru/scripts/XML_daily.asp";

    @Override
    public InputStream getXML() {
        try {
            URL cbr = new URL(CBR_URL);
            return cbr.openStream();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't load XML from this URL: " + CBR_URL);
        }
    }
}
