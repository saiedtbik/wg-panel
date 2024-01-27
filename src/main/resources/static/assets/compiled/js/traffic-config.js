let clientsTable;
$(document).ready(function () {
    clientsTable = $('#clientListTable').DataTable({
        // "processing": true,
        // "serverSide": true,
        // "ajax": {
        //
        //     "url": "/api/v1/user/all",
        //     "type": "GET",
        //     "data": function (d) {
        //         alert(d);
        //         var customParams = getMyNewParameters();
        //         var params = jQuery.isEmptyObject(customParams) ? d : customParams;
        //         return params;
        //     },
        //     dataSrc: ''
        //
        // },


        ajax: {
            url: '/api/v1/user/all',
            dataSrc: ''
        },
        columns: [
            //
            // {
            //     data: "true",
            //     defaultContent: '',
            //     "render": function (data, type, row, meta) {
            //         if (data == true) {
            //             return ' <div style="font-weight: bold; color: #00A000"> <img src="/icon/ok.ico" alt="" width="15" height="15" data-toggle="tooltip" title="طرح انتخاب شده"> طرح انتخاب شده</div>';
            //         } else {
            //             return '';
            //         }
            //     }
            //
            // },
            {
                className: 'sorting_1 dt-control',
                orderable: false,
                data: null,
                defaultContent: ''
            },
            {data: "username", title: "نام کاربری", "width": "15%"},
            {data: "mobileNum", title: "موبایل", "width": "15%"},
            {data: "email", title: "ایمیل", "width": "40%"},

            // {
            //     title: "وضعیت ",
            //     data: "clientStatus",
            //     width: "13%",
            //     // className: "centeralign",
            //     // "targets": 0,
            //     "render": function (data, type, row, meta) {
            //
            //         if (data == "ACTIVE") {
            //             return '<span class="badge bg-success">فعال</span>';
            //             // return '<span className="badge bg-danger">Inactive</span>';
            //         }
            //         if (data == "DISABLED") {
            //             return '<span class="badge bg-danger">غیرفعال</span>';
            //         }
            //
            //     }
            //
            // },

            {
                title: "وضعیت ",
                width: "10%",
                data: "clientStatus",
                'className': 'outBox-control',
                "render": function (data, type, row, meta) {

                    if (data == "ACTIVE") {

                        return '  <button id="traffic-btn" type="button" id="add-user-Btn" class="btn btn-icon tablebutton deactive btn-success mr-0">\n' +
                            ' فعال \n' +
                            ' </button>';

                        // return '<div class="btn-group mb-1"> <div class="dropdown" > ' +
                        //     '<button class="btn btn-primary dropdown-toggle me-1" type="button" id="dropdownMenuButton" data-bs-toggle= "dropdown" aria-haspopup="true"aria-expanded="false">' +
                        //     'عملیات' +
                        //     ' </button>' +
                        //     ' <div class="dropdown-menu" aria-labelledby="dropdownMenuButton"> ' +
                        //     '<a class="dropdown-item" href="#">غیرفعال </a>' +
                        //     ' <a class="dropdown-item reset-traffic" href="#">ریست ترافیک مصرفی</a>' +
                        //     ' </div> ' +
                        //     '</div>' +
                        //     '</div>';
                    }
                    if (data == "DISABLED") {

                        return '  <button id="traffic-btn" type="button" id="add-user-Btn" class="btn btn-icon tablebutton activate  btn-danger mr-0">\n' +
                            'غیرفعال \n' +
                            ' </button>';

                        // return '<div class="btn-group mb-1"> <div class="dropdown" > ' +
                        //     '<button class="btn btn-primary dropdown-toggle me-1" type="button" id="dropdownMenuButton" data-bs-toggle= "dropdown" aria-haspopup="true"aria-expanded="false">' +
                        //     'عملیات' +
                        //     ' </button>' +
                        //     ' <div class="dropdown-menu" aria-labelledby="dropdownMenuButton"> ' +
                        //     '<a class="dropdown-item activate" href="#">فعال </a>' +
                        //     ' <a class="dropdown-item reset-traffic" href="#">ریست ترافیک مصرفی</a>' +
                        //     ' </div> ' +
                        //     '</div>' +
                        //     '</div>';
                    }


                }
            },

            {
                title: "",
                width: "10%",
                'className': 'outBox-control',
                "render": function (data, type, row, meta) {

                    return '  <button id="traffic-btn" type="button" id="add-user-Btn" class="btn btn-icon tablebutton  btn-success charge  mr-0">\n' +
                        ' شارژ\n' +
                        ' </button>'
                }

            },

            {
                title: "",
                width: "10%",
                'className': 'outBox-control',
                "render": function (data, type, row, meta) {

                    return '  <button id="traffic-btn" type="button"  id="add-user-Btn" class="btn btn-icon download tablebutton  btn-secondary mr-0">\n' +
                        ' دانلود \n' +
                        ' </button>'
                }

            },


            {
                title: "",
                width: "10%",
                'className': 'outBox-control',
                "render": function (data, type, row, meta) {

                    return '  <button id="traffic-btn" type="button" id="add-user-Btn" class="btn btn-icon reset-traffic tablebutton  btn-primary mr-0">\n' +
                        ' ریست  \n' +
                        ' </button>'
                }

            },

            {
                title: "",
                width: "10%",
                'className': 'outBox-control',
                "render": function (data, type, row, meta) {

                    return '  <button id="traffic-btn" type="button" id="add-user-Btn" class="btn btn-icon tablebutton  btn-danger mr-0">\n' +
                        ' خذف \n' +
                        ' </button>'
                }

            }


        ],


        dom: 'Bfrtip',
        buttons: [
            {
                extend: 'copyHtml5',
                text: '<i class="fa fa-files-o"></i>',
                titleAttr: 'Copy'
            },
            {
                extend: 'excelHtml5',
                text: '<i class="fa fa-file-excel-o"></i>',
                titleAttr: 'Excel'
            },
            {
                extend: 'csvHtml5',
                text: '<i class="fa fa-file-text-o"></i>',
                titleAttr: 'CSV'
            },
            {
                extend: 'pdfHtml5',
                text: '<i class="fa fa-file-pdf-o"></i>',
                titleAttr: 'PDF'
            }

        ],

        direction: 'rtl',

        "language": {
            "decimal": "",
            "emptyTable": "هیچ داده ای در جدول وجود ندارد",
            "info": "نمایش _START_ تا _END_ از _TOTAL_ رکورد",
            "infoEmpty": "نمایش 0 تا 0 از 0 رکورد",
            "infoFiltered": "(فیلتر شده از _MAX_ رکورد)",
            "infoPostFix": "",
            "infoThousands": ",",
            "lengthMenu": "نمایش _MENU_ رکورد",
            "loadingRecords": "در حال بارگزاری...",
            "processing": "در حال پردازش...",
            "search": "فیلتر:",
            "zeroRecords": "رکوردی با این مشخصات پیدا نشد",
            "paginate": {
                "first": "ابتدا",
                "last": "انتها",
                "next": "بعدی",
                "previous": "قبلی"
            },
            "aria": {
                "sortAscending": ": فعال سازی نمایش به صورت صعودی",
                "sortDescending": ": فعال سازی نمایش به صورت نزولی"
            }
        },


        "orderable": true,
        "paging": false,
        // "pageLength": 8,
        searching: true,
        "info": true

    });


    // clientsTable.ajax.url( '/api/v1/user/d4e81806-575e-47cf-b645-b22082ada0f9/enable-client' ).load();


    $('#clientListTable tbody').on('click', 'td.outBox-control button.charge', function () {
        var tr = $(this).closest('tr');
        var row = clientsTable.row(tr);
        var username = row.data().clientId;

        // Show modal
        $("#inlineForm").css("display", "block");

        // Set hidden input variable value
        $("#username").val(username);
        $("#trafficId").val("");

        // Handle the close button functionality
        // $(".close").click(function() {
        //     $("#inlineForm").css("display", "none");
        // });

    });



    $('#clientListTable tbody').on('click', 'td.outBox-control button.charge2', function () {
        var tr = $(this).closest('tr');
        var row = clientsTable1.row(tr);
        var trafficId = row.data().id;
        var capacity = row.data().capacity;
        var expirationDate = row.data().expirationDate;

        $("#capacity").val(capacity);
        $("#expire-date").val(expirationDate);
        $("#trafficId").val(trafficId);





        // Show modal
        $("#inlineForm").css("display", "block");

        // Set hidden input variable value
        // $("#username").val(username);

        // Handle the close button functionality
        // $(".close").click(function() {
        //     $("#inlineForm").css("display", "none");
        // });

    });
    // --------------------------------------



    clientsTable.on('click', 'td.dt-control', function (e) {
        let tr = e.target.closest('tr');
        let row = clientsTable.row(tr);

        if (row.child.isShown()) {
            // This row is already open - close it
            row.child.hide();
        } else {
            // Open this row
            row.child(format(row.data())).show();
        }
    });


    $("#add-traffic-form").submit(function (event) {
        // Get values from input fields
        var id =  $('#trafficId').val();
        var username = $('#username').val();
        var capacity = $('#capacity').val();
        var expirationDate = $('#expire-date').val();


        // Create data object to be sent in POST request
        var postData = {
            id: id,
            username: username,
            capacity: capacity,
            expirationDate: expirationDate,
        };

        // AJAX POST request

        $.ajax({
            url: '/api/v1/user/add-traffic',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(postData),
            success: function (response, textStatus, xhr) {
                $('#trafficId').val("");
                $("#inlineForm").hide();
                clientsTable.ajax.reload();

            },
            error: function (xhr, status, error) {
                $('#trafficId').val("");
            }

        });
        event.preventDefault();
    });


    $('#close-modal-Btn').click(function () {
        $("#inlineForm").hide();

    });


    /*------------------------------------------------ disable client and reset traffic ----------------------*/

    clientsTable.on('click', 'td.outBox-control button.deactive', function () {
        var tr = $(this).closest('tr');
        var row = clientsTable.row(tr);
        var clientId = row.data().clientId;

        $.ajax({
            url: '/api/v1/user/' + clientId + '/disable-client',
            type: 'POST',
            contentType: 'application/json',
            // data: JSON.stringify(postData),
            success: function (response, textStatus, xhr) {
                clientsTable.ajax.reload();
            },
            error: function (xhr, status, error) {

            }

        });
    });


    clientsTable.on('click', 'td.outBox-control button.activate', function () {
        var tr = $(this).closest('tr');
        var row = clientsTable.row(tr);
        var clientId = row.data().clientId;

        $.ajax({
            url: '/api/v1/user/' + clientId + '/enable-client',
            type: 'POST',
            contentType: 'application/json',
            // data: JSON.stringify(postData),
            success: function (response, textStatus, xhr) {
                clientsTable.ajax.reload();
            },
            error: function (xhr, status, error) {

            }

        });
    });







    clientsTable.on('click', 'td.outBox-control button.reset-traffic', function () {
        var tr = $(this).closest('tr');
        var row = clientsTable.row(tr);
        var clientId = row.data().clientId;

        $.ajax({
            url: '/api/v1/user/' + clientId + '/client/rest-wg-transfer',
            type: 'POST',
            contentType: 'application/json',
            // data: JSON.stringify(postData),
            success: function (response, textStatus, xhr) {
                $('#success-msg').text("پیغام‌: ترافیک کاربر با موفقیت ریست شد.");
                $('#success-msg').show();

                setTimeout(function() {
                    $('#success-msg').hide();

                }, 4000);

            },
            error: function (xhr, status, error) {
                $('#failed_msg').text( "پیغام خطا: "  + xhr.responseJSON.message  );
                $('#failed_msg').show();
                setTimeout(function() {
                    $('#failed_msg').hide();

                }, 4000);
            }

        });

    });





    clientsTable.on('click', 'td.outBox-control button.download', function () {
        var tr = $(this).closest('tr');
        var row = clientsTable.row(tr);
        var clientId = row.data().clientId;
        var username = row.data().username;

        $.ajax({
            url: '/api/v1/client/'+ clientId +'/configs',
            type: 'GET',
            xhrFields: {
                responseType: 'blob' // Treat the response as a binary blob
            },
            success: function (data) {
                const url = window.URL.createObjectURL(data);
                const a = document.createElement('a');
                a.href = url;
                a.download = username + '.zip'; // Specify the desired file name
                document.body.appendChild(a);
                a.click();
                window.URL.revokeObjectURL(url);
            },
            error: function (error) {
                console.error('Error fetching the zip file:', error);
            }

        });

    });

});




