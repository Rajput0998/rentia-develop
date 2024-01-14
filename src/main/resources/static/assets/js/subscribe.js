// subscribe.js
(function ($) {
    "use strict";

    $(".newsletter-form").validator().on("submit", function (event) {
        if (event.isDefaultPrevented()) {
            formError();
            submitMSG(false, "Please enter a valid email address.");
        } else {
            event.preventDefault();
            subscribeForm();
        }
    });

    function subscribeForm() {
        var email = $(".newsletter-form input[name='EMAIL']").val();
        var formData = { "EMAIL": email };

        $.ajax({
            type: "POST",
            url: "/api/u1/user/subscribe",
            data: JSON.stringify(formData),
            contentType: "application/json",
            success: function (response, status, xhr) {
                if (status === "success") {
                    subscribeSuccess();
                } else {
                    formError();
                    submitMSG(false, response);
                }
            }
        });
    }

    function subscribeSuccess() {
        $(".newsletter-form")[0].reset();
        $("#validator-newsletter").text("Thank you for subscribing!").show();
    }

    function formError() {
        $("#validator-newsletter").text("Please enter a valid email address.").show();
    }

    function submitMSG(valid, msg) {
        if (!valid) {
            $("#validator-newsletter").text(msg).show();
        }
    }
})(jQuery);
