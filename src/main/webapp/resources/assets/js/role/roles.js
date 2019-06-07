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

        var idRole = $(this).attr("id");

        if (confirm("Are you sure?") == false) {
            return;
        }

        $.ajax({
            url: context + 'roles/delete/' + idRole,
            method: 'DELETE',
            data: null,
            success: function (data) {
                if (data.msg === 'ok') {
                    alert("Supprimmé!");
                    location.href = context + 'roles';
                }
                else {
                    alert("Suppression imposibble!");
                    location.href = context + 'roles';
                }
            },
            error: function () {
                alert("Suppression imposibble!");
                location.href = context + 'roles';
            }
        });

    });


    //*initialize editable datatable

});