function validateForm() {
	
	// error fields
	var theErrorFields = [];
	
	// Passengers form
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
	
	// check gender
	var email = flightTicket["gender"].value.trim();;
	if (email == "") {
		theErrorFields.push("Gender");
	}
	
	if (theErrorFields.length > 0) {
		alert("Form validation failed. Please add data for following fields: " + theErrorFields);
		return false;
	}
}