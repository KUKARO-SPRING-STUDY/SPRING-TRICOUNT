package groom.backend.springtricount.settlement;

import groom.backend.springtricount.expense.ExpenseDto;
import groom.backend.springtricount.expense.ExpenseRepository;
import groom.backend.springtricount.member.MemberDto;
import groom.backend.springtricount.member.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementService {
    private final SettlementRepository settlementRepository;
    private final ExpenseRepository expenseRepository;

    public List<SettlementDto> findAll(MemberDto memberDto) {
        MemberEntity member = new MemberEntity(memberDto.id(), memberDto.loginId(), memberDto.name(), null);
        return settlementRepository.findAllByMemberId(member)
                .stream().map(settlementEntity -> getSettlementDto(settlementEntity, member)
                ).toList();
    }

    public SettlementDto findById(MemberDto memberDto, Long id) {
        MemberEntity member = new MemberEntity(memberDto.id(), memberDto.loginId(), memberDto.name(), null);
        return settlementRepository.findById(id)
                .map(settlement -> getSettlementDto(settlement, member))
                .orElse(null);
    }

    private SettlementDto getSettlementDto(SettlementEntity settlementEntity, MemberEntity member) {
        return new SettlementDto(
                settlementEntity.id(),
                settlementEntity.name(),
                settlementEntity.participants().stream().map(participantEntity -> new MemberDto(
                        participantEntity.id(),
                        participantEntity.loginId(),
                        participantEntity.name(),
                        null
                )).toList(),
                expenseRepository.findAllBySettlementId(member, settlementEntity.id()).stream()
                        .map(expenseEntity -> new ExpenseDto(
                                expenseEntity.id(),
                                expenseEntity.name(),
                                expenseEntity.settlementId(),
                                new MemberDto(
                                        expenseEntity.payerMember().id(),
                                        expenseEntity.payerMember().loginId(),
                                        expenseEntity.payerMember().name(),
                                        null
                                ),
                                expenseEntity.amount(),
                                expenseEntity.expenseDateTime()
                        )).toList());
    }

}
