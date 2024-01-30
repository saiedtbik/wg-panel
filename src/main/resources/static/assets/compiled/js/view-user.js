
let userTable;
$(document).ready(function () {
    userTable = $('#policyListTable').DataTable({
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
            headers: {'Authorization': "Bearer " + localStorage.getItem("token")},

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
            {data: "fullName", title: "نام", width: "10%"},
            // {data: "clientId", title: "شماره کلاینت", width: "40%"},
            {data: "username", title: "نام کاربری", width: "5%"},
            {data: "mobileNum", title: "موبایل", width: "10%"},
            {data: "email", title: "ایمیل", width: "12%"},
            {data: "jalaliCreateDate", title: "تاریخ ایجاد", width: "10%"},


            {
                title: "وضعیت",
                data: "enabled",
                width: "8%",
                // className: "centeralign",
                // "targets": 0,
                "render": function (data, type, row, meta) {

                    if (data == true) {
                        return '<span class="badge bg-success">فعال</span>';
                        // return '<span className="badge bg-danger">Inactive</span>';
                    }
                    if (data == false) {
                        return '<span className="badge bg-danger">غیرفعال</span>';
                    }

                }

            },

            // {
            //     title: "",
            //     width: "5%",
            //     data: 'enabled',
            //     'className': 'outBox-control',
            //     "render": function (data, type, row, meta) {
            //         return '<div class="btn-group mb-1"> <div class="dropdown" > ' +
            //             '<button class="btn btn-primary dropdown-toggle me-1" type="button" id="dropdownMenuButton" data-bs-toggle= "dropdown" aria-haspopup="true"aria-expanded="false">' +
            //             'عملیات' +
            //             ' </button>' +
            //             ' <div class="dropdown-menu" aria-labelledby="dropdownMenuButton"> ' +
            //             ' <a class="dropdown-item deactive" href="#">غیرفعال</a>' +
            //             ' <a class="dropdown-item" href="#">حذف </a>' +
            //             ' <a class="dropdown-item" href="#">ویرایش</a>' +
            //             ' <a class="dropdown-item" href="#">بازیابی رمز عبور</a>' +
            //             ' </div> ' +
            //             '</div>' +
            //             '</div>';
            //     }
            // }
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
        "paging": true,
        "pageLength": 8,
        searching: true,
        "info": true

    });


});


$(document).ready(function() {
    $("#exit").click(function(){
        localStorage.removeItem("token");
        window.location.assign("/login");
    });
});


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
// // function callMyAction() {
// //
// //     setMyNewParameters( {'systemID':$('#systemID').val(),'tilte':$('#tilte').val(),'userID':$('#userID').val(),'taskTypeId':$('#taskTypeId').val(),'ticketCode':$('#ticketCode').val(),'priorityID':$('#priorityID').val(),'moduleID':$('#moduleID').val(),'fromDate':$('#fromDate').val(),'toDate':$('#toDate').val(),'taskState':$('#taskStateID').val()});
// //     table.ajax.url( 'TaskActionSearchInboxTask' ).load();
// //
// // }
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
