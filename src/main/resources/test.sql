select s.id                as settlement_id,
       s.name              as settlement_name,
       m.id                as member_id,
       m.login_id          as member_login_id,
       m.name              as member_name,
       e.id                as expense_id,
       e.name              as expense_name,
       e.amount            as expense_amount,
       e.payer_member_id   as expense_payer_id,
       e.expense_date_time as expense_expense_date_time
from settlement s
         join settlement_participant sp on s.id = sp.settlement_id
         join member m on sp.member_id = m.id
         join expense e on s.id = e.settlement_id
where m.login_id = 'kim';

select s.id   as settlement_id,
       s.name as settlement_name
from settlement_participant sp
         join settlement s on s.id = sp.settlement_id

select s.id       as settlement_id,
       s.name     as settlement_name,
       m.id       as member_id,
       m.login_id as member_login_id,
       m.name     as member_name
from (select * from settlement_participant where member_id = 3) spm
         join settlement_participant sp on spm.settlement_id = sp.settlement_id
         join settlement s on s.id = sp.settlement_id
         join member m on sp.member_id = m.id

select s.id       as settlement_id,
       s.name     as settlement_name,
       m.id       as member_id,
       m.login_id as member_login_id,
       m.name     as member_name
from settlement_participant sp
         join settlement s on s.id = sp.settlement_id
         join member m on 3 = m.id