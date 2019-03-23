package ru.pavel2107.otus.repository;

import ru.pavel2107.otus.domain.Question;

import java.util.Collection;
import java.util.List;

public interface QuestionRepository {
    void clear();
    void init();
    Question save( Question question);

    Question findById( String id);

    Collection<Question> getAll();
}
