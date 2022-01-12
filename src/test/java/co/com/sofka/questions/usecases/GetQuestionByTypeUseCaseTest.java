package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class GetQuestionByTypeUseCaseTest {

    @MockBean
    private QuestionRepository questionRepository;

    @Autowired
    private GetQuestionByTypeUseCase getQuestionByTypeUseCase;

    @Test
    @DisplayName("Get Question By Type Test")
    void getQuestionByType(){
        var dato1 = new Question();
        dato1.setId("01");
        dato1.setCategory("Ciencia");
        dato1.setType("Debate");
        dato1.setUserId("user01");
        dato1.setQuestion("¿Ejemplo de pregunta?");

        Mockito.when(questionRepository.findByType("Debate")).thenReturn(Flux.just(dato1));

        StepVerifier.create(getQuestionByTypeUseCase.apply("Debate"))
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getId().equals(dato1.getId());
                    assert questionDTO.getQuestion().equals(dato1.getQuestion());
                    assert questionDTO.getUserId().equals(dato1.getUserId());
                    assert questionDTO.getType().equals(dato1.getType());
                    return true;
                })
                .verifyComplete();
    }

}