select a.product_code, a.price * sum(b.sales_amount) as SALES
from product as a
join offline_sale as b
on a.product_id = b.product_id
group by a.product_code
order by SALES desc, a.product_code asc;