package ru.unn.agile.CurrencyConverter.Provider;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import ru.unn.agile.CurrencyConverter.Model.Currency;

public class XMLCurrencyProvider implements ICurrencyProvider {
    private IXMLSource transport;

    public XMLCurrencyProvider(IXMLSource transport) {
        setXMLTransport(transport);
    }

    public void setXMLTransport(IXMLSource transport) {
        if (transport == null) {
            throw new IllegalArgumentException("Transport can't be null");
        }

        this.transport = transport;
    }

    @Override
    public final Currency[] getActualCurrency() {
        List<Currency> actualCurrency = new ArrayList<Currency>();

        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputStream is = transport.getXML();
            Document doc = db.parse(is);

            NodeList nList = doc.getElementsByTagName("Valute");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    int numCode = Integer.parseInt(
                            eElement.getElementsByTagName("NumCode").item(0).getTextContent());
                    String charCode =
                            eElement.getElementsByTagName("CharCode").item(0).getTextContent();
                    int nominal = Integer.parseInt(
                            eElement.getElementsByTagName("Nominal").item(0).getTextContent());
                    String name =
                            eElement.getElementsByTagName("Name").item(0).getTextContent();
                    double value = Double.parseDouble(
                            eElement.getElementsByTagName("Nominal").item(0).getTextContent());

                    actualCurrency.add(new Currency(numCode, charCode, name, nominal, value));
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("Can't get actual courses from current transport");
        }
        // RUB currency doesn't contains in XML, let's add it by hand
        actualCurrency.add(new Currency(1, "RUB", "Российский рубль", 1, 1));

        return actualCurrency.toArray(new Currency[actualCurrency.size()]);
    }

        public static class Builder {
        private IXMLSource transport = new CBR_XMLSource();

        public Builder XMLSource(IXMLSource transport) {
            this.transport = transport;
            return this;
        }

        public XMLCurrencyProvider build() {
            return new XMLCurrencyProvider(transport);
        }
    }
}
