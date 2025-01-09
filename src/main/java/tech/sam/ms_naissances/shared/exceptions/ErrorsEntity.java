package tech.sam.ms_naissances.shared.exceptions;

import java.time.LocalDateTime;

public record ErrorsEntity(
        LocalDateTime time,
        int status,
        String code,
        String message
) {
}
