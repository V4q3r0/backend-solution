package co.com.sofka.questions.usecases;

import co.com.sofka.questions.model.UserDTO;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface GetUsers {

    Flux<UserDTO> get();
}
