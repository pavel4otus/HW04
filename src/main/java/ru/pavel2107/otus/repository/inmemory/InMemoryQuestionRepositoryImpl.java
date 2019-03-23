package ru.pavel2107.otus.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.pavel2107.otus.config.ApplicationProperties;
import ru.pavel2107.otus.domain.Question;
import ru.pavel2107.otus.repository.QuestionRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class InMemoryQuestionRepositoryImpl implements QuestionRepository {

    private Map<String, Question> map = new ConcurrentHashMap<>();
    private String fileName;

    InMemoryQuestionRepositoryImpl( ApplicationProperties properties){
        fileName = properties.getFilename();
    }


    @Override
    public void clear(){
        map.clear();
    }

    @Override
    public Question save(Question question) {
        return map.put( question.getId(), question);
    }

    @Override
    public Collection<Question> getAll() {
        return map.values();
    }

    @Override
    public Question findById(String id) {
        return map.get( id);
    }

    public void init(){

        ClassLoader classLoader = InMemoryQuestionRepositoryImpl.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                if (!line.startsWith("#")) {
                    String elements[] = line.split(";");
                    String id = elements[0];
                    String question = elements[1];
                    int rightAnswer = Integer.parseInt(elements[2]);
                    List<String> answer = new ArrayList<>();
                    for (int i = 3; i < elements.length; i++) {
                        answer.add(elements[i]);
                    }
                    Question q = new Question(id, question, answer, rightAnswer);
                    save(q);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
