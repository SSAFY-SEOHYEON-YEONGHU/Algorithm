SELECT a.NAME as NAME , a.DATETIME as DATETIME
from ANIMAL_INS a
where a.ANIMAL_ID
not in (select ANIMAL_ID from ANIMAL_OUTS)
order by a.DATETIME
limit 3;
