
DELIMITER $$

DROP PROCEDURE IF EXISTS `purchase_historic`.`getAllApplicationsByMonth` $$
CREATE PROCEDURE `getAllApplicationsByMonth`(IN mes varchar(9), IN ano decimal(4,0))
BEGIN
  set @query = CONCAT('select id, application, value, buy_date, due_date, description from expense where mes = ', mes +' and ano = ', ano);
prepare stmt from  @query;
execute stmt;
END $$

DELIMITER ;