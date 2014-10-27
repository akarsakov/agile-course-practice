package ru.unn.agile.CurrencyConverter.Provider;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.CurrencyConverter.Model.Currency;
import static org.junit.Assert.*;
import static ru.unn.agile.CurrencyConverter.Model.CurrencyIndexes.*;
import static ru.unn.agile.CurrencyConverter.Provider.FixedCurrencyProviderTest.*;

public class XMLCurrencyProviderTest extends FixedCurrencyProvider {
    private ICurrencyProvider provider;
    private final String fileWithCorrectXML = "correct_currency.xml";
    private final String fileWithIncorrectXML = "incorrect_currency.xml";

    @Before
    public void init() {
        IXMLSource source = new File_XMLSource(fileWithCorrectXML);
        provider = new XMLCurrencyProvider.Builder().XMLSource(source).build();
    }

    @Test
    public void xmlCurrencyProviderReturnsNotEmptyList() {
        Currency[] currencyList = provider.getActualCurrency();

        assertTrue(currencyList.length > 0);
    }

    @Test
    public void xmlCurrencyProviderReturnsAtLeastThreeCurrency() {
        Currency[] currencyList = provider.getActualCurrency();

        assertTrue(currencyList.length >= 3);
    }

    @Test
    public void xmlCurrencyProviderReturnsRequiredCurrency() {
        Currency[] currencyList = provider.getActualCurrency();

        assertTrue(checkCurrencyListContainsSpecificNumCode(currencyList, USD.getNumCode()));
        assertTrue(checkCurrencyListContainsSpecificNumCode(currencyList, EUR.getNumCode()));
        assertTrue(checkCurrencyListContainsSpecificNumCode(currencyList, RUB.getNumCode()));
    }

    @Test
    public void xmlCurrencyProviderReturnsUniqueCurrency() {
        Currency[] currencyList = provider.getActualCurrency();

        for (int i = 0; i < currencyList.length; i++) {
            for (int j = 0; j < currencyList.length; j++) {
                if (i != j) {
                    assertFalse(currencyList[i].isEqual(currencyList[j]));
                }
            }
        }
    }

    //@Test(expected = RuntimeException.class)
    //public void xmlCurrencyProviderThrowsExceptionOnIncorrectXML() {
    //    IXMLSource incorrectSource = new File_XMLSource(fileWithIncorrectXML);
    //    new XMLCurrencyProvider.Builder().XMLSource(incorrectSource).build();
    //}
}
