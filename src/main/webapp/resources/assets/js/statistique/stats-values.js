$(window).load(function () {

    // Initialize Line Chart

    var data1 = [
        {
            data: [[1,5.3],[2,5.9],[3,7.2],[4,8],[5,7],[6,6.5],[7,6.2],[8,6.7],[9,7.2],[10,7],[11,6.8],[12,7]],
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
        },
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
        }
    ];

    var options1 = {
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
                [ 1, 'Jan' ], [ 2, 'Feb' ], [ 3, 'Mar' ], [ 4, 'Apr' ], [ 5, 'May' ], [ 6, 'Jun' ], [ 7, 'Jul' ], [ 8, 'Aug' ], [ 9, 'Sep' ], [ 10, 'Oct' ], [ 11, 'Nov' ], [ 12, 'Dec' ]
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

    var plot1 = $.plot($("#line-chart"), data1, options1);

    $(window).resize(function() {
        // redraw the graph in the correctly sized div
        plot1.resize();
        plot1.setupGrid();
        plot1.draw();
    });
    // * Initialize Line Chart

    // Initialize Ordered Chart
    var data2 = [
        {
            data: [[10, 50], [20, 80], [30, 60], [40, 40]],
            label: 'A'
        }, {
            data: [[10, 30], [20, 50], [30, 70], [40, 50]],
            label: 'B'
        }, {
            data: [[10, 40], [20, 60], [30, 90], [40, 60]],
            label: 'C'
        }
    ];

    var options2 = {
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
            colors: ['#428bca','#d9534f','#A40778']
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

    var plot2 = $.plot($("#ordered-chart"), data2, options2);

    $(window).resize(function() {
        // redraw the graph in the correctly sized div
        plot2.resize();
        plot2.setupGrid();
        plot2.draw();
    });
    // * Initialize Ordered Chart

    // Initialize Pie Chart
    var data3 = [
        { label: 'Chrome', data: 30 },
        { label: 'Firefox', data: 15 },
        { label: 'Safari', data: 15 },
        { label: 'IE', data: 10 },
        { label: 'Opera', data: 5 },
        { label: 'Other', data: 10}
    ];

    var options3 = {
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
        colors: ['#428bca','#5cb85c','#f0ad4e','#d9534f','#5bc0de','#616f77'],
        grid: {
            hoverable: true,
            clickable: true,
            borderWidth: 0,
            color: '#ccc'
        },
        tooltip: true,
        tooltipOpts: { content: '%s: %p.0%' }
    };

    var plot3 = $.plot($("#pie-chart"), data3, options3);

    $(window).resize(function() {
        // redraw the graph in the correctly sized div
        plot3.resize();
        plot3.setupGrid();
        plot3.draw();
    });
    // * Initialize Pie Chart

});