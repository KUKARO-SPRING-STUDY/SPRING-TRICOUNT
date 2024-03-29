package groom.backend.springtricount.member;

public record MemberDto(
        Long id,
        String loginId,
        String name
) {
}
