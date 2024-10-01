select Ayear as YEAR, A.Avalue - E.SIZE_OF_COLONY as YEAR_DEV, E.ID as ID
from ECOLI_DATA E,
(select MAX(SIZE_OF_COLONY) as Avalue, YEAR(DIFFERENTIATION_DATE) as Ayear
from ECOLI_DATA
group by YEAR(DIFFERENTIATION_DATE)) as A
where YEAR(E.DIFFERENTIATION_DATE) = A.Ayear
order by YEAR, YEAR_DEV