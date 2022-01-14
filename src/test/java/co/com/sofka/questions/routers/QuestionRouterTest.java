package co.com.sofka.questions.routers;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class QuestionRouterTest {


    @MockBean
    private QuestionRepository questionRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testCreateQuestion() {
        Question question = new Question();
        question.setId("01");
        question.setType("Opinion");
        question.setCategory("Ciencias");
        question.setQuestion("¿Pregunta?");
        question.setUserId("user01");

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId("01");
        questionDTO.setType("Opinion");
        questionDTO.setCategory("Ciencias");
        questionDTO.setQuestion("¿Pregunta?");
        questionDTO.setUserId("user01");

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

    @Test
    void getQuestionTest(){
        Question question = new Question();
        question.setId("01");
        question.setType("Opinion");
        question.setCategory("Ciencias");
        question.setQuestion("¿Pregunta?");
        question.setUserId("user01");

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId("01");
        questionDTO.setType("Opinion");
        questionDTO.setCategory("Ciencias");
        questionDTO.setQuestion("¿Pregunta?");
        questionDTO.setUserId("user01");

        Mockito.when(questionRepository.findById(anyString())).thenReturn(Mono.just(question));

        webTestClient.get()
                .uri("/getQuestion/{id}", "01")
                .exchange()
                .expectStatus().isOk()
                .expectBody(QuestionDTO.class)
                .value(questionDTO1 -> {
                   questionDTO1.getId().equals(question.getId());
                });
    }

}