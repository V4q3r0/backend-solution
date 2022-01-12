package co.com.sofka.questions.usecases;

import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class DeleteQuestionUseCaseTest {

    @MockBean
    private QuestionRepository questionRepository;

    @Autowired
    private DeleteQuestionUseCase deleteQuestionUseCase;

   /* @Test
    @DisplayName("DeleteUseCase Test")
    void deleteQuestion(){
        Mockito.when(questionRepository.deleteById(anyString())).thenReturn(Mono.empty());

        var resultado = deleteQuestionUseCase.apply("01");

        Assertions.assertEquals(null, resultado.block());
    }*/

}