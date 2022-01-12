package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
class GetQuestionOwnerUseCaseTest {

    @MockBean
    private QuestionRepository questionRepository;

    @Autowired
    private GetQuestionOwnerUseCase getQuestionOwnerUseCase;

    @Test
    @DisplayName("Owner Questions Test")
    void OwnerList(){
        var dato1 = new Question();
        dato1.setId("01");
        dato1.setCategory("Ciencia");
        dato1.setType("Debate");
        dato1.setUserId("user01");
        dato1.setQuestion("¿Ejemplo de pregunta?");

        var dato2 = new Question();
        dato2.setId("02");
        dato2.setCategory("Ciencia");
        dato2.setType("Debate");
        dato2.setUserId("user01");
        dato2.setQuestion("¿Ejemplo de pregunta 2?");

        Mockito.when(questionRepository.findByUserId("user01")).thenReturn(Flux.just(dato1, dato2));

        StepVerifier.create(questionRepository.findByUserId("user01"))
                .expectNextCount(2)
                .verifyComplete();
    }


}