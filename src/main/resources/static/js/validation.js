function validateFormRegisterAgency() {
    const name = document.getElementById("name").value;
    const number = document.getElementById("mobile-number").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm-password").value;

    const nameError = document.getElementById("name-error");
    const numberError = document.getElementById(
        "mobile-number-error"
    );
    const emailError = document.getElementById(
        "email-error"
    );
    const passwordError = document.getElementById(
        "password-error"
    );
    const confirmPasswordError = document.getElementById(
        "confirm-password-error"
    );


    nameError.textContent = "";
    numberError.textContent = "";
    emailError.textContent = "";
    passwordError.textContent = "";
    confirmPasswordError.textContent = "";

    let isValid = true;

    if (name.length < 2) {
        nameError.textContent =
            "Name must be at least 2 characters long.";
        isValid = false;
    }

    if (number.length < 10 || number.length > 12) {
        numberError.textContent =
            "Please enter valid mobile number.";
        isValid = false;
    }

    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        emailError.textContent =
            "Please enter a valid email address.";
        isValid = false;
    }

    if (password === "" || password.length < 6) {
        passwordError.textContent =
            "Password must be at least 6 characters long.";
        isValid = false;
    }


    if (confirmPassword !== password) {
        confirmPasswordError.textContent =
            "Passwords do not match.";
        isValid = false;
    }

    return isValid;
}

function validateFormRegisterUser() {
    const firstName = document.getElementById("first-name").value;
    const lastName = document.getElementById("last-name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm-password").value;

    const firstNameError = document.getElementById("first-name-error");
    const lastNameError = document.getElementById("last-name-error");
    const emailError = document.getElementById(
        "email-error"
    );
    const passwordError = document.getElementById(
        "password-error"
    );
    const confirmPasswordError = document.getElementById(
        "confirm-password-error"
    );


    firstNameError.textContent = "";
    lastNameError.textContent = "";
    emailError.textContent = "";
    passwordError.textContent = "";
    confirmPasswordError.textContent = "";

    let isValid = true;

    if (firstName.length < 2) {
        firstNameError.textContent =
            "First name must be at least 2 characters long.";
        isValid = false;
    }

    if (lastName.length < 2) {
        lastNameError.textContent =
            "Last name must be at least 2 characters long.";
        isValid = false;
    }

    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        emailError.textContent =
            "Please enter a valid email address.";
        isValid = false;
    }

    if (password === "" || password.length < 6) {
        passwordError.textContent =
            "Password must be at least 6 characters long.";
        isValid = false;
    }


    if (confirmPassword !== password) {
        confirmPasswordError.textContent =
            "Passwords do not match.";
        isValid = false;
    }

    return isValid;
}

function validateLogin() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const emailError = document.getElementById(
        "email-error"
    );
    const passwordError = document.getElementById(
        "password-error"
    );

    emailError.textContent = "";
    passwordError.textContent = "";

    let isValid = true;

    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        emailError.textContent =
            "Please enter a valid email address.";
        isValid = false;
    }

    if (password === "" || password.length < 6) {
        passwordError.textContent =
            "Password must be at least 6 characters long.";
        isValid = false;
    }
    return isValid;
}

function validateFormContactUs() {
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const message = document.getElementById("message").value;

    const nameError = document.getElementById("name-error");
    const emailError = document.getElementById(
        "email-error"
    );
    const messageError = document.getElementById(
        "message-error"
    );


    nameError.textContent = "";
    emailError.textContent = "";
    messageError.textContent = "";

    let isValid = true;

    if (name.length < 2) {
        nameError.textContent =
            "Name must be at least 2 characters long.";
        isValid = false;
    }

    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        emailError.textContent =
            "Please enter a valid email address.";
        isValid = false;
    }

    if (message.length < 2) {
        messageError.textContent =
            "Message must be at least 2 characters long.";
        isValid = false;
    } else if (message.length > 1500) {
        messageError.textContent =
            "Message must be maximum 1500 characters long.";
        isValid = false;
    }

    return isValid;
}

function validateFormAddProperty() {
    const url = document.getElementById("url").value;
    const name = document.getElementById("name").value;
    const price = document.getElementById("price").value;
    const size = document.getElementById("size").value;
    const country = document.getElementById("country").value;
    const city = document.getElementById("city").value;
    const street = document.getElementById("street").value;
    const number = document.getElementById("number").value;
    const description = document.getElementById("description").value;

    const urlError = document.getElementById("url-error");
    const nameError = document.getElementById("name-error");
    const priceError = document.getElementById("price-error");
    const sizeError = document.getElementById("size-error");
    const countryError = document.getElementById("country-error");
    const cityError = document.getElementById("city-error");
    const streetError = document.getElementById("street-error");
    const numberError = document.getElementById("number-error");
    const descriptionError = document.getElementById("description-error");


    urlError.textContent = "";
    nameError.textContent = "";
    priceError.textContent = "";
    sizeError.textContent = "";
    countryError.textContent = "";
    cityError.textContent = "";
    streetError.textContent = "";
    numberError.textContent = "";
    descriptionError.textContent = "";

    let isValid = true;

    if (url.length < 3) {
        urlError.textContent =
            "Url must be at least 3 characters long.";
        isValid = false;
    }

    if (name.length < 3) {
        nameError.textContent =
            "Name must be at least 3 characters long.";
        isValid = false;
    }

    if (price < 1) {
        priceError.textContent =
            "Price must be positive number";
        isValid = false;
    }

    if (size < 1) {
        sizeError.textContent =
            "Price must be positive number";
        isValid = false;
    }

    if (country.length < 3) {
        countryError.textContent =
            "Country must be at least 3 characters long.";
        isValid = false;
    }

    if (city.length < 3) {
        cityError.textContent =
            "City must be at least 3 characters long.";
        isValid = false;
    }

    if (street.length < 3) {
        streetError.textContent =
            "Street must be at least 3 characters long.";
        isValid = false;
    }

    if (number.length < 1) {
        numberError.textContent =
            "Number must be at least 1 characters long.";
        isValid = false;
    }

    if (description.length < 3) {
        descriptionError.textContent =
            "Description must be at least 3 characters long.";
        isValid = false;

    } else if (description.length > 1500) {
        descriptionError.textContent =
            "Description must be maximum 1500 characters long.";
        isValid = false;
    }

    return isValid;
}
function validateFormAddAgent() {
    const email = document.getElementById("email").value;

    const emailError = document.getElementById("email-error");
    let isValid = true;

    emailError.textContent = "";

    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        emailError.textContent =
            "Please enter a valid email address.";
        isValid = false;
    }

    return isValid;
}
