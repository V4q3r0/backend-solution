package co.com.sofka.questions.usecases;

import co.com.sofka.questions.reposioties.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class DeleteUserCaseUse implements Function<String, Mono<Void>> {

    private final UserRepository userRepository;

    public DeleteUserCaseUse(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<Void> apply(String id) {
        return userRepository.deleteById(id);
    }
}
