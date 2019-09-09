$(window).load(function(){
    var context = $('.brand').attr("href");
    var table2 = $('#editable-usage');
    var axe = '';

    $(document).on('click', '.myIcon', function (e) {
        e.preventDefault();
        var index = Number($(this).attr('id').slice(-1));
        axe = $('#idForAxe'+index).text();
        //$('#boutonpourmodal').click();
    });

    $('#validerPourNotation').on('click', function (e) {
        e.preventDefault();
        var entreprise = $('#entrepriseForNotation').val();

        if (entreprise !== '') location.href = context + 'regles-or/notation/' + axe + '/' + entreprise;
        else alert('Choisir une entreprise !');
    });


    //initialize editable datatable

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
            url: context + 'axes/delete/' + id,
            method: 'GET',
            data: null,
            success: function (data) {
                if (data.msg === 'ok') {
                    alert("Supprimé!");
                }
                else {
                    alert("Suppression imposibble!");
                }

                location.href = context + 'axes';
            },
            error: function () {
                alert("Suppression imposibble!");
                location.href = context + 'axes';
            }
        });

    });


    //*initialize editable datatable
});