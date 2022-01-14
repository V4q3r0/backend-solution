package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.reposioties.AnswerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class DeleteAnswerUseCaseTest {

    @MockBean
    private AnswerRepository answerRepository;

    @Autowired
    private DeleteAnswerUseCase deleteAnswerUseCase;

    @Test
    @DisplayName("Delete Answer Test")
    void deleteAnswer(){

        Mockito.when(answerRepository.deleteById("01")).thenReturn(Mono.empty());
        Mockito.when(answerRepository.deleteByQuestionId("01")).thenReturn(Mono.empty());

        StepVerifier.create(deleteAnswerUseCase.apply("01"))
                .verifyComplete();
    }

}