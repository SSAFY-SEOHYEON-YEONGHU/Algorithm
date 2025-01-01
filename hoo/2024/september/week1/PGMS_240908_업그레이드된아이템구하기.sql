select a.item_id, b.item_name, b.rarity
from item_tree a
join item_info b
on a.item_id = b.item_id
where a.parent_item_id in
(select item_id
from item_info
where rarity = "RARE")
order by a.item_id desc;