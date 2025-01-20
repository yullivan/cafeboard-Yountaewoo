package cafeboard.Member.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MemberResquest(
        @NotBlank String name,
        @NotBlank String password,
        @NotBlank String userId
) {
}
