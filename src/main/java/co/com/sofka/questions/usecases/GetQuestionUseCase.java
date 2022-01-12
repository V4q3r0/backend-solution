package co.com.sofka.questions.usecases;

import co.com.sofka.questions.mappers.MappersUtils;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class GetQuestionUseCase implements Function<String, Mono<QuestionDTO>> {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final MappersUtils mappersUtils;

    public GetQuestionUseCase(QuestionRepository questionRepository, AnswerRepository answerRepository, MappersUtils mappersUtils){
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.mappersUtils = mappersUtils;
    }

    @Override
    public Mono<QuestionDTO> apply(String id) {
        Objects.requireNonNull(id, "Id es requerida");
        return questionRepository.findById(id)
                .map(mappersUtils.mapEntityToQuestion())
                .flatMap(questionDTO ->
                    Mono.just(questionDTO).zipWith(
                            answerRepository.findAllByQuestionId(questionDTO.getId())
                                    .map(mappersUtils.mapEntityToAnswer())
                                    .collectList(),
                            (question, answers) -> {
                                question.setAnswers(answers);
                                return question;
                            }
                    )
                );
    }
}
