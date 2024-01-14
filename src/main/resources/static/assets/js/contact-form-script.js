/*==============================================================*/
// Raque Contact Form  JS
/*==============================================================*/
(function ($) {
    "use strict"; // Start of use strict
    $("#contactForm").validator().on("submit", function (event) {
        if (event.isDefaultPrevented()) {
            // handle the invalid form...
            formError();
            submitMSG(false, "Did you fill up the form properly?");
        } else {
            // everything looks good!
            event.preventDefault();
            submitForm();
        }
    });


    function submitForm() {
    // Initiate Variables With Form Content
    var name = $("#name").val();
    var email = $("#email").val();
    var mobNum = $("#mobNum").val();
    var pgType = $("#pg_type").val();
    var budget = $("#budget").val();
    
    // Set minAmount and maxAmount based on selected value
    var minAmount, maxAmount;
	if (budget === "10000-15000") {
        minAmount = 10000.0;
        maxAmount = 15000.0;
    }
    else if (budget === "15000-20000") {
        minAmount = 15000.0;
        maxAmount = 20000.0;
    } else if (budget === "20000-25000") {
        minAmount = 20000.0;
        maxAmount = 25000.0;
    } else if (budget === "25000-30000") {
        minAmount = 25000.0;
        maxAmount = 30000.0;
    } else if (budget === "30000-40000") {
        minAmount = 30000.0;
        maxAmount = 40000.0;
    } else if (budget === "40000-50000") {
        minAmount = 40000.0;
        maxAmount = 50000.0;
    } else {
        minAmount = 50000.0;
        maxAmount = 60000.0;
    }
    
    

    // Create JSON object
    var formData = {
        "name": name,
        "email": email,
        "mobNum": mobNum,
        "pgType": pgType,
        "minAmount": minAmount,
        "maxAmount": maxAmount
    };

    $.ajax({
        type: "POST",
        url: "/api/u1/user/interested/register",
        data: JSON.stringify(formData), // Convert JSON object to string
        contentType: "application/json", // Set content type to JSON
        success: function (response, status, xhr) {
			console.log(response.id);
			console.log(status);
			console.log(xhr);
            if (status === "success") {
                formSuccess();
            } else {
                formError();
                submitMSG(false, response);
            }
        }
    });
}

    /*function formSuccess(){
        $("#contactForm")[0].reset();
        submitMSG(true, "Thank you for the interseted in the Property!")
    }

    function formError(){
        $("#contactForm").removeClass().addClass('shake animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
            $(this).removeClass();
        });
    }

    function submitMSG(valid, msg){
        if(valid){
            var msgClasses = "h4 tada animated text-success";
        } else {
            var msgClasses = "h4 text-danger";
        }
        $("#msgSubmit").text(msg);
    }*/
    function formSuccess(){
    $("#contactForm")[0].reset();
    $("#success-message").text("Thank you for your interest in the property!").show();
    $("#error-message").hide();
}

function formError(){
    $("#success-message").hide();
    $("#error-message").show();
}

function submitMSG(status, msg){
    if(status === true){
        $("#success-message").text(msg).show();
        $("#error-message").hide();
    } else {
        $("#success-message").hide();
        $("#error-message").text(msg).show();
    }
}
}(jQuery)); // End of use strict