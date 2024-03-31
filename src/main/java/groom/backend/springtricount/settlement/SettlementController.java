package groom.backend.springtricount.settlement;

import groom.backend.springtricount.annotation.Login;
import groom.backend.springtricount.member.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/settlements")
public class SettlementController {
    private final SettlementService settlementService;

    @GetMapping("")
    public List<SettlementDto> getAll(@Login MemberDto member){
        return settlementService.findAll(member);
    }
}
