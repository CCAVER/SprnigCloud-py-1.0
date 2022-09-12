package com.example.userServ.controller;

import com.example.finalwork4.domain.pyInf;
import com.example.userServ.service.AcServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ImgWork {
    @Autowired
    @LoadBalanced
    RestTemplate restTemplate;

    @Autowired
    AcServ acServ;

    @RequestMapping(value = "/image/{image_name}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("image_name") String image_name, final HttpServletResponse response, HttpSession session) throws Exception{
        pyInf pi= (pyInf) session.getAttribute("pi");

        int newid= Integer.parseInt(String.valueOf(session.getAttribute("newid")));

        response.setContentType("image/png");
        response.setCharacterEncoding("UTF-8");

        byte[] data=acServ.genImg(newid);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
