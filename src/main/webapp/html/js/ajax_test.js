$(document).ready(function () {
    var fileUpload = $("#fileUpload");
    var result = $("#result");

    fileUpload.fileupload({
        dataType:'json',
        add:function(e, data){
            var file = data.files[0];
            file.id = getId();
            result.append("<p id='" + file.id + "'>" + file.name + " " + file.size +"</p>");
            data.submit();
        },
        done:function(e, data) {
            var response = data.result,
                status = data.textStatus,
                file = data.files[0];

            var p = $(result).find("#" + file.id);
            if (status == "success") {
                p.before("<img width='100' height='100' src='" + response.url + "' alt='img' />");
                p.text(p.text() + " " + "loaded");
            } else {
                p.text(p.text() + " " + "failed");
            }
        },
        always:function(e, data) {
            var response = data.result;
        }
    });

    var id = 0;

    function getId() {
        return id++;
    }

});