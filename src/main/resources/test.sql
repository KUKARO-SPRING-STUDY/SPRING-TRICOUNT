
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
