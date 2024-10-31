package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuizRequestDTO {
    private String title;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String rightAnswer;
    private String description;
}
