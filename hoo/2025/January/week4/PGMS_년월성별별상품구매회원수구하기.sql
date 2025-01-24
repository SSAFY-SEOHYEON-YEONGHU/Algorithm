-- 코드를 입력하세요
select YEAR(sales_date) year, MONTH(sales_date) month, ui.gender gender, count(distinct ui.user_id) users# 먼저 join을 통해 각 주문의 유저 성별을 뽑아와준다
from online_sale os
join user_info ui
on os.user_id = ui.user_id
where ui.gender is not null
group by year, month, gender
order by year asc, month asc, gender asc;