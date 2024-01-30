

// $(document).ready(function() {
// $('#add-user-Btn').click(function() {
$(document).ready(function () {
    $("form").submit(function (event) {
        // Get values from input fields
        // var firstName = $('#firstName').val();
        // var lastName = $('#lastName').val();
        var configUrl = $('#config-url').val();


        // AJAX POST request



        $.ajax({
            url:'api/v1/add-endpoint/'+ configUrl,
            headers: {'Authorization': "Bearer " + localStorage.getItem("token")},
            type:'POST',
            contentType:'application/json',
            success:function(response, textStatus, xhr) {
                $('#success-msg').text("پیغام‌: کانفیگ با موفقیت ثبت شد");
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



    $.ajax({
        url:'api/v1/endpoint',
        type:'GET',
        contentType:'application/json',
        headers: {'Authorization': "Bearer " + localStorage.getItem("token")},
        success:function(response, textStatus, xhr) {
            $('#config-url').val(response)
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



});


$(document).ready(function() {
    $("#exit").click(function(){
        localStorage.removeItem("token");
        window.location.assign("/login");
    });
});
