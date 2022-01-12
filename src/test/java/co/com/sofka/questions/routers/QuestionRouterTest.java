package co.com.sofka.questions.routers;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.mappers.MappersUtils;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.usecases.CreateQuestionUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {QuestionRouter.class ,CreateQuestionUseCase.class, MappersUtils.class})
class QuestionRouterTest {

    @MockBean
    private QuestionRepository questionRepository;

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private MappersUtils mappersUtils;

    @Test
    public void testCreateQuestion() {
        Question question = new Question();
        question.setId("01");
        question.setType("Opinion");
        question.setCategory("Ciencias");
        question.setQuestion("¿Pregunta?");
        question.setUserId("user01");

        QuestionDTO questionDTO = new QuestionDTO();

        questionDTO = mappersUtils.mapEntityToQuestion().apply(question);

        Mockito.when(questionRepository.save(any())).thenReturn(Mono.just(question));

        webTestClient.post()
                .uri("/createQuestion")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(questionDTO, QuestionDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(questionResponse -> {
                    Assertions.assertEquals(questionResponse, question.getId());
                });
    }

}