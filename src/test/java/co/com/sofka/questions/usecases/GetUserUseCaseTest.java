package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.mappers.MappersUtils;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class GetUserUseCaseTest {

    @MockBean
    private QuestionRepository questionRepository;

    @Autowired
    private GetQuestionUseCase getQuestionUseCase;
    @Autowired
    private MappersUtils mapperUtils;

    @Test
    @DisplayName("GetQuestion Test")
    void getQuestion() {
        var dato1 = new Question();
        dato1.setId("01");
        dato1.setCategory("Ciencia");
        dato1.setType("Debate");
        dato1.setUserId("user01");
        dato1.setQuestion("¿Ejemplo de pregunta?");

        Mockito.when(questionRepository.findById("01")).thenReturn(Mono.just(dato1));

        StepVerifier.create(getQuestionUseCase.apply(dato1.getId()))
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getId().equals(dato1.getId());
                    assert questionDTO.getQuestion().equals(dato1.getQuestion());
                    assert questionDTO.getCategory().equals(dato1.getCategory());
                    assert questionDTO.getType().equals(dato1.getType());
                    assert questionDTO.getUserId().equals(dato1.getUserId());
                    return true;
                });

    }

}