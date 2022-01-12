package co.com.sofka.questions.usecases;

import co.com.sofka.questions.mappers.MappersUtils;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class AddPositionToAnswerUseCase implements Function<String, Mono<AnswerDTO>> {

    private final AnswerRepository answerRepository;
    private final MappersUtils mappersUtils;

    public AddPositionToAnswerUseCase(AnswerRepository answerRepository, MappersUtils mappersUtils) {
        this.answerRepository = answerRepository;
        this.mappersUtils = mappersUtils;
    }

    @Override
    public Mono<AnswerDTO> apply(String id) {
        Objects.requireNonNull(id, "Id es requerida");
        var answer = answerRepository.findById(id).block();
        answer.setPosition(answer.getPosition()+1);
        return answerRepository.save(answer)
                .map(mappersUtils.mapEntityToAnswer());
    }
}
