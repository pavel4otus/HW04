package ru.pavel2107.otus.service;

import ru.pavel2107.otus.domain.Question;

public interface TestService {
    boolean init();
    boolean inviteStudent();
    boolean inviteStudent( String name, String surname);
    void processTest();
    void showQuestion( Question question);
    void inputAnswer( Question question);
    void setAnswer( String questionID, int answerID);
    void showTestResult();
    int countQuestions();

    Question findById( String id);

}
