$(document).ready(function () {

    $("form").submit(function (event) {

        var username = $('#username').val();
        var password = $('#password').val();


        // Create data object to be sent in POST request
        var postData = {
            username: username,
            password: password,
        };

        // AJAX POST request

        $.ajax({
            url:'/api/v1/auth/authenticate',
            type:'POST',
            contentType:'application/json',
            data: JSON.stringify(postData),
            success:function(response, textStatus, xhr) {
                localStorage.setItem("token", response.access_token)
                window.location.assign("/view-traffics");

            },
            error: function(xhr, status, error) {
                $('#failed_msg').text( "پیغام خطا: "  + xhr.responseJSON.message  );
                $('#failed_msg').show();
                setTimeout(function() {
                    $('#failed_msg').hide();

                }, 4000);
            }

        });
        event.preventDefault();
    });
});
