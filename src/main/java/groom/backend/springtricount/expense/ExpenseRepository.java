package groom.backend.springtricount.expense;

import groom.backend.springtricount.member.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ExpenseRepository {
    private final JdbcTemplate jdbcTemplate;

    public ExpenseDto save(ExpenseDto expenseDto) {
        return null;
    }

    public List<ExpenseEntity> findAll(MemberDto memberDto) {
        return jdbcTemplate.query("SELECT * FROM expense WHERE member_id = ?", this::expenseRowMapper, memberDto.id())
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    private ExpenseEntity expenseRowMapper(ResultSet rs, int rowNum) {
        try {
            return new ExpenseEntity(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getLong("settlement_id"),
                    rs.getLong("payer_member_id"),
                    rs.getBigDecimal("amount"),
                    getExpenseDateTime(rs));
        } catch (SQLException e) {
            return null;
        }
    }

    private LocalDateTime getExpenseDateTime(ResultSet rs) throws SQLException {
        return rs.getDate("expense_date_time")
                .toLocalDate()
                .atTime(rs.getTime("expense_date_time").toLocalTime());
    }
}
