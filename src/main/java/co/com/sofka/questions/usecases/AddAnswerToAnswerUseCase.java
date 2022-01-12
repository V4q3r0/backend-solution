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
public class AddAnswerToAnswerUseCase implements Function<AnswerDTO, Mono<AnswerDTO>> {

    private final AnswerRepository answerRepository;
    private final MappersUtils mappersUtils;

    public AddAnswerToAnswerUseCase(AnswerRepository answerRepository, MappersUtils mappersUtils) {
        this.answerRepository = answerRepository;
        this.mappersUtils = mappersUtils;
    }

    @Override
    public Mono<AnswerDTO> apply(AnswerDTO answerDTO) {
        Objects.requireNonNull(answerDTO.getQuestionId(), "Id de repuesta es obligatoria");
        return answerRepository.save(mappersUtils.mapperToAnswer().apply(answerDTO))
                .map(mappersUtils.mapEntityToAnswer());
    }
}
