DELETE FROM orderlines;
DELETE FROM orders WHERE OrderDate > "2020-01-01";

INSERT INTO orders (`OrderID`,`CustomerID`,`SalespersonPersonID`,`PickedByPersonID`,`ContactPersonID`,`BackorderOrderID`,`OrderDate`,`ExpectedDeliveryDate`,`CustomerPurchaseOrderNumber`,`IsUndersupplyBackordered`,`Comments`,`DeliveryInstructions`,`InternalComments`,`PickingCompletedWhen`,`LastEditedBy`,`LastEditedWhen`)
VALUES
(73596,1,1,1,1,1,'2020-05-28','2020-05-28','1',1,NULL,NULL,NULL,'2020-05-28 11:00:00',1,'2020-05-29 11:00:00'),
(73597,2,1,1,1,1,'2020-05-28','2020-05-28','1',1,NULL,NULL,NULL,'2020-05-28 11:00:00',1,'2020-05-29 11:00:00'),
(73598,3,1,1,1,1,'2020-05-28','2020-05-28','1',1,NULL,NULL,NULL,'2020-05-28 11:00:00',1,'2020-05-29 11:00:00'),
(73599,4,1,1,1,1,'2020-05-28','2020-05-28','1',1,NULL,NULL,NULL,'2020-05-28 11:00:00',1,'2020-05-29 11:00:00'),
(73600,5,1,1,1,1,'2020-05-28','2020-05-28','1',1,NULL,NULL,NULL,'2020-05-28 11:00:00',1,'2020-05-29 11:00:00'),
(73601,6,1,1,1,1,'2020-05-28','2020-05-28','1',1,NULL,NULL,NULL,'2020-05-28 11:00:00',1,'2020-05-29 11:00:00'),
(73602,7,1,1,1,1,'2020-05-28','2020-05-28','1',1,NULL,NULL,NULL,'2020-05-28 11:00:00',1,'2020-05-29 11:00:00'),
(73603,8,1,1,1,1,'2020-05-28','2020-05-28','1',1,NULL,NULL,NULL,'2020-05-28 11:00:00',1,'2020-05-29 11:00:00'),
(73604,9,1,1,1,1,'2020-05-28','2020-05-28','1',1,NULL,NULL,NULL,'2020-05-28 11:00:00',1,'2020-05-29 11:00:00'),
(73605,10,1,1,1,1,'2020-05-28','2020-05-28','1',1,NULL,NULL,NULL,'2020-05-28 11:00:00',1,'2020-05-29 11:00:00');

INSERT INTO orderlines (`OrderLineID`, `OrderID`, `StockItemID`, `Description`, `PackageTypeID`, `Quantity`, `UnitPrice`, `TaxRate`, `PickedQuantity`, `PickingCompletedWhen`, `LastEditedBy`, `LastEditedWhen`)
VALUES
(1, 73596, 1, '', 1, 1, 25.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00'),
(2, 73596, 4, '', 1, 1, 32.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00'),
(3, 73597, 1, '', 1, 1, 25.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00'),
(4, 73597, 4, '', 1, 1, 32.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00'),
(5, 73598, 1, '', 1, 1, 25.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00'),
(6, 73598, 4, '', 1, 1, 32.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00'),
(7, 73599, 1, '', 1, 1, 25.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00'),
(8, 73599, 4, '', 1, 1, 32.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00'),
(9, 73600, 1, '', 1, 1, 25.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00'),
(10, 73600, 4, '', 1, 1, 32.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00'),
(11, 73601, 1, '', 1, 1, 25.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00'),
(12, 73601, 4, '', 1, 1, 32.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00'),
(13, 73602, 1, '', 1, 1, 25.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00'),
(14, 73602, 4, '', 1, 1, 32.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00'),
(15, 73603, 1, '', 1, 1, 25.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00'),
(16, 73603, 4, '', 1, 1, 32.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00'),
(17, 73604, 1, '', 1, 1, 25.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00'),
(18, 73604, 4, '', 1, 1, 32.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00'),
(19, 73605, 1, '', 1, 1, 25.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00'),
(20, 73605, 4, '', 1, 1, 32.00, 15.000, 1, '2020-05-28 11:00:00', 1, '2020-05-28 11:00:00');

UPDATE customers SET DeliveryAddressLine1 = 'De Schipperij 9', `DeliveryAddressLine2` = 'Hellendoorn', `DeliveryPostalCode` = '7447 XW' WHERE (`CustomerID` = 1);
UPDATE customers SET DeliveryAddressLine1 = 'Strangeweg 18', `DeliveryAddressLine2` = 'Ommen', `DeliveryPostalCode` = '7731 GW' WHERE (`CustomerID` = 2);
UPDATE customers SET DeliveryAddressLine1 = 'Sterkerijstraat 1', `DeliveryAddressLine2` = 'Slagharen', `DeliveryPostalCode` = '7776 XG' WHERE (`CustomerID` = 3);
UPDATE customers SET DeliveryAddressLine1 = 'Vincent van Goghlaan 23', `DeliveryAddressLine2` = 'Meppel', `DeliveryPostalCode` = '7944 EK' WHERE (`CustomerID` = 4);
UPDATE customers SET DeliveryAddressLine1 = 'Lovinkstraat 20', `DeliveryAddressLine2` = 'Kampen', `DeliveryPostalCode` = '8265 BT' WHERE (`CustomerID` = 5);
UPDATE customers SET DeliveryAddressLine1 = 'Markt 50', `DeliveryAddressLine2` = 'Hardenberg', `DeliveryPostalCode` = '7772 AE' WHERE (`CustomerID` = 6);
UPDATE customers SET DeliveryAddressLine1 = 'Tjalkstraat 1', `DeliveryAddressLine2` = 'Raalte', `DeliveryPostalCode` = '8102 HG' WHERE (`CustomerID` = 7);
UPDATE customers SET DeliveryAddressLine1 = 'Dr. A. Philipsstr. 12', `DeliveryAddressLine2` = 'Hoogeveen', `DeliveryPostalCode` = '7903 AM' WHERE (`CustomerID` = 8);
UPDATE customers SET DeliveryAddressLine1 = 'Pr. Irenestraat 4', `DeliveryAddressLine2` = 'Genemuiden', `DeliveryPostalCode` = '8281 DX' WHERE (`CustomerID` = 9);
UPDATE customers SET DeliveryAddressLine1 = 'Wethouder Klunderstraat 16', `DeliveryAddressLine2` = 'Zuidwolde', `DeliveryPostalCode` = '7921 HR' WHERE (`CustomerID` = 10);
