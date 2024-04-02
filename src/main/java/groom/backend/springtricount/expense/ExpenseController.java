package groom.backend.springtricount.expense;

import groom.backend.springtricount.annotation.Login;
import groom.backend.springtricount.member.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping("")
    public ExpenseDto create(@Login MemberDto memberDto, @RequestBody ExpenseRequest expenseRequest) {
        ExpenseDto expenseDto = new ExpenseDto(
                null,
                expenseRequest.name(),
                expenseRequest.settlementId(),
                memberDto,
                expenseRequest.amount(),
                LocalDateTime.now()
        );
        return expenseService.save(expenseDto);
    }

    @GetMapping("")
    public List<ExpenseDto> findAll(@Login MemberDto memberDto) {
        return expenseService.findAll(memberDto);
    }
}
