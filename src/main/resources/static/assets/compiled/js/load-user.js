$(document).ready(function () {
    $("form").submit(function (event) {

        // AJAX POST request

        $.ajax({
            url:'/api/v1/user/create-from-wg-clients',
            type:'POST',
            contentType:'application/json',
            // data: JSON.stringify(postData),
            success:function(response, textStatus, xhr) {
                $('#success-msg').text("پیغام‌: کاربران با موفقیت ایجاد شدند");
                $('#success-msg').show();

                setTimeout(function() {
                    $('#success-msg').hide();

                }, 4000);

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
