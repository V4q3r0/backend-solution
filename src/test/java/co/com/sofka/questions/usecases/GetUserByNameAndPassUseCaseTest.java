package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.User;
import co.com.sofka.questions.reposioties.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class GetUserByNameAndPassUseCaseTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private GetUserByNameAndPassUseCase getUserByNameAndPassUseCase;

    @Test
    @DisplayName("Get User By Name And Password Test")
    void setGetUserByNameAndPass(){
        var dato1 = new User();
        dato1.setId("user01");
        dato1.setName("Pepito");
        dato1.setEmail("correo@gmail.com");
        dato1.setPassword("12345");
        dato1.setPhotoURL("imagen.com.jpg");

        Mockito.when(userRepository.findByName("Pepito")).thenReturn(Mono.just(dato1));

        StepVerifier.create(getUserByNameAndPassUseCase.apply("Pepito", "12345"))
                .expectNextMatches(userDTO -> {
                    assert userDTO.getId().equals(dato1.getId());
                    assert userDTO.getName().equals(dato1.getName());
                    assert userDTO.getEmail().equals(dato1.getEmail());
                    assert userDTO.getPassword().equals(dato1.getPassword());
                    assert userDTO.getPhotoURL().equals(dato1.getPhotoURL());
                    return true;
                })
                .verifyComplete();
    }

}