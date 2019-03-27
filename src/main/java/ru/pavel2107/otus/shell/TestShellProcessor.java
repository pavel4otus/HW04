package ru.pavel2107.otus.shell;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.pavel2107.otus.domain.Question;
import ru.pavel2107.otus.service.TestService;

@ShellComponent
public class TestShellProcessor {

    private TestService testService;
    private boolean initialized    = false;
    private boolean studentInvited = false;


    @Autowired
    public TestShellProcessor( TestService testService){
        this.testService = testService;
    }

    @ShellMethod( value = "Start test", key ="start")
    public void startTest(){
        initialized = testService.init();
    }

    @ShellMethod( value = "Enter student's name and surname", key = "student")
    @ShellMethodAvailability( "isInitialized")
    public void enterStudent(@ShellOption String name, @ShellOption String surname){
        studentInvited = testService.inviteStudent( name, surname);
    }

    @ShellMethod( value = "Quantity", key = "count")
    @ShellMethodAvailability( "isInitialized and isInvited")
    public String countQuestions(){
        return "Count of questions: " + testService.countQuestions();
    }

    @ShellMethod( value = "Show Question #", key = "show")
    @ShellMethodAvailability( "isInvited")
    public void showQuestion( @ShellOption String questionID){
        Question question = testService.findById( questionID);
        testService.showQuestion( question);
    }

    @ShellMethod( value ="Enter answer for test", key = "answer")
    @ShellMethodAvailability( "isInvited")
    public void setAnswer( @ShellOption String questionID, @ShellOption int answerNum){
        testService.setAnswer( questionID, answerNum);
    }

    @ShellMethod( value = "Show results and exit", key = "result")
    @ShellMethodAvailability( "isInvited")
    public void stop(){
        testService.showTestResult();
        System.exit(0);
    }



    public Availability isInitialized(){
        return initialized
                ? Availability.available()
                : Availability.unavailable( "Test is not started");
    }

    public Availability isInvited(){
        if( initialized) {
            return studentInvited
                    ? Availability.available()
                    : Availability.unavailable("Student not invited");
        } else {
            return Availability.unavailable( "Test is not initialized");
        }
    }



}