function format(d) {
    // `d` is the original data object for the row

    return (
        '<div class="table-responsive" style="font-size: 13px">\n' +
        '          <!--                            <table class="table" id="policyListTable1">-->\n' +
        '          <table class=" custom-table table table-striped table-bordered table-hover "  id="clientListTable1"  style="direction:rtl; width: 70%; text-align: center;  margin: 0 auto; "   ></table>\n' +
        '          <!--                            </table>-->\n' +
        '        </div>' +
        '<script>' +
        '$(document).ready(function () {\n' +
        '    clientsTable1 = $(\'#clientListTable1\').DataTable({\n' +
        '        // "processing": true,\n' +
        '        // "serverSide": true,\n' +
        '        // "ajax": {\n' +
        '        //\n' +
        '        //     "url": "/api/v1/user/all",\n' +
        '        //     "type": "GET",\n' +
        '        //     "data": function (d) {\n' +
        '        //         alert(d);\n' +
        '        //         var customParams = getMyNewParameters();\n' +
        '        //         var params = jQuery.isEmptyObject(customParams) ? d : customParams;\n' +
        '        //         return params;\n' +
        '        //     },\n' +
        '        //     dataSrc: \'\'\n' +
        '        //\n' +
        '        // },\n' +
        '\n' +
        '\n' +
        '        ajax: {\n' +
        '            url: \'/api/v1/user/' + d.clientId + '/traffics\',\n' +
        '            dataSrc: \'\'\n' +
        '        },\n' +

        '        columns: [\n' +
        '            //\n' +
        '            // {\n' +
        '            //     data: "true",\n' +
        '            //     defaultContent: \'\',\n' +
        '            //     "render": function (data, type, row, meta) {\n' +
        '            //         if (data == true) {\n' +
        '            //             return \' <div style="font-weight: bold; color: #00A000"> <img src="/icon/ok.ico" alt="" width="15" height="15" data-toggle="tooltip" title="طرح انتخاب شده"> طرح انتخاب شده</div>\';\n' +
        '            //         } else {\n' +
        '            //             return \'\';\n' +
        '            //         }\n' +
        '            //     }\n' +
        '            //\n' +
        '            // },\n' +

        '            {data: "capacityView", title: "ظرفیت", "width": "15%"},\n' +
        '            {data: "expirationDateView", title: "تاریخ انقضا", "width": "15%"},\n' +
        '            {data: "transferRx", title: "حجم آپلود", "width": "15%"},\n' +
        '            {data: "transferTx", title: "حجم دانلود", "width": "15%"},\n' +
        '            {data: "createAt", title: "تاریخ ایجاد", "width": "15%"},\n' +
        '\n' +
        '            {\n' +
        '                title: "وضعیت ",\n' +
        '                data: "status",\n' +
        '                width: "13%",\n' +
        '                // className: "centeralign",\n' +
        '                // "targets": 0,\n' +
        '                "render": function (data, type, row, meta) {\n' +
        '\n' +
        '                    if (data == "ACTIVE") {\n' +
        '                        return \'<span class="badge bg-success">فعال</span>\';\n' +
        '                        // return \'<span className="badge bg-danger">Inactive</span>\';\n' +
        '                    }\n' +
        '                    else if (data == "CREATED") {\n' +
        '                        return \'<span class="badge bg-info">غیر فعال</span>\';\n' +
        '                    }\n' +
        '                    else if (data == "NO_CAPACITY") {\n' +
        '                        return \'<span class="badge bg-danger">ظرفیت ندارد</span>\';\n' +
        '                    }\n' +
        '                    else if (data == "EXPIRED") {\n' +
        '                        return \'<span class="badge bg-warning">منقضی</span>\';\n' +
        '                    }\n' +
        '\n' +
        '                }\n' +
        '\n' +
        '            },\n' +
        '\n' +

        '\n' +
        '            {\n' +
        '                title: "",\n' +
        '                width: "10%",\n' +
        '                data: "clientId",\n' +
        '                className: "outBox-control",\n' +
        '                "render": function (data, type, row, meta) {\n' +
        '                    return \'<button type="button" id="add-user-Btn" class="btn btn-icon tablebutton  btn-success charge2 mr-0" ><i class="bi bi-x"></i></button>\';\n' +
        '                }\n' +
        '            }\n' +
        '        ],\n' +
        '\n' +
        '\n' +
        '        dom: \'Bfrtip\',\n' +
        '        buttons: [\n' +
        '            {\n' +
        '                extend: \'copyHtml5\',\n' +
        '                text: \'<i class="fa fa-files-o"></i>\',\n' +
        '                titleAttr: \'Copy\'\n' +
        '            },\n' +
        '            {\n' +
        '                extend: \'excelHtml5\',\n' +
        '                text: \'<i class="fa fa-file-excel-o"></i>\',\n' +
        '                titleAttr: \'Excel\'\n' +
        '            },\n' +
        '            {\n' +
        '                extend: \'csvHtml5\',\n' +
        '                text: \'<i class="fa fa-file-text-o"></i>\',\n' +
        '                titleAttr: \'CSV\'\n' +
        '            },\n' +
        '            {\n' +
        '                extend: \'pdfHtml5\',\n' +
        '                text: \'<i class="fa fa-file-pdf-o"></i>\',\n' +
        '                titleAttr: \'PDF\'\n' +
        '            }\n' +
        '\n' +
        '        ],\n' +
        '\n' +
        '        direction: \'rtl\',\n' +
        '\n' +
        '        "language": {\n' +
        '            "decimal": "",\n' +
        '            "emptyTable": "هیچ داده ای در جدول وجود ندارد",\n' +
        '            "info": "نمایش _START_ تا _END_ از _TOTAL_ رکورد",\n' +
        '            "infoEmpty": "نمایش 0 تا 0 از 0 رکورد",\n' +
        '            "infoFiltered": "(فیلتر شده از _MAX_ رکورد)",\n' +
        '            "infoPostFix": "",\n' +
        '            "infoThousands": ",",\n' +
        '            "lengthMenu": "نمایش _MENU_ رکورد",\n' +
        '            "loadingRecords": "در حال بارگزاری...",\n' +
        '            "processing": "در حال پردازش...",\n' +
        '            "search": "فیلتر:",\n' +
        '            "zeroRecords": "رکوردی با این مشخصات پیدا نشد",\n' +
        '            "paginate": {\n' +
        '                "first": "ابتدا",\n' +
        '                "last": "انتها",\n' +
        '                "next": "بعدی",\n' +
        '                "previous": "قبلی"\n' +
        '            },\n' +
        '            "aria": {\n' +
        '                "sortAscending": ": فعال سازی نمایش به صورت صعودی",\n' +
        '                "sortDescending": ": فعال سازی نمایش به صورت نزولی"\n' +
        '            }\n' +
        '        },\n' +
        '\n' +
        '\n' +
        '        "paging": false,\n' +
        '        "pageLength": 3,\n' +
        '        searching: false,\n' +
        '\n' +
        '    });' +
        '    });' +
        '</script>'
    );
    // return (
    //     '<dl>' +
    //     '<dt>Full name:</dt>' +
    //     '<dd>' +
    //     d.fullName +
    //     '</dd>' +
    //     '<dt>Extension number:</dt>' +
    //     '<dd>' +
    //     d.fullName +
    //     '</dd>' +
    //     '<dt>Extra info:</dt>' +
    //     '<dd>And any further details here (images etc)...</dd>' +
    //     '</dl>'
    // );
}



