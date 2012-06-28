$(document).ready(function () {
    var photoHolder = $("#user_photo"),
        photoUrlParam = $("#photo_url_param"),
        dataOfBirth = $("#dataOfBirth"),
        photoUploader = $("#photoUpload");

    dataOfBirth.datepicker({
        dateFormat: "dd-mm-yy",
//        minDate:"-100Y",
//        maxDate:"-7Y",
        yearRange: "-100:-7",
        defaultDate: "-20Y",
        changeMonth: true,
        changeYear: true,
        showOn: "button",
        buttonImage: "html/image/calendar.gif",
        buttonImageOnly: true
    });

    photoUploader.fileupload({
        dataType:'json',
        done:function(e, data) {
            var response = data.result,
                status = data.textStatus;

            if (status == "success") {
                photoHolder.attr("src", response.url);
                photoUrlParam.val(response.url);
            } else {
                alert("Bad image");
            }
        },
        submit: function () {
            photoHolder.attr("src", "html/image/loading_big.gif");
        }



    });


});