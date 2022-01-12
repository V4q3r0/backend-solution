package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.User;
import co.com.sofka.questions.mappers.MappersUtils;
import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.reposioties.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateUserUseCase implements SaveUser{

    private final UserRepository userRepository;
    private final MappersUtils mappersUtils;

    public CreateUserUseCase(UserRepository userRepository, MappersUtils mappersUtils) {
        this.userRepository = userRepository;
        this.mappersUtils = mappersUtils;
    }

    @Override
    public Mono<String> apply(UserDTO userDTO) {
        return userRepository.save(mappersUtils.mapperToUser().apply(userDTO))
                .map(User::getId);
    }
}
