$(".r_operate").click(function(){
    $(".preview").each(function(i){
        $("#preview_pass").removeClass("active");
        $("#preview_unpass").removeClass("active");
    });
    $(".result-preview").css("display","none");
});

$(".result-preview").click(function(e){
    var shade = $(".result-preview");
    if(shade.is(e.target)){
        $(".preview").each(function(i){
            $("#preview_pass").removeClass("active");
            $("#preview_unpass").removeClass("active");
        });
        $(".result-preview").css("display","none");
    }
});