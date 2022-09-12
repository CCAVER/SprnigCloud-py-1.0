package com.example.userServ.service.impl;

import com.example.finalwork4.domain.pyInf;
import com.example.userServ.dao.AccountDao;
import com.example.userServ.domain.account;
import com.example.userServ.domain.pyDetail;
import com.example.userServ.service.AcServ;
import com.sun.glass.ui.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("AcServ")
public class AcServImpl implements AcServ {

    @Autowired
    AccountDao accountDao;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<account> show() {
        List<account> ac=accountDao.findAll();
        return ac;
    }

    @Override
    public String[] acCheck(String uname, String pas) {
        return new String[]{accountDao.check(uname, pas),accountDao.getId(uname, pas)};
    }
    @Override
    public byte[] genImg(int newId) throws Exception {
        try {
            byte[] data=accountDao.getImgD(String.valueOf(newId)).getData();
            return data;
        }catch (Exception e){e.printStackTrace();throw new Exception("genFail");}

    }
    @Override
    public List<pyDetail> getDetail(String uid) {
        return accountDao.getMyInf(uid);
    }

    @Override
    public void doDel(String id) {
        accountDao.doDel(id);
    }
}
