package co.com.sofka.questions.usecases;

import co.com.sofka.questions.mappers.MappersUtils;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class GetQuestionByCategoryUseCase implements Function<String, Flux<QuestionDTO>> {

    private final QuestionRepository questionRepository;
    private final MappersUtils mappersUtils;

    public GetQuestionByCategoryUseCase(QuestionRepository questionRepository, MappersUtils mappersUtils) {
        this.questionRepository = questionRepository;
        this.mappersUtils = mappersUtils;
    }

    @Override
    public Flux<QuestionDTO> apply(String category) {
        Objects.requireNonNull(category, "La categoria es requerida");
        return questionRepository.findByCategory(category)
                .map(mappersUtils.mapEntityToQuestion());
    }
}
