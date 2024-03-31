package groom.backend.springtricount.expense;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExpenseEntity(
        Long id,
        String name,
        Long settlementId,
        Long payerMemberId,
        BigDecimal amount,
        LocalDateTime expenseDateTime
) {
}
