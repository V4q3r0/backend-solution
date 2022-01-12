package co.com.sofka.questions.usecases;

import co.com.sofka.questions.mappers.MappersUtils;
import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.reposioties.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class GetAllUsersUseCase implements GetUsers {

    private final UserRepository userRepository;
    private final MappersUtils mappersUtils;

    public GetAllUsersUseCase(UserRepository userRepository, MappersUtils mappersUtils) {
        this.userRepository = userRepository;
        this.mappersUtils = mappersUtils;
    }

    @Override
    public Flux<UserDTO> get() {
        return userRepository.findAll()
                .map(mappersUtils.mapEntityToUser());
    }
}
