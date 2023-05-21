package com.sxy.zongce.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionSolver {

    @ExceptionHandler(value = {AddException.class})
    @ResponseBody
    public <T> ResponseRes<T> addExceptionHandler(Exception e){
        //这里先判断拦截到的Exception是不是我们自定义的异常类型
        if(e instanceof AddException){
            AddException appException = (AddException)e;
            return ResponseRes.error(appException.getCode(),appException.getMsg());
        }

        //如果拦截的异常不是我们自定义的异常(例如：数据库主键冲突)
        return ResponseRes.error(500,"服务器异常");

    }

    @ExceptionHandler(value = {ApplicationException.class})
    @ResponseBody
    public <T> ResponseRes<T> applicationExceptionHandler(Exception e){
        //这里先判断拦截到的Exception是不是我们自定义的异常类型
        if(e instanceof ApplicationException){
            ApplicationException applicationException = (ApplicationException)e;
            return ResponseRes.error(applicationException.getCode(),applicationException.getMsg());
        }

        //如果拦截的异常不是我们自定义的异常(例如：数据库主键冲突)
        return ResponseRes.error(500,"服务器异常");

    }

    @ExceptionHandler(value = {FileException.class})
    @ResponseBody
    public <T> ResponseRes<T> fileExceptionHandler(Exception e){
        //这里先判断拦截到的Exception是不是我们自定义的异常类型
        if(e instanceof FileException){
            FileException fileException = (FileException)e;
        return ResponseRes.error(fileException.getCode(),fileException.getMsg());
        }

        //如果拦截的异常不是我们自定义的异常(例如：数据库主键冲突)
        return ResponseRes.error(500,"服务器异常");


    }

    @ExceptionHandler(value = {UserException.class})
    @ResponseBody
    public <T> ResponseRes<T> userExceptionHandler(Exception e){
        //这里先判断拦截到的Exception是不是我们自定义的异常类型
        if(e instanceof UserException){
            UserException userException = (UserException)e;
            return ResponseRes.error(userException.getCode(),userException.getMsg());
        }

        //如果拦截的异常不是我们自定义的异常(例如：数据库主键冲突)
        return ResponseRes.error(500,"服务器异常");

    }

    @ExceptionHandler(value = {VisibilityException.class})
    @ResponseBody
    public <T> ResponseRes<T> visibilityExceptionHandler(Exception e) {
        //这里先判断拦截到的Exception是不是我们自定义的异常类型
        if (e instanceof VisibilityException) {
            VisibilityException visibilityException = (VisibilityException) e;
            return ResponseRes.error(visibilityException.getCode(), visibilityException.getMsg());
        }

        //如果拦截的异常不是我们自定义的异常(例如：数据库主键冲突)
        return ResponseRes.error(500, "服务器异常");
    }

    @ExceptionHandler(value = {ExistingException.class})
    @ResponseBody
    public <T> ResponseRes<T> existingExceptionHandler(Exception e){
        //这里先判断拦截到的Exception是不是我们自定义的异常类型
        if(e instanceof ExistingException){
            ExistingException existingException = (ExistingException)e;
            return ResponseRes.error(existingException.getCode(),existingException.getMsg());
        }

        //如果拦截的异常不是我们自定义的异常(例如：数据库主键冲突)
        return ResponseRes.error(500,"服务器异常");

    }

}
