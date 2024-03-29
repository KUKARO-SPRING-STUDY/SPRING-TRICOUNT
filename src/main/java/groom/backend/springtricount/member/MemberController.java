package groom.backend.springtricount.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class MemberController {
    private final MemberService memberService;


    @GetMapping("/members")
    public List<MemberDto> findAll() {
        return memberService.findAll();
    }

    @PostMapping("/signup")
    public MemberDto signup(@RequestBody MemberRequest memberRequest) {
        MemberDto memberDto = new MemberDto(null, memberRequest.loginId(), memberRequest.name(), memberRequest.password());
        return memberService.signup(memberDto);
    }
}
