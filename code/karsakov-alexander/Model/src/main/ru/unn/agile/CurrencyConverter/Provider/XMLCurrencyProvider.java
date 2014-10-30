package ru.unn.agile.CurrencyConverter.Provider;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import ru.unn.agile.CurrencyConverter.Model.Currency;

public class XMLCurrencyProvider implements ICurrencyProvider {
    private IXMLSource source;

    public XMLCurrencyProvider(IXMLSource source) {
        setXMLSource(source);
    }

    public void setXMLSource(IXMLSource source) {
        if (source == null) {
            throw new IllegalArgumentException("source can't be null");
        }

        this.source = source;
    }

    @Override
    public final ArrayList<Currency> getActualCurrencyRates() {
        ArrayList<Currency> actualCurrency = new ArrayList<Currency>();

        Document doc;
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputStream is = source.getXML();
            doc = db.parse(is);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return actualCurrency;
        }

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

                    actualCurrency.add(Currency.builder().numCode(numCode).charCode(charCode)
                                               .name(name).nominal(nominal).value(value).build());
                }
            }

        // RUB currency doesn't contains in XML, let's add it by hand
        actualCurrency.add(Currency.builder().numCode(1).charCode("RUB").name("Российский рубль")
                                   .nominal(1).value(1).build());

        return actualCurrency;
    }

    public static class Builder {
        private IXMLSource transport;

        public Builder XMLSource(IXMLSource transport) {
            this.transport = transport;
            return this;
        }

        public XMLCurrencyProvider build() {
            return new XMLCurrencyProvider(transport);
        }
    }
}