package com.example.userServ.service;

import com.example.finalwork4.domain.pyInf;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "pyConn")
public interface FeAc {

    @RequestMapping(value = "pyReq/genId", method = RequestMethod.POST)
    String getNewId(@RequestBody pyInf pi) throws Exception;

    @RequestMapping(value = "pyReq/fix", method = RequestMethod.POST)
    String getfix(@RequestBody pyInf pi, @RequestParam(value = "rowid") int rowid) throws Exception;
}
