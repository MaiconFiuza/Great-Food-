package fiap.postech.challenge.fase.one.dtos;

import java.util.List;

public record ValidationErrorDTO(List<String> errors, int status) {
}
