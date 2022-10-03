const loginReturnButton = document.querySelector(".login-return");

loginReturnButton.onclick = () => {
    location.href = "/account/login";
}

const registerGoButton = document.querySelector(".login-button");

registerButton.onclick = () => {
    const registerInputs = document.querySelectorAll(".login-input");

    let registerInfo = {
        lastName: registerInputs[0].value,
        firstName: registerInputs[1].value,
        email: registerInputs[2].value,
        passwored: registerInputs[3].value
    }

    $.ajax({
        async: false,
        type: "post",
        url: "/api/account/register",
        contentType: "application/json",
        data: JSON.stringify(registerInfo),
        dataType: "json",
        success: (response) => {

        },
        error: (error) => {

        }
    });
}