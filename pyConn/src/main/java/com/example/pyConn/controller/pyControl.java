package com.example.pyConn.controller;
import com.example.finalwork4.domain.pyInf;
import com.example.pyConn.service.pyServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

//@Controller
@RequestMapping("pyReq")
@RestController
public class pyControl {

    @Autowired
    pyServ pyServ;
    @RequestMapping (value = "genId",method = RequestMethod.POST)
    public String getNewId(@RequestBody pyInf pi) throws Exception {
        String s = String.valueOf(pyServ.generate(pi));
        return s;
        //return "1";
    }
    @RequestMapping(value = "fix",method = RequestMethod.POST)
    public String fix(@RequestBody pyInf pi,int rowid) throws Exception {
        String s = String.valueOf(pyServ.fix(pi,rowid));
        return s;
    }
}
