
DELIMITER $$

DROP PROCEDURE IF EXISTS `purchase_historic`.`getAllApplicationsByYear` $$
CREATE PROCEDURE `purchase_historic`.`getAllApplicationsByYear` (IN ano decimal(4,0))
BEGIN
  set @query = CONCAT('select id, application, value, buy_date, due_date, description from expense_control where ano =', ano);
prepare stmt from @query;
execute stmt;

END $$

DELIMITER ;