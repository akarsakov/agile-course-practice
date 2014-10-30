package ru.unn.agile.CurrencyConverter.Provider;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class URL_XMLSource implements IXMLSource {
    private final String url;

    public URL_XMLSource(String url) {
        if (url == null) {
            throw new IllegalArgumentException("url can't be null");
        }

        this.url = url;
    }

    @Override
    public InputStream getXML() throws IOException {
        URL cbr = new URL(url);
        return cbr.openStream();
    }
}