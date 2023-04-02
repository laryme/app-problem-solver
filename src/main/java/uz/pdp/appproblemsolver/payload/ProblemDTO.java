package uz.pdp.appproblemsolver.payload;

import jakarta.validation.constraints.*;
import uz.pdp.appproblemsolver.common.aop.EnumNamePattern;
import uz.pdp.appproblemsolver.entity.enums.ProblemLevel;

public record ProblemDTO(
        @NotBlank(message = "Title is required")
        String title,
        @NotBlank(message = "Description is required")
        String description,
        @Size(min = 0, max = 2, message = "Please enter valid id")
        Integer problemLevelOrdinal,
        @NotNull(message = "Category is required")
        @Positive(message = "Please provide valid value")
        Integer categoryId
) {
}
