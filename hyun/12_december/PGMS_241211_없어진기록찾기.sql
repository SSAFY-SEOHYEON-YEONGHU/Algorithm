package sql;


SELECT ANIMAL_ID, NAME
from ANIMAL_OUTS
where ANIMAL_ID not in (select ANIMAL_ID from ANIMAL_INS)
gi