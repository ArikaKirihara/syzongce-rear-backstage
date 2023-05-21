$('.login_btn').click(function(){
    var username = $('#username').val();
    var retNum = /^[^!@#$%^&*()+=~`{}[\]\\|:;'"<>?/]+$/;
    if(retNum.test(username)){
        alert("格式正确");
    }else{
        alert("学号或用户名格式错误");
    }

});
