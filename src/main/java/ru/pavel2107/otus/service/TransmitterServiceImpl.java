package ru.pavel2107.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransmitterServiceImpl implements TransmitterService {

    private TranslatorService translatorService;
    private Scanner scanner;

    @Autowired
    public TransmitterServiceImpl( TranslatorService translatorService){
        this.translatorService = translatorService;
        scanner = new Scanner( System.in);
    }

    @Override
    public void printResource(String... args) {
        if( args.length == 1){
            printString( translatorService.translate( args[0]));
        } else {
            String[] list =  Arrays.copyOfRange( args, 1, args.length);
            printString( translatorService.translate( args[0], list));
        }
    }

    @Override
    public void printlnResource(String... args) {
        printResource( args);
        printlnString( "");
    }

    @Override
    public void printString(String str) {
        System.out.print( str);
    }

    @Override
    public void printlnString(String str) {
        System.out.println( str);
    }

    @Override
    public String getString() {
        return scanner.nextLine();
    }

    @Override
    public int getInt() {
        int result;
        while( true){
            try {
                result = scanner.nextInt();
                break;
            }
            catch ( InputMismatchException e){
                printResource( "error");
                scanner.nextLine();
            }
        }
        return result;
    }

}
