package groom.backend.springtricount.member;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
    }

    public MemberEntity save(MemberEntity member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("member")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> params = Map.of(
                "login_id", member.loginId(),
                "password", member.password(),
                "name", member.name()
        );

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));

        return new MemberEntity(key.longValue(), member.loginId(), member.name(), member.password());
    }

    public List<MemberEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM member", this::memberRowMapper)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    private MemberEntity memberRowMapper(ResultSet rs, int rowNum) {
        try {
            return new MemberEntity(rs.getLong("id"), rs.getString("login_id"), rs.getString("name"), rs.getString("password"));
        } catch (SQLException e) {
            return null;
        }
    }
}