package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class AddAnswerUseCaseTest {

    @MockBean
    private QuestionRepository questionRepository;
    @MockBean
    private AnswerRepository answerRepository;

    @Autowired
    private GetQuestionUseCase getQuestionUseCase;
    @Autowired
    private AddAnswerUseCase addAnswerUseCase;

    @Test
    @DisplayName("Add Answer Test")
    void addAnswer() {
        var dato1 = new Answer();
        dato1.setId("answer01");
        dato1.setPosition(1);
        dato1.setAnswer("Ejemplo de respuesta");
        dato1.setUserId("user01");
        dato1.setQuestionId("question01");

        var dato3 = new AnswerDTO();
        dato3.setPosition(1);
        dato3.setAnswer("Ejemplo de respuesta");
        dato3.setUserId("user01");
        dato3.setQuestionId("question01");

        var list = new ArrayList<AnswerDTO>();
        list.add(dato3);

        var dato2 = new Question();
        dato2.setId("question01");
        dato2.setType("Nuevas tecnologias");
        dato2.setCategory("Software");
        dato2.setUserId("user01");
        dato2.setQuestion("¿Ejemplo de pregunta 2?");

        Mockito.when(questionRepository.findById(anyString())).thenReturn(Mono.just(dato2));
        Mockito.when(answerRepository.findAllByQuestionId(anyString())).thenReturn(Flux.just(dato1));
        Mockito.when(answerRepository.save(any())).thenReturn(Mono.just(dato1));

        StepVerifier.create(addAnswerUseCase.apply(dato3))
                .expectNextMatches(questionDTO -> {
                    Assertions.assertEquals("Ejemplo de respuesta", questionDTO.getAnswers().get(0).getAnswer());
                    return true;
                })
                .verifyComplete();


    }
}