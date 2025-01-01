select
    case
        when (MONTH(DIFFERENTIATION_DATE) < 4) then '1Q'
        when (4 <= MONTH(DIFFERENTIATION_DATE) and MONTH(DIFFERENTIATION_DATE) < 7) then '2Q'
        when (7 <= MONTH(DIFFERENTIATION_DATE) and MONTH(DIFFERENTIATION_DATE) < 10) then '3Q'
        when (10 <= MONTH(DIFFERENTIATION_DATE)) then '4Q'
    end as QUARTER, COUNT(*) as ECOLI_COUNT
from ecoli_data
group by QUARTER
order by QUARTER asc;