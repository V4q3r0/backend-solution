package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.mappers.MappersUtils;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateQuestionUseCase implements SaveQuestion{

    private final QuestionRepository questionRepository;
    private final MappersUtils mappersUtils;

    public CreateQuestionUseCase(QuestionRepository questionRepository, MappersUtils mappersUtils){
        this.questionRepository = questionRepository;
        this.mappersUtils = mappersUtils;
    }

    @Override
    public Mono<String> apply(QuestionDTO questionDTO) {
        return questionRepository
                .save(mappersUtils.mapperToQuestion(null).apply(questionDTO))
                .map(Question::getId);
    }
}
