// let previewContainer = document.querySelector('.result-preview');
// let previewBox = previewContainer.querySelectorAll('.preview');
//
// document.querySelectorAll('.tc_btn').forEach(r_btn =>{
//     r_btn.onclick = () =>{
//         previewContainer.style.display = 'flex';
//         let name = r_btn.getAttribute('data-name');
//         previewBox.forEach(preview =>{
//             let target = preview.getAttribute('data-target');
//             if(name == target){
//                 preview.classList.add('active');
//             }
//         });
//     };
// });
//
// document.querySelectorAll('.r_operate').forEach(r_btn =>{
//     r_btn.onclick = () =>{
//         previewBox.forEach(preview =>{
//             preview.classList.remove('active');
//         });
//         previewContainer.style.display = 'none';
//     };
// });
//
// previewContainer.onclick = function(e){
//     console.log(e);
//     if(e.target==previewContainer){
//         previewBox.forEach(preview =>{
//             preview.classList.remove('active');
//         });
//         previewContainer.style.display = 'none';
//     }
// }

function btndel(){
    $(".result-preview").css("display","flex");
    $("#pre_del").addClass("active");
    $(".r_operate").click(function(){
        $(".preview").each(function(){
            $("#pre_del").removeClass("active");
        });
        $(".result-preview").css("display","none");
    });
}
function btnreason(){
    $(".result-preview").css("display","flex");
    $("#pre_reason").addClass("active");
    $(".r_operate").click(function(){
        $(".preview").each(function(){
            $("#pre_reason").removeClass("active");
        });
        $(".result-preview").css("display","none");
    });
}

$(".result-preview").click(function(e){
    var shade = $(".result-preview");
    if(shade.is(e.target)){
        $(".preview").each(function(i){
            $("#pre_del").removeClass("active");
            $("#pre_reason").removeClass("active");
        });
        $(".result-preview").css("display","none");
    }
});