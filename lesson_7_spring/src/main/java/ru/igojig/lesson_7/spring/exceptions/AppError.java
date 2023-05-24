package ru.igojig.lesson_7.spring.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppError {
    private int statusCode;
    private String message;
}
