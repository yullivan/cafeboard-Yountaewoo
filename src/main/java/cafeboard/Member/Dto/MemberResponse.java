package cafeboard.Member.Dto;

public record MemberResponse(
        String name,
        String password,
        String userId
) {
}
