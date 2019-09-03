$(window).load(function(){

    var accordionCount = Number($('#accodionCount').text());

    $('#navNext').on('click', function(e){
        var thisLi = $(this).closest('li').siblings('.active ');
        var nextLi = thisLi.next();
        var accVisible = $('#accordion').find('.panel:visible');

        if(Number(thisLi.text()) > 0) $('#navPrev').show();

        if(Number(nextLi.text()) == accordionCount) $(this).hide();

        if(Number(nextLi.text()) < (accordionCount + 1)){
            thisLi.removeClass('active');
            nextLi.addClass('active');
            accVisible.hide('slow');
            accVisible.next().show('slow');
        }
    });

    $('#navPrev').on('click', function(e){
        e.preventDefault();
        var thisLi = $(this).closest('li').siblings('.active ');
        var prevLi = thisLi.prev();
        var accVisible = $('#accordion').find('.panel:visible');

        if(Number(thisLi.text()) < (accordionCount + 1)) $('#navNext').show();

        if(Number(prevLi.text()) == 1) $(this).hide();

        if(Number(prevLi.text()) > 0){
            thisLi.removeClass('active');
            prevLi.addClass('active');
            accVisible.hide('slow');
            accVisible.prev().show('slow');
        }
    });

    $('#navPuce_1').addClass('active');

    for (var i = 1; i < (accordionCount + 1); i++){
        //changerCouleur_1('indPourDom' + i, 0);

        if (i != 1){
            $('#acc'+i).hide();
        }
    }

    $(document).on('click', '.b-default', function (e) {
        e.preventDefault();
        var index = $(this).attr('id').slice(-1);
        var thisLi = $(this).closest('li');
        var activeLi = thisLi.siblings('.active');

        if(Number(thisLi.text()) == 1){
            $('#navNext').show();
            $('#navPrev').hide();
        }
        else if(Number(thisLi.text()) == accordionCount){
            $('#navNext').hide();
            $('#navPrev').show();
        }
        else {
            $('#navNext').show();
            $('#navPrev').show();
        }

        thisLi.siblings('.active').removeClass('active');
        thisLi.addClass('active');
        $('#acc' + index).siblings().hide('slow');
        $('#acc' + index).show('slow');
    });

});


function changerCouleur_1(id, valeur) {
    var poucentage = (valeur * 100)/3;
    var val1 = 100/3;
    var val2 = (100/3)*2;

    if(poucentage <= val1){$('#'+id).attr('class', 'myIcon icon-red');}
    else if((poucentage > val1) && (poucentage <= val2)){$('#'+id).attr('class', 'myIcon icon-orange');}
    else if((poucentage > val2) && (poucentage <= 100)){$('#'+id).attr('class', 'myIcon icon-greensea');}
}