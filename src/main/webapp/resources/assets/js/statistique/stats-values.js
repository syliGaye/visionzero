$(window).load(function () {

    var context = $('.brand').attr("href");

    $('#boutonTri').on('click', function (e) {
        e.preventDefault();

        var entreprise = $('#triEntreprise').val();
        var pays = $('#triPays').val();
        var raisonsociale = $('#triRaisonSociale').val();
        var secteuractivite = $('#triSecteurActivite').val();

        if (entreprise === null) entreprise = 'null';
        if (pays === null) pays = 'null';
        if (raisonsociale === null) raisonsociale = 'null';
        if (secteuractivite === null) secteuractivite = 'null';

        drawChartsWithRequest(context + 'statistiques/' + entreprise + '/' + pays + '/' + secteuractivite + '/' + raisonsociale);

    });

    drawChartsWithRequest(context + 'statistiques/null/null/null/null');

});

function drawChartsWithRequest(url) {
    var dataLine = [];
    var optionsLine = {};
    var plotLine = null;
    var dataOrdered = [];
    var optionsOrdered = {};
    var plotOrdered = null;
    var dataPie = [];
    var optionsPie = {};
    var plotPie = null;

    $.ajax({
        url: url,
        method: 'GET',
        data: null,
        success: function (data) {
            if (data.msg === 'objet'){
                console.log(data.object);
                alert('un Objet');
            }
            else if (data.msg = 'liste'){
                console.log(data.result);
                alert('une liste');
            }
            else {
                alert('Données vides !');
            }
            var donnees = [];

            data.result.forEach(function (t) {
                //donnees.push(t.);
            });

            drawLineChart(dataLine, optionsLine, plotLine);
            drawOrderedChart(dataOrdered, optionsOrdered, plotOrdered);
            drawPieChart(dataPie, optionsPie, plotPie);
        },
        error: function (msg) {
            alert('Impossible d\'afficher les graphiques!');
        }
    });
}

function drawLineChart(data, options, plot) {
    // Initialize Line Chart

    data = [
        {
            data: [[1,5.3],[2,5.9],[3,7.2],[4,8],[5,7],[6,6.5],[7,6.2]],
            label: 'Sales',
            points: {
                show: true,
                radius: 6
            },
            splines: {
                show: true,
                tension: 0.45,
                lineWidth: 5,
                fill: 0
            }
        }/*,
        {
            data: [[1,6.6],[2,7.4],[3,8.6],[4,9.4],[5,8.3],[6,7.9],[7,7.2],[8,7.7],[9,8.9],[10,8.4],[11,8],[12,8.3]],
            label: 'Orders',
            points: {
                show: true,
                radius: 6
            },
            splines: {
                show: true,
                tension: 0.45,
                lineWidth: 5,
                fill: 0
            }
        }*/
    ];

    options = {
        colors: ['#a2d200', '#cd97eb'],
        series: {
            shadowSize: 0
        },
        xaxis:{
            font: {
                color: '#ccc'
            },
            position: 'bottom',
            ticks: [
                [ 1, 'Axe 1' ], [ 2, 'Axe 2' ], [ 3, 'Axe 3' ], [ 4, 'Axe 4' ], [ 5, 'Axe 5' ], [ 6, 'Axe 6' ], [ 7, 'Axe 7' ]
            ]
        },
        yaxis: {
            font: {
                color: '#ccc'
            }
        },
        grid: {
            hoverable: true,
            clickable: true,
            borderWidth: 0,
            color: '#ccc'
        },
        tooltip: true,
        tooltipOpts: {
            content: '%s: %y.4',
            defaultTheme: false,
            shifts: {
                x: 0,
                y: 20
            }
        }
    };

    plot = $.plot($("#line-chart"), data, options);

    $(window).resize(function() {
        // redraw the graph in the correctly sized div
        plot.resize();
        plot.setupGrid();
        plot.draw();
    });
    // * Initialize Line Chart
}

function drawPieChart(data, options, plot) {
    // Initialize Pie Chart
    data = [
        { label: 'Axe 1', data: 30 },
        { label: 'Axe 2', data: 15 },
        { label: 'Axe 3', data: 15 },
        { label: 'Axe 4', data: 10 },
        { label: 'Axe 5', data: 5 },
        { label: 'Axe 6', data: 10},
        { label: 'Axe 7', data: 20}
    ];

    options = {
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
        colors: ['#428bca','#5cb85c','#f0ad4e','#d9534f','#5bc0de','#616f77','#A2D200'],
        grid: {
            hoverable: true,
            clickable: true,
            borderWidth: 0,
            color: '#ccc'
        },
        tooltip: true,
        tooltipOpts: { content: '%s: %p.0%' }
    };

    plot = $.plot($("#pie-chart"), data, options);

    $(window).resize(function() {
        // redraw the graph in the correctly sized div
        plot.resize();
        plot.setupGrid();
        plot.draw();
    });
    // * Initialize Pie Chart
}

function drawOrderedChart(data, options, plot) {

    // Initialize Ordered Chart
    data = [
        {
            data: [[10, 50]],
            label: 'Axe 1'
        }, {
            data: [[10, 30]],
            label: 'Axe 2'
        }, {
            data: [[10, 40]],
            label: 'Axe 3'
        }, {
            data: [[10, 20]],
            label: 'Axe 4'
        }, {
            data: [[10, 35]],
            label: 'Axe 5'
        }, {
            data: [[10, 15]],
            label: 'Axe 6'
        }, {
            data: [[10, 70]],
            label: 'Axe 7'
        }
    ];

    options = {
        series: {
            shadowSize: 0
        },
        bars: {
            show: true,
            fill: true,
            lineWidth: 0,
            fillColor: {
                colors: [{ opacity:0.6 }, { opacity:0.8}]
            },
            order: 1, // order bars
            colors: ['#428bca','#d9534f','#A40778','#22BEEF','#FFC100','#9C27B0','#1693A5']
        },
        xaxis: {
            font: {
                color: '#ccc'
            }
        },
        yaxis: {
            font: {
                color: '#ccc'
            }
        },
        grid: {
            hoverable: true,
            clickable: true,
            borderWidth: 0,
            color: '#ccc'
        },
        tooltip: true
    };

    plot = $.plot($("#ordered-chart"), data, options);

    $(window).resize(function() {
        // redraw the graph in the correctly sized div
        plot.resize();
        plot.setupGrid();
        plot.draw();
    });
    // * Initialize Ordered Chart
}