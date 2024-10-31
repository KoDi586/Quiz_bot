package org.example.controller;

import org.example.dto.QuizRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class NewController {

    @GetMapping("/test")
    public ResponseEntity<QuizRequestDTO> getNewQuiz() {
        return ResponseEntity.ok(new QuizRequestDTO(
                "IT program",
                "answer1",
                "answer2",
                "answer3",
                "answer4",
                "3",
                "des"));


    }

    @GetMapping("/all_themes")
    public ResponseEntity<List<String>> getAllThemes() {
        return ResponseEntity.ok(Arrays.asList("IT program", "other"));
    }
}
