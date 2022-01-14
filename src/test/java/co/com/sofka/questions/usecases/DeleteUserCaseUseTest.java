package co.com.sofka.questions.usecases;

import co.com.sofka.questions.reposioties.UserRepository;
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
class DeleteUserCaseUseTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private DeleteUserCaseUse deleteUserCaseUse;

    @Test
    @DisplayName("Delete User Test")
    void deleteQuestion(){
        Mockito.when(userRepository.deleteById(anyString())).thenReturn(Mono.empty());

        StepVerifier.create(deleteUserCaseUse.apply("01"))
                .verifyComplete();
    }

}