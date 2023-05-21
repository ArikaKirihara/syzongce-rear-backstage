/**侧边栏 */
$(document).ready(function(){
    $(".sidebar_menu li").click(function(){
        if($(this).hasClass('active')){
            $(".sidebar_menu li").removeClass("active");
            
        }else{
            $(".sidebar_menu li").removeClass("active");
            $(this).addClass("active");
        }
        
    })
    $(".accordion a").click(function(){
        $(".accordion a").removeClass("active");
        $(this).addClass("active");
    })
    $(".shousuo").click(function(){
        $(".wrapper").addClass("active");
    })
    $(".bg_shadow,.close").click(function(){
        $(".wrapper").removeClass("active");
    })
})