package groom.backend.springtricount.member;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
    }

    public Optional<List<MemberEntity>> findAll() {
        List<MemberEntity> result = jdbcTemplate.query("SELECT * FROM member", this::memberRowMapper);
        return Optional.of(result);
    }

    private MemberEntity memberRowMapper(ResultSet rs, int rowNum) {
        try {
            return new MemberEntity(rs.getLong("id"), rs.getString("login_id"), rs.getString("name"), rs.getString("password"));
        } catch (SQLException e) {
            return null;
        }
    }
}