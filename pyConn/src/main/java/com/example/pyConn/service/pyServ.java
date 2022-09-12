package com.example.pyConn.service;

import com.example.finalwork4.domain.pyInf;

import java.sql.SQLException;
import java.util.HashMap;

public interface pyServ {
    int generate(pyInf inf) throws Exception;
    int fix(pyInf inf,int rowid) throws Exception;
}
