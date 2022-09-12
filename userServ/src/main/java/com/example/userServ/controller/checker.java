package com.example.userServ.controller;

import com.example.userServ.service.MySecure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class checker {
    @Autowired
    MySecure mySecure;

    @RequestMapping("/dochecks")
    public String goPy2(String username, String password, HttpServletRequest request, HttpSession session) throws IOException, InterruptedException
    {
        try {
            String[] tmp=mySecure.login(username,password);
            if(tmp[0].equals("true")){
                session.setAttribute("uid",tmp[1]);//获取UID
                return "py";
            }else{
                request.setAttribute("wrong","账户名或密码有误");
                return "logPage";}
        }catch (Exception e){
            request.setAttribute("wrong","用户不存在");
            return "logPage";}
    }
    @RequestMapping("doreg")
    public String reg(String username,String password,HttpServletRequest request,HttpSession session){
        if(username!=""&&username!=null&&password!=""&&password!=null){
            try {
                mySecure.add(username,password);
                return "logPage";
            }catch (Exception e){
                request.setAttribute("wrong","该用户已存在");
                return "reg";}

        }
        return "reg";
    }
}
