SELECT * FROM location;
SELECT * FROM user;
SELECT * FROM patient;
SELECT * FROM product;
SELECT * FROM productsincrease;
SELECT * FROM productsforlocation;
SELECT * FROM sale;
SELECT * FROM salesdetail;

SELECT 
    s.idLocation AS Sede,
    SUM(sd.quantity) AS Total_Productos_Vendidos
FROM 
    sale s
JOIN 
    salesDetail sd ON s.idSale = sd.idSale
GROUP BY 
    s.idLocation
ORDER BY 
    Total_Productos_Vendidos DESC;