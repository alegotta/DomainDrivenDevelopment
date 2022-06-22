package it.unibz.ddd.service.database;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertiesReader {
    private final Properties properties;
    private Map<String, String> content;

    public PropertiesReader() {
        this.properties = new Properties();
        InputStream confFile = ClassLoader.getSystemResourceAsStream("database.properties");

        if (confFile == null)
            throw new IllegalArgumentException("File does not exist");
        else
            read(confFile);
    }

    private void read(InputStream stream) {
        try {
            properties.load(stream);
            content = properties.entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            e -> String.valueOf(e.getKey().toString()),
                            e -> String.valueOf(e.getValue())));
        } catch (IOException e) {
            content = new HashMap<>();
            throw new UncheckedIOException(e);
        }
    }

    public Map<String, String> getContent() {
        return content;
    }
}
