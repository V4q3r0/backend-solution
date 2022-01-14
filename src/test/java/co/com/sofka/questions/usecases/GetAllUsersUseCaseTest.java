package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.User;
import co.com.sofka.questions.reposioties.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
class GetAllUsersUseCaseTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private GetAllUsersUseCase getAllUsersUseCase;

    @Test
    @DisplayName("Get All Users Test")
    void getAllUser(){
        var dato1 = new User();
        dato1.setId("user01");
        dato1.setName("Pepito");
        dato1.setEmail("correo@gmail.com");
        dato1.setPassword("12345");
        dato1.setPhotoURL("imagen.com.jpg");

        var dato2 = new User();
        dato2.setId("user02");
        dato2.setName("Juan David");
        dato2.setEmail("estoNoExiste@gmail.com");
        dato2.setPassword("54321");
        dato2.setPhotoURL("imagen_perfil.com.jpg");

        Mockito.when(userRepository.findAll()).thenReturn(Flux.just(dato1, dato2));

        StepVerifier.create(getAllUsersUseCase.get())
                .expectNextMatches(userDTO -> {
                    assert userDTO.getId().equals(dato1.getId());
                    assert userDTO.getName().equals(dato1.getName());
                    assert userDTO.getEmail().equals(dato1.getEmail());
                    assert userDTO.getPassword().equals(dato1.getPassword());
                    assert userDTO.getPhotoURL().equals(dato1.getPhotoURL());
                    return true;
                })
                .expectNextMatches(userDTO -> {
                    assert userDTO.getId().equals(dato2.getId());
                    assert userDTO.getName().equals(dato2.getName());
                    assert userDTO.getEmail().equals(dato2.getEmail());
                    assert userDTO.getPassword().equals(dato2.getPassword());
                    assert userDTO.getPhotoURL().equals(dato2.getPhotoURL());
                    return true;
                })
                .verifyComplete();
    }

}