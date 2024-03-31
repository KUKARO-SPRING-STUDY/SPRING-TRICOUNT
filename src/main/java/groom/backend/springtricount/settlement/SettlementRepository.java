package groom.backend.springtricount.settlement;

import groom.backend.springtricount.member.MemberDto;
import groom.backend.springtricount.member.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SettlementRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<SettlementEntity> findAll(MemberDto member) {
        return jdbcTemplate.query("""
                                select s.id       as settlement_id,
                                       s.name     as settlement_name,
                                       m.id       as member_id,
                                       m.login_id as member_login_id,
                                       m.name     as member_name
                                from (select * from settlement_participant where member_id = ?) spm
                                         join settlement_participant sp on spm.settlement_id = sp.settlement_id
                                         join settlement s on s.id = sp.settlement_id
                                         join member m on sp.member_id = m.id
                                """,
                        this::settlementRowMapper,
                        member.id()
                ).stream().filter(Objects::nonNull)
                .collect(
                        Collectors.groupingBy(
                                SettlementEntity::id,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        this::mergeMemberEntityBySettlementEntity
                                )))
                .values().stream().toList();
    }

    /**
     * SettlementEntity를 List로 받아서 participants를 합쳐서 하나의 SettlementEntity로 만들어준다.
     */
    private SettlementEntity mergeMemberEntityBySettlementEntity(List<SettlementEntity> list) {
        SettlementEntity settlement = list.getFirst();
        List<MemberEntity> participants = new ArrayList<>();
        list.forEach(s -> participants.addAll(s.participants()));
        return new SettlementEntity(settlement.id(), settlement.name(), participants);
    }

    private SettlementEntity settlementRowMapper(ResultSet rs, int rowNum) {
        try {
            return new SettlementEntity(
                    rs.getLong("settlement_id"),
                    rs.getString("settlement_name"),
                    List.of(new MemberEntity(
                            rs.getLong("member_id"),
                            rs.getString("member_login_id"),
                            rs.getString("member_name"),
                            null
                    )));
        } catch (SQLException e) {
            return null;
        }
    }
}


