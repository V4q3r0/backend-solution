package co.com.sofka.questions.usecases;

import co.com.sofka.questions.mappers.MappersUtils;
import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.reposioties.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Service
@Validated
public class GetUserByNameAndPassUseCase implements BiFunction<String, String, Mono<UserDTO>> {

    private final UserRepository userRepository;
    private final MappersUtils mappersUtils;

    public GetUserByNameAndPassUseCase(UserRepository userRepository, MappersUtils mappersUtils) {
        this.userRepository = userRepository;
        this.mappersUtils = mappersUtils;
    }

    @Override
    public Mono<UserDTO> apply(String name, String password) {
        return userRepository.findByName(name)
                .filter(user -> user.getPassword().equals(password))
                .map(mappersUtils.mapEntityToUser());
    }
}
