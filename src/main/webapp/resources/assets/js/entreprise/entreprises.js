$(window).load(function(){

    //initialize editable datatable

    var context = $('.brand').attr("href");
    var table2 = $('#editable-usage');

    var oTable = $('#editable-usage').DataTable({
        "aoColumnDefs": [
            { 'bSortable': false, 'aTargets': [ "no-sort" ] }
        ]
    });


    table2.on('click', '.delete', function (e) {
        e.preventDefault();

        var id = $(this).attr("id");

        if (confirm("êtes-vous sûr de la suppression?") == false) {
            return;
        }

        $.ajax({
            url: context + 'entreprises/delete/' + id,
            method: 'GET',
            data: null,
            success: function (data) {
                if (data.msg === 'ok') {
                    alert("Supprimé!");
                }
                else {
                    alert("Suppression imposibble!");
                }

                location.href = context + 'entreprises';
            },
            error: function () {
                alert("Suppression imposibble!");
                location.href = context + 'entreprises';
            }
        });

    });


    //*initialize editable datatable

});