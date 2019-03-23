package ru.pavel2107.otus.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties( prefix = "locale")
public class ApplicationProperties {
    private String language;
    private String country;
    private String filename;


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
