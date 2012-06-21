$(document).ready(function () {
        $(document).ready(function() {
            $('#catTable').dataTable({
                "bProcessing": true,
                "bServerSide": true,
                "bFilter":false,
                "bSort": false,
                "sPaginationType": "full_numbers",
                "iFullNumbersShowPages": 10,
                "sAjaxSource": 'category/ajax'
//                "oLanguage": {
//                    "sUrl": "html/json/dataTable_${lang}.json"
//                }


            });
        } );
    });