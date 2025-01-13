package cafeboard.Member.Dto;

public record CreateMember(
        String name,
        String password,
        String userId
) {
}
