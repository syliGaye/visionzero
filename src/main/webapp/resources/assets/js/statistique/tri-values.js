$(window).load(function () {

    var context = $('.brand').attr("href");

    // Continents

    $.ajax({
        url: context + 'statistiques/continents',
        method: 'GET',
        data: null,
        success: function (data) {
            $('#triContinent').html("");
            var options = '';

            data.result.forEach(function (t) {
                options += '<option value="' + t.idContinent + '">' + t.libelleContinent + '</option>';
            });

            $('#triContinent').html('<option value="">Choisir un Continent</option>' + options);
        },
        error: function () {
            alert("Données Continents impossible!");
        }
    });

    // * Continents

    // Raison Sociales

    $.ajax({
        url: context + 'statistiques/raisonsociales',
        method: 'GET',
        data: null,
        success: function (data) {
            $('#triRaisonSociale').html("");
            var options = '';

            data.result.forEach(function (t) {
                options += '<option value="' + t.idRaisonSociale + '">' + t.libelleRaisonSociale + '</option>';
            });

            $('#triRaisonSociale').html('<option value="">Choisir une Raison Sociale</option>' + options);
        },
        error: function () {
            alert("Données Raison Sociales impossible!");
        }
    });

    // * Raison Sociales

    // Secteur d'Activités

    $.ajax({
        url: context + 'statistiques/secteuractivites',
        method: 'GET',
        data: null,
        success: function (data) {
            $('#triSecteurActivite').html("");
            var options = '';

            data.result.forEach(function (t) {
                options += '<option value="' + t.idSecteurActivite + '">' + t.libelleSecteurActivite + '</option>';
            });

            $('#triSecteurActivite').html('<option value="">Choisir un Secteur d\'Activit&eacute;</option>' + options);
        },
        error: function () {
            alert("Données Secteur d'Activités impossible!");
        }
    });
    // * Secteur d'Activités

    // Pays
    getPays(context);

    $('#triContinent').on('change', function (e) {
        e.preventDefault();
        getPays(context);
    }).trigger('change');
    // * Pays

    // Entreprise
    getEntreprises(context);

    $('#triPays').on('change', function (e) {
        e.preventDefault();
        getEntreprises(context);
    });

    $('#triSecteurActivite').on('change paste keyup', function (e) {
        e.preventDefault();
        getEntreprises(context);
    });

    $('#triRaisonSociale').on('change paste keyup', function (e) {
        e.preventDefault();
        getEntreprises(context);
    });
    // * Entreprise

});

function getEntreprises(ctx) {
    var pays = $('#triPays').val();
    var secAc = $('#triSecteurActivite').val();
    var raiSoc = $('#triRaisonSociale').val();

    if (pays === ''){pays = 'null';}
    if (secAc === ''){secAc = 'null';}
    if (raiSoc === ''){raiSoc = 'null';}

    $.ajax({
        url: ctx + 'statistiques/entreprises/' + pays + '/' + raiSoc + '/' + secAc ,
        method: 'GET',
        data: null,
        success: function (data) {
            $('#triEntreprise').html("");
            var options = '';

            data.result.forEach(function (t) {
                options += '<option value="' + t.codeEntreprise + '">' + t.nomEntreprise + '</option>';
            });

            $('#triEntreprise').html('<option value="">Choisir une Entreprise</option>' + options);
        },
        error: function () {
            alert("Données Entreprises impossible!");
        }
    });
}

function getPays(ctx) {
    var continent = $('#triContinent').val();

    if (continent === ''){continent = 'null';}

    $.ajax({
        url: ctx + 'statistiques/pays/' + continent,
        method: 'GET',
        data: null,
        success: function (data) {
            $('#triPays').html("");
            var options = '';

            data.result.forEach(function (t) {
                options += '<option value="' + t.idPays + '">' + t.libellePays + '</option>';
            });

            $('#triPays').html('<option value="">Choisir un Pays</option>' + options);
        },
        error: function () {
            alert("Données Pays impossible!");
        }
    });
}