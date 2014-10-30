package ru.unn.agile.CurrencyConverter.Model;

public enum CurrencyIndex {
    USD(0, 840),
    EUR(1, 978),
    RUB(2, 1);

    private final int index;
    private final int numCode;

    CurrencyIndex(final int index, final int numCode) {
        this.index = index;
        this.numCode = numCode;
    }

    public final int getIndex() {
        return index;
    }

    public final int getNumCode() {
        return numCode;
    }

    static public final CurrencyIndex getCurrencyIndexByNumCode(final int numCode) {
        for (final CurrencyIndex index : CurrencyIndex.values()) {
            if (index.getNumCode() == numCode)
                return index;
        }
        return null;
    }
}
