package ru.unn.agile.CurrencyConverter.Provider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class File_XMLSource implements IXMLSource {
    private String fileName;

    public File_XMLSource(String fileName) {
        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("FileName can't be empty");
        }

        this.fileName = fileName;
    }

    @Override
    public InputStream getXML() {
        InputStream fileStream = null;
        try {
            fileStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't open file: " + fileName);
        }
        return fileStream;
    }
}
