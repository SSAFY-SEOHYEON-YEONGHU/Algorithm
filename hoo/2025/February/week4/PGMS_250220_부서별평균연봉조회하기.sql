-- 코드를 작성해주세요
select he.dept_id, hd.dept_name_en dept_name_en, round(avg(he.sal), 0) avg_sal
from hr_employees he
join hr_department hd
on he.dept_id = hd.dept_id
group by dept_id
order by avg_sal desc;