package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.collections.User;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.reposioties.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class CreateUserUseCaseTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Test
    @DisplayName("Create Question Test")
    void createUser(){
        var dato1 = new User();
        dato1.setId("01");
        dato1.setName("Pepito");
        dato1.setEmail("ejemplo@gmail.com");
        dato1.setPassword("123");
        dato1.setPhotoURL("imagen.com");

        var dato2 = new UserDTO();
        dato2.setName("Pepito");
        dato2.setEmail("ejemplo@gmail.com");
        dato2.setPassword("123");
        dato2.setPhotoURL("imagen.com");

        Mockito.when(userRepository.save(any())).thenReturn(Mono.just(dato1));

        StepVerifier.create(createUserUseCase.apply(dato2))
                .expectNext(dato1.getId())
                .verifyComplete();
    }

}