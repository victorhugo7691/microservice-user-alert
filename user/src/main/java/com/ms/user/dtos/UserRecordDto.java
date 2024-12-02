package com.ms.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(@NotBlank(message = "O nome deve ser informado") String nome,
                            @NotBlank(message = "O email deve ser informado") @Email String email) {
}