// ------------------------ new -------------------------



//
// var myCustomParams = {};
// var getMyNewParameters = function(){
//     return myCustomParams;
// }
//
// var setMyNewParameters = function(params){
//     myCustomParams = params;
// }

//----------------------------------------------------------------- Policy Data Table ------------------------------------


// var docIsEditable;
// $.ajax({
//     type: 'GET',
//     // url: 'registerPolicyActoinGetIsEditable',
//     url: 'api/v1/user/all',
//
//     dataType: 'json',
//     success: function (field) {
//         docIsEditable = field.docIsEditable;
//     }
// });

//-------------------------------------------------------------
//
// var table ;
// $(document).ready(function () {
//     table = $('#policyListTable').DataTable({
//
//         // "processing": true,
//         // "serverSide": true,
//         "processing": true,
//         "serverSide": true,
//         "ajax": {
//
//             "url": "/api/v1/user/all",
//             "type": "GET",
//             "data": function ( d ) {
//
//                 // alert(d);
//                 var customParams = getMyNewParameters();
//                 var params = jQuery.isEmptyObject(customParams) ? d : customParams;
//                 return params;
//             },
//             // dataSrc: 'userDto'
//
//         },
//
//
//         columns: [
//             // {
//             //     data:"selected",
//             //     defaultContent: '',
//             //     "render": function ( data, type, row, meta ) {
//             //         if (data == true) {
//             //             return ' <div style="font-weight: bold; color: #00A000"> <img src="/icon/ok.ico" alt="" width="15" height="15" data-toggle="tooltip" title="طرح انتخاب شده"> طرح انتخاب شده</div>';
//             //         }else{
//             //             return '';
//             //         }
//             //     }
//             //
//             // },
//             {data: "fullName" ,title: "نام", "width": "15%" },
//             {data: "username",title: "نام کاربری" ,"width": "32%"},
//             {data: "enabled",title: "وصعیت", "width": "15%" },
//             {data: "clientId",title: "شناسه" ,"width": "15%"}
//


