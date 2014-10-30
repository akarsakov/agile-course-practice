package ru.unn.agile.CurrencyConverter.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class CurrencyIndexTest {

    @Test
    public void currencyIndexHasValues() {
        assertTrue(CurrencyIndex.values().length > 0);
    }

    @Test
    public void currencyIndexByNumCodeReturnsNullIfNotFound() {
        CurrencyIndex index = CurrencyIndex.getCurrencyIndexByNumCode(-1);

        assertTrue(index == null);
    }

    @Test
    public void currencyIndexByNumCodeReturnsCorrectResult() {
        CurrencyIndex rubIndex = CurrencyIndex.getCurrencyIndexByNumCode(1);

        assertTrue(rubIndex == CurrencyIndex.RUB);
    }
}
