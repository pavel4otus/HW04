package ru.pavel2107.otus.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.pavel2107.otus.service.TranslatorService;
import ru.pavel2107.otus.service.TranslatorServiceImpl;

import java.util.Locale;

@Configuration
public class TranslatorServiceConfig {


    @Bean
    public TranslatorService translatorService( MessageSource messageSource, ApplicationProperties properties){
        String lang    = properties.getLanguage();
        String country = properties.getCountry();

        Locale locale = new Locale( lang, country);

        TranslatorServiceImpl translatorService = new TranslatorServiceImpl( messageSource, locale);
        return translatorService;
    }



    @Bean
    public MessageSource messageSource( ){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename( "classpath:i18n/bundle");
        messageSource.setDefaultEncoding( "UTF-8");
        return messageSource;
    }
}
