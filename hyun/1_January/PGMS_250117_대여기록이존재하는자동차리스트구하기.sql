SELECT DISTINCT A.CAR_ID as CAR_ID
FROM CAR_RENTAL_COMPANY_CAR A, CAR_RENTAL_COMPANY_RENTAL_HISTORY B
WHERE A.CAR_ID = B.CAR_ID and A.CAR_TYPE = '세단' and month(B.START_DATE) >= 10
ORDER BY CAR_ID DESC;

