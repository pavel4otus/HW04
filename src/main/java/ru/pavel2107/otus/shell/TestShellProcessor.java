package ru.pavel2107.otus.shell;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.pavel2107.otus.domain.Question;
import ru.pavel2107.otus.service.TestService;

@ShellComponent
public class TestShellProcessor {

    private TestService testService;


    @Autowired
    public TestShellProcessor( TestService testService){
        this.testService = testService;
    }

    @ShellMethod( value = "Start test", key ="start")
    public void startTest(){
        testService.init();
    }

    @ShellMethod( value = "Enter student's name and surname", key = "student")
    public void enterStudent(@ShellOption String name, @ShellOption String surname){
        testService.inviteStudent( name, surname);

    }

    @ShellMethod( value = "Quantity", key = "count")
    public String countQuestions(){
        return "Count of questions: " + testService.countQuestions();
    }

    @ShellMethod( value = "Show Question #", key = "show")
    public void showQuestion( @ShellOption String questionID){
        Question question = testService.findById( questionID);
        testService.showQuestion( question);
    }

    @ShellMethod( value ="Enter answer for test", key = "answer")
    public void setAnswer( @ShellOption String questionID, @ShellOption int answerNum){
        testService.setAnswer( questionID, answerNum);
    }

    @ShellMethod( value = "Show results and exit", key = "result")
    public void stop(){
        testService.showTestResult();
        System.exit(0);
    }

}
