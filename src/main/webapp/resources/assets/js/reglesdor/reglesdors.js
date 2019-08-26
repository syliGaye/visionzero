$(window).load(function(){

    $('#navNext').on('click', function(e){
        var thisLi = $(this).closest('li').siblings('.active ');
        var nextLi = thisLi.next();
        var accVisible = $('#accordion').find('.panel:visible');

        if(Number(thisLi.text()) > 0) $('#navPrev').show();

        if(Number(nextLi.text()) == 3) $(this).hide();

        if(Number(nextLi.text()) < 4){
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

        if(Number(thisLi.text()) < 4) $('#navNext').show();

        if(Number(prevLi.text()) == 1) $(this).hide();

        if(Number(prevLi.text()) > 0){
            thisLi.removeClass('active');
            prevLi.addClass('active');
            accVisible.hide('slow');
            accVisible.prev().show('slow');
        }
    });

    $('#navPuce_1').on('click', function(e){
        e.preventDefault;
        var thisLi = $(this).closest('li');
        var activeLi = thisLi.siblings('.active');

        if(Number(thisLi.text()) == 1){
            $('#navNext').show();
            $('#navPrev').hide();
        }
        else if(Number(thisLi.text()) == 3){
            $('#navNext').hide();
            $('#navPrev').show();
        }
        else {
            $('#navNext').show();
            $('#navPrev').show();
        }

        thisLi.siblings('.active').removeClass('active');
        thisLi.addClass('active');
        $('#acc1').siblings().hide('slow');
        $('#acc1').show('slow');
    });

    $('#navPuce_2').on('click', function(e){
        e.preventDefault;
        var thisLi = $(this).closest('li');
        var activeLi = thisLi.siblings('.active');

        if(Number(thisLi.text()) == 1){
            $('#navNext').show();
            $('#navPrev').hide();
        }
        else if(Number(thisLi.text()) == 3){
            $('#navNext').hide();
            $('#navPrev').show();
        }
        else {
            $('#navNext').show();
            $('#navPrev').show();
        }

        thisLi.siblings('.active').removeClass('active');
        thisLi.addClass('active');
        $('#acc2').siblings().hide('slow');
        $('#acc2').show('slow');
    });

    $('#navPuce_3').on('click', function(e){
        e.preventDefault;
        var thisLi = $(this).closest('li');
        var activeLi = thisLi.siblings('.active');

        if(Number(thisLi.text()) == 1){
            $('#navNext').show();
            $('#navPrev').hide();
        }
        else if(Number(thisLi.text()) == 3){
            $('#navNext').hide();
            $('#navPrev').show();
        }
        else {
            $('#navNext').show();
            $('#navPrev').show();
        }

        thisLi.siblings('.active').removeClass('active');
        thisLi.addClass('active');
        $('#acc3').siblings().hide('slow');
        $('#acc3').show('slow');
    });

});