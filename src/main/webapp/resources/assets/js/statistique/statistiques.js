$(window).load(function(){

    $('#lineViewTri').on('click', function(e){
        var lineInput = $(this).find('input');

        if(!lineInput.prop('checked')){$('#lineViewDiagram').find('.tile-close').click();}
        else{$('#lineViewDiagram').show();}
    });

    $('#barViewTri').on('click', function(e){
        var barInput = $(this).find('input');

        if(!barInput.prop('checked')){
            $('#barViewDiagram').find('.tile-close').click();
            $('#pieViewDiagram').closest('.col-md-6').removeClass('col-md-6').addClass('col-md-12');
        }
        else{
            $('#barViewDiagram').show();
            $('#pieViewDiagram').closest('.col-md-12').removeClass('col-md-12').addClass('col-md-6');
        }
    });

    $('#pieViewTri').on('click', function(e){
        var pieInput = $(this).find('input');

        if(!pieInput.prop('checked')){
            $('#pieViewDiagram').find('.tile-close').click();
            $('#barViewDiagram').closest('.col-md-6').removeClass('col-md-6').addClass('col-md-12');
        }
        else{
            $('#pieViewDiagram').show();
            $('#barViewDiagram').closest('.col-md-12').removeClass('col-md-12').addClass('col-md-6');
        }
    });
});