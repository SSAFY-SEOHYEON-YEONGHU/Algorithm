-- 코드를 입력하세요
SELECT a.animal_id, a.name
from animal_ins a
join (select ai.animal_id, datediff(ao.datetime, ai.datetime) diff
     from animal_ins ai
     join animal_outs ao
     on ai.animal_id = ao.animal_id) b
on a.animal_id = b.animal_id
order by b.diff desc
limit 2;