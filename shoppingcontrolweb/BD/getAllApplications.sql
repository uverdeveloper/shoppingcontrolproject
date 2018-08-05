DELIMITER $$

DROP PROCEDURE IF EXISTS `purchase_historic`.`getAllApplications` $$
CREATE PROCEDURE `purchase_historic`.`getAllApplications` ()
BEGIN
  SELECT id, application, value, buy_date, description FROM expense;
END $$

DELIMITER ;