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
                        new MemberDto(
                                expenseEntity.payerMember().id(),
                                expenseEntity.payerMember().loginId(),
                                expenseEntity.payerMember().name(),
                                null),
                        expenseEntity.amount(),
                        expenseEntity.expenseDateTime()))
                .toList();
    }

    public List<ExpenseDto> findAllBySettlementId(MemberDto memberDto, Long settlementId) {
        return expenseRepository.findAllBySettlementId(memberDto, settlementId)
                .stream()
                .map(expenseEntity -> new ExpenseDto(
                        expenseEntity.id(),
                        expenseEntity.name(),
                        expenseEntity.settlementId(),
                        new MemberDto(
                                expenseEntity.payerMember().id(),
                                expenseEntity.payerMember().loginId(),
                                expenseEntity.payerMember().name(),
                                null),
                        expenseEntity.amount(),
                        expenseEntity.expenseDateTime()))
                .toList();
    }
}
