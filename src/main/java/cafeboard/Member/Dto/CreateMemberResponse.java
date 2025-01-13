package cafeboard.Member.Dto;

public record CreateMemberResponse(
        String name,
        String password,
        String userId
) {
}
