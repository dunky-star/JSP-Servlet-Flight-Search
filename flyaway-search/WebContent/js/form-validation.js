function validateForm() {
	
	// error fields
	var theErrorFields = [];
	
	// student form
	var flightTicket = document.forms["flightTicket"];
	
	// check first name
	var firstName = flightTicket["firstName"].value.trim();;
	if (firstName == "") {
		theErrorFields.push("First name");
	}

	// check last name
	var lastName = flightTicket["lastName"].value.trim();;
	if (lastName == "") {
		theErrorFields.push("Last name");
	}
	
	// check email
	var email = flightTicket["email"].value.trim();;
	if (email == "") {
		theErrorFields.push("Email");
	}
	
	if (theErrorFields.length > 0) {
		alert("Form validation failed. Please add data for following fields: " + theErrorFields);
		return false;
	}
}