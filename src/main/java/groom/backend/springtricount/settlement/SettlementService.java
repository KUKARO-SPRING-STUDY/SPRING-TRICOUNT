package groom.backend.springtricount.settlement;

import groom.backend.springtricount.member.MemberDto;
import groom.backend.springtricount.member.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementService {
    private final SettlementRepository settlementRepository;

    public List<SettlementDto> findAll(MemberDto member) {
        MemberEntity memberEntity = new MemberEntity(member.id(), member.loginId(), member.name(), null);
        return settlementRepository.findAll(memberEntity)
                .stream().map(settlementEntity -> new SettlementDto(
                        settlementEntity.id(),
                        settlementEntity.name(),
                        settlementEntity.participants().stream().map(participantEntity -> new MemberDto(
                                participantEntity.id(),
                                participantEntity.loginId(),
                                participantEntity.name(),
                                null
                        )).toList(),
                        List.of()
                )).toList();
    }
}
