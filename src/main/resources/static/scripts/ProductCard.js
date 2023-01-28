$(function() {
    $('.buy').click(function(){
        $('.bottom').addClass("clicked");
    });

    $('.remove').click(function(){
        $('.bottom').removeClass("clicked");
    });
    //TODO: multiple product class several times, so it fills parent container
});
