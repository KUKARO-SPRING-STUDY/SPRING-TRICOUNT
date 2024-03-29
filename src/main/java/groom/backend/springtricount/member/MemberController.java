package groom.backend.springtricount.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
