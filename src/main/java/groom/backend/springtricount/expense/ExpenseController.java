package groom.backend.springtricount.expense;

import groom.backend.springtricount.annotation.Login;
import groom.backend.springtricount.member.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping("")
    public ExpenseDto create(@Login MemberDto memberDto, ExpenseDto expenseDto) {
        return expenseService.save(expenseDto);
    }

    @GetMapping("")
    public List<ExpenseDto> findAll(@Login MemberDto memberDto) {
        return expenseService.findAll(memberDto);
    }
}
