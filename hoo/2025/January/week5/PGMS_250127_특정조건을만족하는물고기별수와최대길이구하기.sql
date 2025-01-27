-- 코드를 작성해주세요
select count(*) fish_count, MAX(length) max_length, fish_type
from fish_info
group by fish_type
having avg(case when length is null then 10
          else length
          end) >= 33
order by fish_type;