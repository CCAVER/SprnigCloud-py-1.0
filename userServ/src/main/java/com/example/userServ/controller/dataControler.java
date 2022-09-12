package com.example.userServ.controller;

import com.example.finalwork4.domain.pyInf;
import com.example.userServ.domain.pyDetail;
import com.example.userServ.service.AcServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class dataControler {
    @Autowired
    AcServ as;

    @PostMapping(value = "/nowInf")
    public Map<String, String> nowInf(HttpSession session) {
        pyInf pi = (pyInf) session.getAttribute("pi");
        Map<String, String> map = new HashMap<String, String>();
        if(!((String)session.getAttribute("read")).equals("readonly")){
        map.put("quality", String.valueOf(pi.getQuality()));
        map.put("fname", pi.getFname());
        map.put("uid", pi.getUid());
        map.put("mathm", pi.getMatha());
        map.put("lent", String.valueOf(pi.getLent()));
        map.put("hei", String.valueOf(pi.getHei()));
        map.put("maxi", String.valueOf(pi.getMaxi()));
        map.put("mini", String.valueOf(pi.getMini()));
        }
        if(((String)session.getAttribute("read")).equals("readonly")){
            Map<String,String[]> map2= (Map<String, String[]>) session.getAttribute("mywork");
            String[] pd=map2.get(session.getAttribute("newid"));
            map.put("quality",pd[4]);
            map.put("fname",pd[0]);
            map.put("uid", (String) session.getAttribute("uid"));
            map.put("mathm",pd[1]);
            map.put("lent",pd[6]);
            map.put("hei",pd[5]);
            map.put("maxi",pd[3]);
            map.put("mini",pd[2]);
        }
        return map;
    }

    @PostMapping("mywork")
    public Map<String, String[]> mywork(HttpSession session) {
        String uid = (String) session.getAttribute("uid");
        List<pyDetail> pd = as.getDetail(uid);
        Map<String, String[]> map = new HashMap<String, String[]>();
        for (pyDetail infs : pd) {
            map.put(String.valueOf(infs.getId()),
                    new String[]{
                            infs.getPyname(),
                            infs.getMatha(),
                            String.valueOf(infs.getMini()),
                            String.valueOf(infs.getMaxi()),
                            String.valueOf(infs.getQal()),
                            String.valueOf(infs.getWei()),
                            String.valueOf(infs.getLent())
                    });
        }
        session.setAttribute("mywork", map);
        return map;

    }
}
