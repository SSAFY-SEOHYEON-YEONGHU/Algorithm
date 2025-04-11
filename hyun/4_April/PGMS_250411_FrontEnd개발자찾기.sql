-- 다른사람 1
SELECT
    ID,
    EMAIL,
    FIRST_NAME,
    LAST_NAME
FROM
    DEVELOPERS
WHERE
    SKILL_CODE & (
        SELECT SUM(CODE)
        FROM SKILLCODES
        WHERE CATEGORY = 'Front End'
    ) > 0
ORDER BY
    ID ASC;

-- 다른사람 2
SELECT DISTINCT D.ID, D.EMAIL, D.FIRST_NAME, D.LAST_NAME
FROM DEVELOPERS D
JOIN SKILLCODES S
ON D.SKILL_CODE & S.CODE = S.CODE
WHERE S.CATEGORY = 'Front End'
ORDER BY D.ID

-- 내풀이
SELECT ID, EMAIL, FIRST_NAME, LAST_NAME
FROM (SELECT CODE
    FROM SKILLCODES
    WHERE CATEGORY = 'Front End') A JOIN DEVELOPERS B ON A.CODE & B.SKILL_CODE = A.CODE
GROUP BY ID, EMAIL, FIRST_NAME, LAST_NAME
ORDER BY ID