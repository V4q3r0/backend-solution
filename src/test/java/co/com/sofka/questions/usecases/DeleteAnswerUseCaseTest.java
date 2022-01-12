package co.com.sofka.questions.usecases;

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

    /*@Test
    @DisplayName("DeleteUseCase Test")
    void deleteQuestion(){
        Mockito.when(answerRepository.deleteById(anyString())).thenReturn(Mono.empty());

        var resultado = deleteAnswerUseCase.apply("01");

        Assertions.assertEquals(null, resultado.block());
    }*/

}