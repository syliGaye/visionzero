$(window).load(function () {

    var context = $('.brand').attr("href");
    var entreprise = '';
    var pays = '';
    var raisonsociale = '';
    var secteuractivite = '';

    $('#boutonTri').on('click', function (e) {
        e.preventDefault();

        entreprise = $('#triEntreprise').val();
        pays = $('#triPays').val();
        raisonsociale = $('#triRaisonSociale').val();
        secteuractivite = $('#triSecteurActivite').val();

        drawLineChart(context, entreprise, pays, raisonsociale, secteuractivite);
        drawBarChart(context, entreprise, pays, raisonsociale, secteuractivite);
        drawPieChart(context, entreprise, pays, raisonsociale, secteuractivite);

        $('#fermerLeTri').click();

    });

    drawLineChart(context, entreprise, pays, raisonsociale, secteuractivite);
    drawBarChart(context, entreprise, pays, raisonsociale, secteuractivite);
    drawPieChart(context, entreprise, pays, raisonsociale, secteuractivite);

});

function drawPieChart(context, entreprise, pays, raisonsociale, secteuractivite) {

    if (entreprise === '') entreprise = 'null';
    if (pays === '') pays = 'null';
    if (raisonsociale === '') raisonsociale = 'null';
    if (secteuractivite === '') secteuractivite = 'null';

    $.ajax({
        url: context + 'statistiques/pie/' + entreprise + '/' + pays + '/' + secteuractivite + '/' + raisonsociale,
        method: 'GET',
        data: null,
        success: function (data) {
            var dataPie = [];
            var colorsPie = [];

            if (data.result !== null) {
                // Initialize Pie Chart
                var index = 0;

                data.result.forEach(function (t) {
                    dataPie.push({
                        label: 'Axe ' + (index + 1),
                        data: t.valeurNote
                    });

                    colorsPie.push(t.hexCouleur);

                    index++;
                });

                var options = {
                    series: {
                        pie: {
                            show: true,
                            innerRadius: 0,
                            stroke: {
                                width: 0
                            },
                            label: {
                                show: true,
                                threshold: 0.05
                            }
                        }
                    },
                    colors: colorsPie,
                    grid: {
                        hoverable: true,
                        clickable: true,
                        borderWidth: 0,
                        color: '#ccc'
                    },
                    tooltip: true,
                    tooltipOpts: { content: '%s: %p.0%' }
                };

                var plot = $.plot($("#pie-chart"), dataPie, options);

                $(window).resize(function() {
                    // redraw the graph in the correctly sized div
                    plot.resize();
                    plot.setupGrid();
                    plot.draw();
                });
                // * Initialize Pie Chart
            }
            else {
                alert('Données vides !');
            }
        },
        error: function (msg) {
            alert('Impossible d\'afficher les graphiques!');
        }
    });
}

function drawBarChart(context, entreprise, pays, raisonsociale, secteuractivite) {

    if (entreprise === '') entreprise = 'null';
    if (pays === '') pays = 'null';
    if (raisonsociale === '') raisonsociale = 'null';
    if (secteuractivite === '') secteuractivite = 'null';

    $.ajax({
        url: context + 'statistiques/bar/' + entreprise + '/' + pays + '/' + secteuractivite + '/' + raisonsociale,
        method: 'GET',
        data: null,
        success: function (data) {
            var dataBar = [];
            var labelBar = [];
            var colorsBar = [];


            if (data.result !== null) {
                var index = 0;

                $('#bar-example').html('');

                data.result.forEach(function (t) {
                    var couleurs = [];
                    var valeurs = [];
                    var libelles = [];
                    var nomEntreprise = t.nomEntreprise;

                    if (t.axeOneLists !== null){
                        t.axeOneLists.forEach(function (t2) {
                            valeurs.push(t2.notationAxeOneList.valeurNotationAxe);
                            couleurs.push(t2.couleur.hexCouleur);
                            libelles.push(t2.libelleAxe);
                        });
                    }

                    dataBar.push({
                        y: nomEntreprise,
                        a: valeurs[0],
                        b: valeurs[1],
                        c: valeurs[2],
                        d: valeurs[3],
                        e: valeurs[4],
                        f: valeurs[5],
                        g: valeurs[6]
                    });
                    labelBar = libelles;
                    colorsBar = couleurs;
                });

                // Morris bar chart

                Morris.Bar({
                    element: 'bar-example',
                    data: dataBar,
                    xkey: 'y',
                    ykeys: ['a', 'b', 'c', 'd', 'e', 'f', 'g'],
                    labels: labelBar,
                    barColors:colorsBar
                });
            }
            else {
                alert('Données vides !');
            }
        },
        error: function (msg) {
            alert('Impossible d\'afficher les graphiques!');
        }
    });
}

function drawLineChart(context, entreprise, pays, raisonsociale, secteuractivite) {

    if (entreprise === '') entreprise = 'null';
    if (pays === '') pays = 'null';
    if (raisonsociale === '') raisonsociale = 'null';
    if (secteuractivite === '') secteuractivite = 'null';

    $.ajax({
        url: context + 'statistiques/line/' + entreprise + '/' + pays + '/' + secteuractivite + '/' + raisonsociale,
        method: 'GET',
        data: null,
        success: function (data) {
            var dataLine = [];
            var labelsLine = [];
            var colorsLine = [];
            var hKeys = '';
            var nomAxe = '';

            if (data.result !== null) {
                var index = 0;

                $('#line-example').html('');

                data.result.forEach(function (t) {
                    nomAxe = 2000 + (index + 1);  //t.libelleAxe;
                    var nomEntreprise = [];
                    var couleurs = [];
                    var valeurs = [];

                    t.entrepriseForLineCharts.forEach(function (t2) {
                        nomEntreprise.push(t2.nomEntreprise);
                        couleurs.push(t2.couleur);
                        valeurs.push(t2.notationAxe);
                    });

                    colorsLine = couleurs;
                    labelsLine = nomEntreprise;

                    if (t.entrepriseForLineCharts.length === 1){
                        dataLine.push({y: '' + nomAxe + '', a: valeurs[0]});
                        hKeys = ['a'];
                    }
                    else if (t.entrepriseForLineCharts.length === 2){
                        dataLine.push({y: '' + nomAxe + '', a: valeurs[0], b: valeurs[1]});
                        hKeys = ['a', 'b'];
                    }
                    else if (t.entrepriseForLineCharts.length === 3){
                        dataLine.push({y: '' + nomAxe + '', a: valeurs[0], b: valeurs[1], c: valeurs[2]});
                        hKeys = ['a', 'b', 'c'];
                    }
                    else if (t.entrepriseForLineCharts.length === 4){
                        dataLine.push({y: '' + nomAxe + '', a: valeurs[0], b: valeurs[1], c: valeurs[2], d: valeurs[3]});
                        hKeys = ['a', 'b', 'c', 'd'];
                    }

                    index++;
                });

                // Morris line chart

                Morris.Line({
                    element: 'line-example',
                    data: dataLine,
                    xkey: 'y',
                    ykeys: hKeys,
                    labels: labelsLine,
                    lineColors:colorsLine
                });
            }
            else {
                alert('Données vides !');
            }
        },
        error: function (msg) {
            alert('Impossible d\'afficher les graphiques!');
        }
    });
}
