package com.example.userServ.dao;

import com.example.userServ.domain.account;
import com.example.userServ.domain.image;
import com.example.userServ.domain.pyDetail;
import com.example.userServ.domain.user;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AccountDao {
    @Select("select * from sale")
    List<account> findAll();

    @Select("select forSale(${num})")
    int sale(@Param("num") int num);

    @Select("select checkU(\"${username}\",\"${password}\");") //字符串必须用 $ 不能用 #
    String check(@Param("username") String username,@Param("password") String password);

    @Select("SELECT uid from user where user.`password`=\"${password}\" and user.username=\"${username}\"")
    String getId(@Param("username") String username,@Param("password") String password);

    @Select("select newrow();")
    int newrow();
/*
    @Select("select data from images where id=${id}")
    image getImg(@Param("id") int newid);

    @Select("select data from images where id=${id};")
    Blob getImgB(@Param("id") String id);

    @Select("select data from images where id=${id};")
    byte[] getImgC(@Param("id") String id);
*/
    @Select("select data from images where id=${id};")
    image getImgD(@Param("id") String id);

    @Select("select DISTINCT images.id,images.name as Pyname,imginf.matha,imginf.mini,imginf.maxi,imginf.qal,imginf.wei,imginf.lent from images,imginf where images.uid=${uid} and images.id=imginf.ImgId GROUP BY images.id")
    List<pyDetail> getMyInf(@Param("uid") String uid);

    @Select("select password from user where username=\"${username}\";")
    String getPas(@Param("username") String username);

    @Insert("insert into user(username,password) values(\"${username}\",\"${password}\")")
    int add(@Param("username") String username,@Param("password") String password);

    @Select("select * from user where username=\"${username}\";")
    user getU(@Param("username") String username);

    @Delete("delete from images where id=${id}")
    void doDel(@Param("id") String id);

    @Select("SELECT reg(\"${username}\",\"${psw}\",${iden});")
    int reg(@Param("username") String username,@Param("psw") String psw,@Param("iden") int iden);
}
