-- 코드를 입력하세요
SELECT distinct c.car_id
from car_rental_company_car c
join car_rental_company_rental_history rh
on c.car_id = rh.car_id
where car_type = '세단' and MONTH(rh.start_date) = 10
order by c.car_id desc;