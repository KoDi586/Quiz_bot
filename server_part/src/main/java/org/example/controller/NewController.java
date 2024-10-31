package org.example.controller;

import org.example.dto.QuizRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewController {

    @GetMapping("/test")
    public ResponseEntity<QuizRequestDTO> getNewQuiz() {
        return ResponseEntity.ok(new QuizRequestDTO(
                "ldksfjsldkf",
                "ldksfjsldkf",
                "ldksfjsldkf",
                "ldksfjsldkf",
                "ldksfjsldkf",
                "ldksfjsldkf",
                "ldksfjsldkf"));


    }
}
