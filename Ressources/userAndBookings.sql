
/* Users 
	passwords = logins
	*/
INSERT INTO `users` (`idUser`, `login`, `password`, `firstname`, `lastname`, `note`) VALUES 
	(2, 'tommy', '044f4b3501cd8e8131d40c057893f4fdff66bf4032ecae159e0c892a28cf6c8e', 'Tom', 'Basil', 3),
	(3, 'vial', 'b107fb284c075897508e7f8dff929ac89b3f1067bdce0f25687ab657e5ee7d0d', 'Christian', 'Vial', 7),
	(4, 'Elie', '281dc093e8ea1bd931774d7a28dccb50e3a307756b7ff07bad897f10a56bfde0', 'Elie', 'Ensuque', 20);
	
	
/*
	bookings :
		(admin) User 1 has only opened bookings
		(Tommy) User 2 has only closed bookings
		(Vial) User 3 has both opened and closed bookings
		(Elie) User 4 has no bookings
		
		status:
		1 is opened
		0 is closed
		
*/

INSERT INTO `bookings` (`idBooking`, `idCar`, `idUser`, `status`, `dateUp`, `dateDown`) VALUES
/*User 1 bookings*/
	(1, 3, 1, 1, '2019-05-28', '0000-00-00'),
	(2, 4, 1, 1, '2019-05-28', '0000-00-00'),
/*User 2 bookings*/
	(3, 3, 2, 0, '2019-05-28', '2019-05-30'),
	(4, 6, 2, 0, '2019-05-28', '2019-05-30'),
/*User 3 bookings*/
	(5, 1, 3, 0, '2019-05-28', '2019-05-30'), 
	(6, 2, 3, 1, '2019-05-28', '0000-00-00');