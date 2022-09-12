package com.example.userServ.service;

import com.example.finalwork4.domain.pyInf;
import com.example.userServ.domain.account;
import com.example.userServ.domain.pyDetail;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface AcServ {
    List<account> show();
    String[] acCheck(String uname,String pas);
    byte[] genImg(int newId) throws Exception;
    List<pyDetail> getDetail(String uid);
    void doDel(String id);
}