//
//             // {   title: "وضعیت",
//             //     "data": "state",
//             //     className: "centeralign",
//             //     // "targets": 0,
//             //     "render": function ( data, type, row, meta ) {
//             //
//             //         if (data == 1) {
//             //             return '<img src="/icon/noTakhsis.png" alt="" width="17" height="17" data-toggle="tooltip" title="در انتظار پاسخ" >';
//             //         }
//             //         if (data == 2) {
//             //             return '<img src="/icon/darentezarpasokh.ico" alt="" width="17" height="17" data-toggle="tooltip" title="در انتظار پاسخ" >';
//             //         }
//             //         if (data == 3) {
//             //             return '<img src="/icon/working.png" alt="" width="17" height="17" data-toggle="tooltip" title="در حال انجام">';
//             //         }
//             //         if (data == 4 ){
//             //             return '<img src="/icon/darentezartaeedpasokh.png" alt="" width="17" height="17" data-toggle="tooltip" title="در انتظار تایید پاسخ">';
//             //         }
//             //         if (data == 5) {
//             //             return '<img src="/icon/radshodeh.ico" alt="" width="17" height="17" data-toggle="tooltip" title="رد پاسخ">';
//             //         }
//             //         if (data == 6) {
//             //             return '<img src="/icon/ok.ico" alt="" width="17" height="17" data-toggle="tooltip" title="تایید شده">';
//             //         }
//             //         if (data == 7) {
//             //             return '<img src="/icon/gereftehshodeh.ico" alt="" width="17" height="17" data-toggle="tooltip" title="گرفته شده">';
//             //         }
//             //     }
//             //
//             //
//             // },
//
//             // {
//             //     "className":      'outBox-control',
//             //     "render": function ( data, type, row, meta ) {
//             //         if(docIsEditable == true) {
//             //
//             //             return " <div class='form-group' style='margin-bottom:0px'> " +
//             //                 " <button class='btn btn-icon tablebutton  btn-success mr-0' type='button' name='selectPolicy' data-toggle='tooltip' title='انتخاب طرح' > " +
//             //                 " <i class='icon-ok'></i> </button> <button type='button' class='btn btn-icon tablebutton   btn-info mr-0' name='downloadGuid' data-toggle='tooltip' title='دانلود فایل راهنما'> " +
//             //                 " <i class='icon-download-alt'></i></button> " +
//             //                 " </div>";
//             //         }else{
//             //             return " <div class='form-group' style='margin-bottom:0px'> " +
//             //                 " <button class='btn btn-icon tablebutton  btn-success mr-0 disabled' type='button' name='selectPolicy' data-toggle='tooltip' title='انتخاب طرح' > " +
//             //                 " <i class='icon-ok'></i> </button> <button type='button' class='btn btn-icon tablebutton   btn-info mr-0 disabled' name='downloadGuid' data-toggle='tooltip' title='دانلود فایل راهنما'> " +
//             //                 " <i class='icon-download-alt'></i></button> " +
//             //                 " </div>";
//             //         }
//             //
//             //     },
//             //
//             // }
//
//         ],
//
//
//         dom: 'Bfrtip',
//         buttons: [
//             {
//                 extend: 'copyHtml5',
//                 text: '<i class="fa fa-files-o"></i>',
//                 titleAttr: 'Copy'
//             },
//             {
//                 extend: 'excelHtml5',
//                 text: '<i class="fa fa-file-excel-o"></i>',
//                 titleAttr: 'Excel'
//             },
//             {
//                 extend: 'csvHtml5',
//                 text: '<i class="fa fa-file-text-o"></i>',
//                 titleAttr: 'CSV'
//             },
//             {
//                 extend: 'pdfHtml5',
//                 text: '<i class="fa fa-file-pdf-o"></i>',
//                 titleAttr: 'PDF'
//             }
//
//         ],
//
//
//
//
//
//
//
//         direction: 'rtl',
//
//         "language": {
//             "decimal":        "",
//             "emptyTable":     "هیچ داده ای در جدول وجود ندارد",
//             "info":           "نمایش _START_ تا _END_ از _TOTAL_ رکورد",
//             "infoEmpty":      "نمایش 0 تا 0 از 0 رکورد",
//             "infoFiltered":   "(فیلتر شده از _MAX_ رکورد)",
//             "infoPostFix":    "",
//             "infoThousands":  ",",
//             "lengthMenu":     "نمایش _MENU_ رکورد",
//             "loadingRecords": "در حال بارگزاری...",
//             "processing":     "در حال پردازش...",
//             "search":         "فیلتر:",
//             "zeroRecords":    "رکوردی با این مشخصات پیدا نشد",
//             "paginate": {
//                 "first":    "ابتدا",
//                 "last":     "انتها",
//                 "next":     "بعدی",
//                 "previous": "قبلی"
//             },
//             "aria": {
//                 "sortAscending":  ": فعال سازی نمایش به صورت صعودی",
//                 "sortDescending": ": فعال سازی نمایش به صورت نزولی"
//             }
//         },
//
//
//         "orderable":      true,
//         "paging":         false,
//         searching: false,
//         "info":     false
//
//     });
//
// //        $('#dataTables-example tbody')
// //                .on( 'mouseenter', 'td', function () {
// //                    alet(table);
// //                    var colIdx = table.cell(this).index().column;
// //
// //                    $( table.cells().nodes() ).removeClass( 'highlight' );
// //                    $( table.column( colIdx ).nodes() ).addClass( 'highlight' );
// //
// //                } )
// //
// //                .on( 'mouseleave', function () {
// //                    $( table.cells().nodes() ).removeClass( 'highlight' );
// //                } )
// //
//
//
//
//
//
//     // function format(d) {
//     //     return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
//     //
//     //         '<tr>' +
//     //         '<td>صادرکننده :</td>' +
//     //         '<td>' + d.taskCreatorStr + '</td>' +
//     //         '</tr>' +
//     //         '<tr>' +
//     //         '<td>دریافت کننده :</td>' +
//     //         '<td>' + d.taskRecieverStr + '</td>' +
//     //         '</tr>' +
//     //         '<tr>' +
//     //         '<td>شماره تیکت:</td>' +
//     //         '<td>' + d.ticketCode + '</td>' +
//     //         '</tr>' +
//     //         '<tr>' +
//     //         '<td>نوع:</td>' +
//     //         '<td>' + d.taskTypeStr + '</td>' +
//     //         '</tr>' +
//     //         '<tr>' +
//     //         '<td>اولویت:</td>' +
//     //         '<td>' + d.priorityStr + '</td>' +
//     //         '</tr>' +
//     //         '<tr>' +
//     //         '<td>مهلت تا تاریخ :</td>' +
//     //         '<td>' + d.deadlineDate + '</td>' +
//     //         '</tr>' +
//     //         '<tr>' +
//     //         '<td> زمان پیشنهادی:</td>' +
//     //         '<td>'+ d.suggestedTime+'(دقیقه)'+'</td>' +
//     //         '</tr>' +
//     //         '<tr>' +
//     //         '<td> توضیحات :</td>' +
//     //         '<td>'+ d.description+'</td>' +
//     //         '<tr>' +
//     //         '<td style="color: red"> تاریخ تایید شده انجام وظیفه  :</td>' +
//     //         '<td style="color: red">'+ d.dateDone+'</td>' +
//     //         '<tr>' +
//     //         '<td style="color: red"> مدت زمان تایید شده انجام وظیفه :</td>' +
//     //         '<td style="color: red">'+ d.durationDone+'</td>' +
//     //         '</tr>' +
//     //         '</table>';
//     // }
//
//
//
//
//
//
//
//
//     // $('#dataTables-example tbody').on('click', 'td.response-control', function () {
//     //
//     //     var tr = $(this).closest('tr');
//     //     var row = table.row(tr);
//     //     // alert(row.data().id);
//     //     $("#taskID").val(row.data().id);
//     //     $("#responseForm").submit();
//     //
//     // });
//
//
//
//
//     $('#policyListTable tbody').on('click', 'td.details-control', function () {
//
//         var tr = $(this).closest('tr');
//         var row = table.row(tr);
//
//         if (row.child.isShown()) {
//             // This row is already open - close it
//             row.child.hide();
//             tr.removeClass('shown');
//         }
//         else {
//             // Open this row
//             row.child(format(row.data())).show();
//             tr.addClass('shown');
//         }
//     });
//
//     var taskInfo = "لیست طرح ها";
//     $('#policyListTable').append('<caption class="caption" >'+taskInfo+'</caption>');
//
//
//
//
// //--------------------------------------select Policy -----------------------------------------------------------------
//
//     $('#policyListTable tbody').on('click', 'td.outBox-control button.btn-success', function () {
//         var tr = $(this).closest('tr');
//         var row = table.row(tr);
//         var policyID = row.data().id;
//         window.open(action='registerPolicyActionSelectPolicy?policyID='+policyID+'','_self',null,null)
//
//     });
// //-----------------------------------------------------------------------------------------------------
//
//
// });
//
//
//
// function callMyAction() {
//
//     setMyNewParameters( {'systemID':$('#systemID').val(),'tilte':$('#tilte').val(),'userID':$('#userID').val(),'taskTypeId':$('#taskTypeId').val(),'ticketCode':$('#ticketCode').val(),'priorityID':$('#priorityID').val(),'moduleID':$('#moduleID').val(),'fromDate':$('#fromDate').val(),'toDate':$('#toDate').val(),'taskState':$('#taskStateID').val()});
//     table.ajax.url( 'TaskActionSearchInboxTask' ).load();
//
// }
//
//
//
// jQuery(document).ready(function(){
//     $("#div-2").collapse();
//
// });
//
//
//
