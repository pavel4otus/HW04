package ru.pavel2107.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;
import ru.pavel2107.otus.config.ApplicationProperties;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DisplayName( "Транслятор")
class TranslatorServiceImplTest {


    @Autowired
    TranslatorService translatorService;

    @Test
    @DisplayName( "Проверка вывода без параметра")
    void checkTranslateWithoutParameter() {
        String str = translatorService.translate( "hello.user");
        assertEquals  ( "Hello, student!", str );

    }

    @Test
    @DisplayName( "Проверка вывода c параметром")
    void checkTranslateWithParameter() {
        String str = translatorService.translate( "results.answer", new String[]{ "1"} );
        assertEquals( "Your answer is 1", str);
    }
}