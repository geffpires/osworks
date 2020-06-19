package com.geff.osworks.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Problem {

    private Integer status;
    private LocalDateTime dataHora;
    private String title;
    private List<FieldProblem> fieldProblemList;

}
