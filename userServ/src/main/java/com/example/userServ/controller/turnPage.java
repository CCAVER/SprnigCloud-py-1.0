package com.example.userServ.controller;

import com.example.finalwork4.domain.pyInf;
import com.example.userServ.service.AcServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class turnPage {
    @Autowired
    AcServ as;
    @RequestMapping("/read")
    public ModelAndView read(HttpServletRequest request, HttpSession session){
        //System.out.println(request.getParameter("pid"));
        session.setAttribute("newid",request.getParameter("pid"));
        //pyInf pi=(pyInf) session.getAttribute("pi");
        session.setAttribute("read","readonly");
        String name=String.valueOf(request.getParameter("fname"));
        ModelAndView view = new ModelAndView("show");
        view.addObject("image_name", name);
        //view.addObject(pi);
        return view;
    }
    @RequestMapping("/")
    //@ResponseBody
    public String hello() throws IOException, InterruptedException {
        return "logPage";
    }
    @RequestMapping("/reg")
    //@ResponseBody
    public String goreg() throws IOException, InterruptedException {
        return "reg";
    }
    @RequestMapping("/py")
    public String goPy() throws IOException, InterruptedException
    {
        return "py";
    }
    @PostMapping("doDel")
    public String doDel(HttpServletRequest request){
        String id= request.getParameter("pid");
        as.doDel(id);
        return "py";
    }
}
