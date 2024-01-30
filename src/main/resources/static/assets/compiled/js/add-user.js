// // $(document).ready(function() {
//     $('#add-user-Btn').click(function() {
//
//         // Get values from input fields
//         var firstName = $('#firstName').val();
//         var lastName = $('#lastName').val();
//         var username = $('#username').val();
//         var password = $('#password').val();
//         var repassword = $('#repassword').val();
//         var email = $('#email').val();
//         var mobileNumber = $('#mobileNumber').val();
//
//         // Create data object to be sent in POST request
//         var postData = {
//             firstName: firstName,
//             lastName: lastName,
//             username: username,
//             password: password,
//             repassword: repassword,
//             email: email,
//             mobileNumber: mobileNumber,
//
//         };
//
//         // AJAX POST request
//
//        alert(JSON.stringify(postData))
//         $.ajax({
//             url:'/api/v1/user',
//             type:'POST',
//             contentType:'application/json',
//             data: JSON.stringify(postData),
//             success:function(response, textStatus, xhr) {
//                     $('#success-msg').text("کاربر با موفقیت ثبت شد");
//                     $('#success-msg').show();
//
//                 setTimeout(function() {
//                     $('#success-msg').hide();
//
//                 }, 4000);
//
//             },
//             error: function(xhr, status, error) {
//                 alert(error)
//                 $('#failed_msg').text( "پیغام خطا: "  + xhr.responseJSON.message  );
//                 $('#failed_msg').show();
//                 setTimeout(function() {
//                     $('#failed_msg').hide();
//
//                 }, 4000);
//             }
//
//     });
//     });


//-------------------------------------------------


// $(document).ready(function() {
// $('#add-user-Btn').click(function() {
$(document).ready(function () {
    $("form").submit(function (event) {
    // Get values from input fields
    // var firstName = $('#firstName').val();
    // var lastName = $('#lastName').val();
    var username = $('#username').val();
    // var password = $('#password').val();
    // var repassword = $('#repassword').val();
    var email = $('#email').val();
    var mobileNumber = $('#mobileNumber').val();

    // Create data object to be sent in POST request
    var postData = {
        // firstName: firstName,
        // lastName: lastName,
        username: username,
        // password: password,
        // repassword: repassword,
        email: email,
        mobileNumber: mobileNumber,
    };

    // AJAX POST request

    $.ajax({
        url:'/api/v1/user',
        type:'POST',
        headers: {'Authorization': "Bearer " + localStorage.getItem("token")},
        contentType:'application/json',
        data: JSON.stringify(postData),
        success:function(response, textStatus, xhr) {
            $('#success-msg').text("پیغام‌: کاربر با موفقیت ثبت شد");
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



$(document).ready(function() {
    $("#exit").click(function(){
        localStorage.removeItem("token");
        window.location.assign("/login");
    });
});


/*--------------------------------------------------------*/


// $(document).ready(function () {
//     $("form").submit(function (event) {
//         var formData = {
//             name: $("#name").val(),
//             email: $("#email").val(),
//             superheroAlias: $("#superheroAlias").val(),
//         };
//
//         $.ajax({
//             type: "POST",
//             url: "process.php",
//             data: formData,
//             dataType: "json",
//             encode: true,
//         }).done(function (data) {
//             console.log(data);
//         });
//
//         event.preventDefault();
//     });
// });