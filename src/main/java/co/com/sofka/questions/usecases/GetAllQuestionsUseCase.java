package co.com.sofka.questions.usecases;

import co.com.sofka.questions.mappers.MappersUtils;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class GetAllQuestionsUseCase implements Supplier<Flux<QuestionDTO>> {

    private final QuestionRepository questionRepository;
    private final MappersUtils mappersUtils;

    public GetAllQuestionsUseCase(QuestionRepository questionRepository, MappersUtils mappersUtils) {
        this.questionRepository = questionRepository;
        this.mappersUtils = mappersUtils;
    }

    @Override
    public Flux<QuestionDTO> get() {
        return questionRepository.findAll()
                .map(mappersUtils.mapEntityToQuestion());
    }
}
