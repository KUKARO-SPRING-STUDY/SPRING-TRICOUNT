package groom.backend.springtricount.expense;

import groom.backend.springtricount.member.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    public ExpenseDto save(ExpenseDto expenseDto) {
        return expenseRepository.save(expenseDto);
    }

    public List<ExpenseDto> findAll(MemberDto memberDto) {
        return expenseRepository.findAll(memberDto)
                .stream()
                .map(expenseEntity -> new ExpenseDto(
                        expenseEntity.id(),
                        expenseEntity.name(),
                        expenseEntity.settlementId(),
                        expenseEntity.payerMemberId(),
                        expenseEntity.amount(),
                        expenseEntity.expenseDateTime()))
                .toList();
    }
}
