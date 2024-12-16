package sql;


select B.ID as ID , B.NAME as NAME, B.HOST_ID as HOST_ID
from (
SELECT HOST_ID
from PLACES
group by HOST_ID
having count(HOST_ID) >= 2
order by ID) A, PLACES B
where A.HOST_ID = B.HOST_ID
order by B.ID