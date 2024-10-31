package org.example.sevrice.quizService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.QuizResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizCreaterService {

    private final RestClient restClientDataBase;
    private static String URI = "/test";
    private static String URI_THEMES = "/all_themes";

    public void createQuiz() {
        QuizResponseDTO testQuiz;
        try {
            testQuiz = restClientDataBase.get().uri(URI).retrieve().body(QuizResponseDTO.class);
//            ResponseEntity<QuizResponseDTO> testQuiz = restClientDataBase.get().uri(URI).retrieve().body(ResponseEntity.class);
            log.info("new quiz is {}", testQuiz.toString());
        } catch (Exception e) {
            log.warn("exception in create quiz");
        }

    }

    public List<String> getAllThemes() {
        List<String> body = null;
        try {
            body = restClientDataBase.get().uri(URI_THEMES).retrieve().body(List.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return body;
    }

}
