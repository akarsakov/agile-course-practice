package ru.unn.agile.CurrencyConverter.Provider;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.CurrencyConverter.Model.Currency;
import ru.unn.agile.CurrencyConverter.Model.CurrencyIndex;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class XMLCurrencyProviderTest {
    private ICurrencyProvider provider;

    private static boolean checkCurrencyRatesContainsNumCode(final ArrayList<Currency> currencyRates,
                                                             final int numCode) {
        for (final Currency currency : currencyRates) {
            if (currency.getNumCode() == numCode) {
                return true;
            }
        }
        return false;
    }

    @Before
    public void init() {
        IXMLSource source = new URL_XMLSource("");
        provider = new XMLCurrencyProvider.Builder().build();
    }

    @Test
    public void xmlCurrencyProviderReturnsNotEmptyList() {
        ArrayList<Currency> currencyRates = provider.getActualCurrencyRates();

        assertTrue(currencyRates.size() > 0);
    }

    @Test
    public void xmlCurrencyProviderReturnsAtLeastThreeCurrency() {
        ArrayList<Currency> currencyRates = provider.getActualCurrencyRates();

        assertTrue(currencyRates.size() >= 3);
    }

    @Test
    public void xmlCurrencyProviderReturnsRequiredCurrency() {
        ArrayList<Currency> currencyRates = provider.getActualCurrencyRates();

        for (CurrencyIndex index : CurrencyIndex.values()) {
            assertTrue(checkCurrencyRatesContainsNumCode(currencyRates, index.getNumCode()));
        }
    }

    @Test
    public void xmlCurrencyProviderReturnsUniqueCurrency() {
        ArrayList<Currency> currencyRates = provider.getActualCurrencyRates();

        for (int i = 0; i < currencyRates.size(); i++) {
            for (int j = 0; j < currencyRates.size(); j++) {
                if (i != j) {
                    assertFalse(currencyRates.get(i) == currencyRates.get(j));
                }
            }
        }
    }
}
