package groom.backend.springtricount.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<MemberDto> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(memberEntity -> new MemberDto(memberEntity.id(), memberEntity.loginId(), memberEntity.name()))
                .toList();
    }
}
