package cafeboard.Member.Dto;

public record LogInRequest(
        String password,
        String userId
) {
}
