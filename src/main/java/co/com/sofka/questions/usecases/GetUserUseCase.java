package co.com.sofka.questions.usecases;

import co.com.sofka.questions.mappers.MappersUtils;
import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.reposioties.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class GetUserUseCase implements Function<String, Mono<UserDTO>> {

    private final UserRepository userRepository;
    private final MappersUtils mappersUtils;

    public GetUserUseCase(UserRepository userRepository, MappersUtils mappersUtils) {
        this.userRepository = userRepository;
        this.mappersUtils = mappersUtils;
    }

    @Override
    public Mono<UserDTO> apply(String id) {
        return userRepository.findById(id)
                .map(mappersUtils.mapEntityToUser());
    }
}
