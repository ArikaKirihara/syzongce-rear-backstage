$('.btn1').click(function(){
    var stu_id = $('#stu_id').val();
    var retNum = /\b\d{12}\b/;
    if(retNum.test(stu_id)){
        alert("格式正确");
    }else{
        alert("学号格式错误");
    }

});
$('.f_btn').click(function(){
    var stu_id = $('#stu_id').val();
    var retNum = /\b\d{12}\b/;
    if(retNum.test(stu_id)){
        alert("格式正确");
    }else{
        alert("学号格式错误");
    }

});
