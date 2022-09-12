package com.example.pyConn.service.impl;

import com.example.finalwork4.domain.pyInf;
import com.example.pyConn.dao.AccountDao;
import com.example.pyConn.service.pyServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service("pyServ")
public class pyServImpl implements pyServ {
    @Autowired
    AccountDao accountDao;

    @Override
    public int generate(pyInf inf) throws Exception {
        //先根据ID创建数据
        int newId=0;
        final String PATH = "D:\\学习系列\\J2E\\scripts";//脚本位置
        String command="";

        if (inf.getMatha()!=""||inf.getMatha()!=null){
            newId=accountDao.newrow();//新的一行已经建立，且获取到了ID
            command= "cmd.exe /c cd "
                    + PATH //此处插入python文件的路径
                    + " && python pyCharms1.py"
                    //+"-f D:\\学习系列\\J2E\\scripts\\";//利用python的命令行机制可以传入参数
                    //+" --matha \"cos(x+8)*(9-2)\" "
                    +" --matha "+inf.getMatha()
                    +" --rowid "+newId
                    +" --fname "+inf.getFname()
                    +" --uid "+inf.getUid()
                    +" --mini "+inf.getMini()
                    +" --maxi "+inf.getMaxi()
                    +" --lent "+inf.getLent()
                    +" --hei "+inf.getHei()
                    +" --qal "+inf.getQuality();
            System.out.println(command);
            //python pyCharms1.py --matha 'cos(x)' --rowid 23 --fname 1 --uid 2 --mini '-10' --maxi '10' --lent 10 --hei 10 --qal 0.001

        }else {
            throw new NullPointerException("函数为空");
        }//未输入抛出异常
        try {
            Process p = Runtime.getRuntime().exec(command);
            //*****************非常关键*******************//
            p.waitFor();//如果去掉，下一步的查询将在python语句未执行完时执行,必然查询出空值
            //******************************************//
            return newId;
            //return 1;
        } catch (IOException e) {
            //e.printStackTrace();
            throw new Exception();
        }
    }
    @Override
    public int fix(pyInf inf,int rowid) throws Exception {
        final String PATH = "D:\\学习系列\\J2E\\scripts";//脚本位置
        String command="";

        if (inf.getMatha()!=""||inf.getMatha()!=null){
            command= "cmd.exe /c cd "
                    + PATH //此处插入python文件的路径
                    + " && python pyCharms1.py"
                    //+"-f D:\\学习系列\\J2E\\scripts\\";//利用python的命令行机制可以传入参数
                    //+" --matha \"cos(x+8)*(9-2)\" "
                    +" --matha "+inf.getMatha()
                    +" --rowid "+rowid
                    +" --fname "+inf.getFname()
                    +" --uid "+inf.getUid()
                    +" --mini "+inf.getMini()
                    +" --maxi "+inf.getMaxi()
                    +" --lent "+inf.getLent()
                    +" --hei "+inf.getHei()
                    +" --qal "+inf.getQuality();
            //System.out.println(command);
            //python pyCharms1.py --matha 'cos(2*x)' --rowid 17 --fname 1 --uid 2 --mini -10.0 --maxi 10.0 --lent 10.0 --hei 10.0 --qal 0.001
        }else {
            throw new NullPointerException("函数为空");
        }//未输入抛出异常
        try {
            Process p = Runtime.getRuntime().exec(command);
            //*****************非常关键*******************
            p.waitFor();//如果去掉，下一步的查询将在python语句未执行完时执行,必然查询出空值
            //******************************************
            return rowid;
            //return 1;
        } catch (IOException e) {
            //e.printStackTrace();
            throw new Exception();
        }
    }


}
