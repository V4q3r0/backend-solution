package co.com.sofka.questions.usecases;

import co.com.sofka.questions.mappers.MappersUtils;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class AddAnswerUseCase implements SaveAnswer {

    private final AnswerRepository answerRepository;
    private final MappersUtils mappersUtils;
    private final GetQuestionUseCase getQuestionUseCase;

    public AddAnswerUseCase(AnswerRepository answerRepository, MappersUtils mappersUtils, GetQuestionUseCase getQuestionUseCase) {
        this.answerRepository = answerRepository;
        this.mappersUtils = mappersUtils;
        this.getQuestionUseCase = getQuestionUseCase;
    }

    @Override
    public Mono<QuestionDTO> apply(AnswerDTO answerDTO) {
        Objects.requireNonNull(answerDTO.getQuestionId(), "Id de la pregunta es requerida");
        return getQuestionUseCase.apply(answerDTO.getQuestionId())
                .flatMap(questionDTO ->
                        answerRepository.save(mappersUtils.mapperToAnswer().apply(answerDTO))
                                .map(answer -> {
                                   questionDTO.getAnswers().add(answerDTO);
                                   return questionDTO;
                                })
                );
    }
}
