package ru.unn.agile.CurrencyConverter.Provider;

import java.io.IOException;
import java.io.InputStream;

public interface IXMLSource {
    InputStream getXML() throws IOException;
}
