package groom.backend.springtricount.member;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record MemberRequest(
        Long id,
        String loginId,
        String name,
        String password
) {
}
