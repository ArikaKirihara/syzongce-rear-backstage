$('.btn1').click(function(){
    var old_password = $('#old_password').val();
    var new_password = $('#new_password').val();
    var retNumLen = /\b\S{6,20}\b/;
    var retNum = /^[^!@#$%^&*()+=~`{}[\]\\|:;'"<>?/]+$/;
    if(retNumLen.test(old_password) && retNum.test(old_password)){
        if(old_password == new_password){
            return true;
        }else{
            alert("密码不一致，请重新输入");
        }
    }else{
        alert("密码需为6~20位字符\n且不包含非法字符（!@#$%^&*()+=~`{}[]\|:;'"+'"'+"<>?/)");
    }
});

$('.login_btn').click(function(){
    var password = $('#password').val();
    var retNumLen = /\b\S{6,20}\b/;
    var retNum = /^[^!@#$%^&*()+=~`{}[\]\\|:;'"<>?/]+$/;
    if(retNumLen.test(password) && retNum.test(password)){
        return true;
    }else{
        alert("密码需为6~20位字符\n且不包含非法字符（!@#$%^&*()+=~`{}[]\|:;'"+'"'+"<>?/)");
    }
});