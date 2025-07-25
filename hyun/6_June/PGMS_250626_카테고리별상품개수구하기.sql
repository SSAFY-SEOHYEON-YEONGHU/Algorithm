# SELECT LEFT(PRODUCT_CODE,2) AS CATEGORY, COUNT(*) AS PRODUCTS
# FROM PRODUCT
# GROUP BY LEFT(PRODUCT_CODE,2)
# ORDER BY CATEGORY

SELECT SUBSTRING(PRODUCT_CODE,1,2) AS CATEGORY, COUNT(*) AS PRODUCTS
FROM PRODUCT
GROUP BY SUBSTRING(PRODUCT_CODE,1,2)
ORDER BY CATEGORY