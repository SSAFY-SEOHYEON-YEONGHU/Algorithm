-- 코드를 작성해주세요
select concat(A.QUARTER,'Q') as QUARTER, count(A.QUARTER) as ECOLI_COUNT
from
(select
    case
        when month(DIFFERENTIATION_DATE) between "1" and "3" then 1
        when month(DIFFERENTIATION_DATE) between "4" and "6" then 2
        when month(DIFFERENTIATION_DATE) between "7" and "9" then 3
        else 4
    end as QUARTER
from ECOLI_DATA) as A
group by A.QUARTER
order by A.QUARTER