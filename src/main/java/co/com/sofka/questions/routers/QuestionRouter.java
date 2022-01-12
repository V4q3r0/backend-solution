package co.com.sofka.questions.routers;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class QuestionRouter {

    @Bean
    public RouterFunction<ServerResponse> createQuestion(CreateQuestionUseCase createQuestionUseCase) {
        return route(
                POST("/createQuestion").and(accept(MediaType.APPLICATION_JSON)),
                serverRequest -> serverRequest.bodyToMono(QuestionDTO.class)
                        .flatMap(questionDTO ->  createQuestionUseCase.apply(questionDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result)))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getQuestion(GetQuestionUseCase getQuestionUseCase) {
        return route(
                GET("/getQuestion/{id}").and(accept(MediaType.APPLICATION_JSON)),
                serverRequest -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getQuestionUseCase.apply(
                                serverRequest.pathVariable("id")),
                                QuestionDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getQuestions(GetAllQuestionsUseCase getAllQuestionsUseCase) {
        return route(
                GET("/getQuestions").and(accept(MediaType.APPLICATION_JSON)),
                serverRequest -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllQuestionsUseCase.get(), QuestionDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getQuestionsByOwner(GetQuestionOwnerUseCase getQuestionOwnerUseCase) {
        return route(
            GET("/getQuestionsByOwner/{userId}").and(accept(MediaType.APPLICATION_JSON)),
            serverRequest -> ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromPublisher(
                            getQuestionOwnerUseCase.apply(serverRequest.pathVariable("userId")),
                            QuestionDTO.class
                    ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getQuestionsByType(GetQuestionByTypeUseCase getQuestionByTypeUseCase) {
        return route(
                GET("/getQuestionsByType/{type}").and(accept(MediaType.APPLICATION_JSON)),
                serverRequest -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getQuestionByTypeUseCase.apply(serverRequest.pathVariable("type")),
                                QuestionDTO.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getQuestionsByCategory(GetQuestionByCategoryUseCase getQuestionByCategoryUseCase) {
        return route(
                GET("/getQuestionsByCategory/{category}").and(accept(MediaType.APPLICATION_JSON)),
                serverRequest -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getQuestionByCategoryUseCase.apply(serverRequest.pathVariable("category")),
                                QuestionDTO.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> deleteQuestion(DeleteQuestionUseCase deleteQuestionUseCase) {
        return route(
                DELETE("/deleteQuestion/{id}").and(accept(MediaType.APPLICATION_JSON)),
                serverRequest -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                deleteQuestionUseCase.apply(serverRequest.pathVariable("id")),
                                Void.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> addAnswer(AddAnswerUseCase addAnswerUseCase) {
        return route(
                POST("/addAnswer").and(accept(MediaType.APPLICATION_JSON)),
                serverRequest -> serverRequest.bodyToMono(AnswerDTO.class)
                        .flatMap(answerDTO -> addAnswerUseCase.apply(answerDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> addAnswerToAnswer(AddAnswerToAnswerUseCase addAnswerToAnswerUseCase) {
        return route(
                POST("/addAnswerToAnswer").and(accept(MediaType.APPLICATION_JSON)),
                serverRequest -> serverRequest.bodyToMono(AnswerDTO.class)
                        .flatMap(answerDTO -> addAnswerToAnswerUseCase.apply(answerDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> addPositionToAnswer(AddPositionToAnswerUseCase addPositionToAnswerUseCase) {
        return route(
                POST("/addPositionToAnswer/{id}").and(accept(MediaType.APPLICATION_JSON)),
                serverRequest -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                addPositionToAnswerUseCase.apply(serverRequest.pathVariable("id")),
                                AnswerDTO.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> deleteAnswer(DeleteAnswerUseCase deleteAnswerUseCase) {
        return route(
                DELETE("/deleteAnswer/{id}").and(accept(MediaType.APPLICATION_JSON)),
                serverRequest -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                deleteAnswerUseCase.apply(serverRequest.pathVariable("id")),
                                Void.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> addUser(CreateUserUseCase createUserUseCase) {
        return route(
                POST("/createUser").and(accept(MediaType.APPLICATION_JSON)),
                serverRequest -> serverRequest.bodyToMono(UserDTO.class)
                        .flatMap(questionDTO ->  createUserUseCase.apply(questionDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result)))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getUser(GetUserUseCase getUserUseCase) {
        return route(
                GET("/gerUser/{id}").and(accept(MediaType.APPLICATION_JSON)),
                serverRequest ->  ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getUserUseCase.apply(serverRequest.pathVariable("id")),
                                UserDTO.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getUsers(GetAllUsersUseCase getAllUsersUseCase) {
        return route(
                GET("/getUsers").and(accept(MediaType.APPLICATION_JSON)),
                serverRequest -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllUsersUseCase.get(), UserDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> deleteUser(DeleteUserCaseUse deleteUserCaseUse) {
        return route(
                DELETE("/deleteUser/{id}").and(accept(MediaType.APPLICATION_JSON)),
                serverRequest -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                deleteUserCaseUse.apply(serverRequest.pathVariable("id")),
                                Void.class
                        ))
        );
    }

}
