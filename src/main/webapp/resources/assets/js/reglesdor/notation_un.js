$(window).load(function () {
    var context = $('.brand').attr("href");
    var accordionCount = Number($('#accodionCount').text());
    var idAxe = $('#identifiantAxe').text();
    var idEntreprise = $('#divDuCodeEntreprise p').attr('id');

    changerCouleurAvecUrl(context + 'regles-or/notation/value/' + idAxe + '/' + idEntreprise, 'indiquePourAxe');

    for (var i = 1; i < (accordionCount + 1); i++){
        var idEvaluation = $('#identifierEvaluation_' + i).text();

        changerCouleurAvecUrl(context + 'regles-or/evaluation/value/' + idEvaluation + '/' + idEntreprise, 'indPourDom' + i);
    }

    $(document).on('change', '.onoffswitch-checkbox', function (e) {
        e.preventDefault();

        var i = Number($(this).attr('id').slice(-5, -4));
        var j = Number($(this).attr('id').slice(-3, -2));
        var k = Number($(this).attr('id').slice(-1));
        var idQuestion = $('#identifierQuest_' + i + '_' + j).text();
        var idReponse = $('#identifierRep_' + i + '_' + j + '_' + k).text();
        var idEvaluation = $('#identifierEvaluation_' + i).text();

        $.ajax({
            url: context + 'regles-or/notation/' + idAxe + '/' + idEntreprise + '/' + idEvaluation + '/' + idQuestion + '/' + idReponse,
            method: 'GET',
            data: null,
            success: function (data) {
                changerCouleurSimple('indiquePourAxe', data.object.valeurAxe);
                changerCouleurSimple('indPourDom' + i, data.object.valeurEval);
            },
            error: function (msg) {
                alert('Notation impossible');
            }
        });
    });
});

function changerCouleurAvecUrl(url, id) {
    $.ajax({
        url : url,
        data: null,
        method: 'GET',
        success: function (data) {
            changerCouleurSimple(id, data.valeur);
        },
        error: function (msg) {
            alert("Valeur introuvable!");
        }
    });
}

function changerCouleurSimple(id, valeur) {
    var poucentage = (valeur * 100)/3;
    var val1 = 100/3;
    var val2 = (100/3)*2;

    if(poucentage <= val1){$('#'+id).attr('class', 'myIcon icon-red');}
    else if((poucentage > val1) && (poucentage <= val2)){$('#'+id).attr('class', 'myIcon icon-orange');}
    else if((poucentage > val2) && (poucentage <= 100)){$('#'+id).attr('class', 'myIcon icon-greensea');}
}