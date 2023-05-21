// let previewContainer = document.querySelector('.result-preview');
// let previewBox = previewContainer.querySelectorAll('.preview');
//
// document.querySelectorAll('.table_1 .t_btn .r_btn').forEach(r_btn =>{
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


$(".r_operate").click(function(){
    $(".preview").each(function(){
        $("#pre_clear").removeClass("active");
        $("#pre_del").removeClass("active");
    });
    $(".result-preview").css("display","none");
    vio_Id = 0;
});


$(".result-preview").click(function(e){
    var shade = $(".result-preview");
    if(shade.is(e.target)){
        $(".preview").each(function(i){
            $("#pre_clear").removeClass("active");
            $("#pre_del").removeClass("active");
        });
        $(".result-preview").css("display","none");
    }
    vio_Id = 0;
});