-- 코드를 입력하세요
SELECT animal_id, name
from animal_outs
where animal_id
not in(select animal_id
        from animal_ins)
order by animal_id asc;

select ao.animal_id, ao.name
from animal_outs ao
left join animal_ins ai # inner join이 아닌, left join을 통해 animal_outs에 있는 row들은 다 불러와줘야 함.
on ao.animal_id = ai.animal_id
where ao.animal_id is not null and ai.animal_id is null # 그 후 outs에는 id가 있는데 ins에는 id가 없는 row들을 걸러주는 where문으로 원하는 결과 조회
order by ao.animal_id asc;