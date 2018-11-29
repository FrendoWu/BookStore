package com.bookstore.controller;

import com.bookstore.domain.Trolley;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by lenovo on 2018/11/21.
 */
@Controller
@RequestMapping("trolly")
public class TrollyController {
    @Autowired
    @Qualifier("BookstoreService")
    private com.bookstore.service.BookstoreService BookstoreService;

    @RequestMapping(value = "/selectTrolley",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public String selectTrolleyByUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
//        int userId = Integer.parseInt(request.getAttribute("userId"));
        int userId = 0;//测试用
        Trolley userTrolley = BookstoreService.selectTrolleyByUserId(userId);
        JSONObject userJson = JSONObject.fromObject(userTrolley);
        return userJson.toString();
    }


    @RequestMapping(value = "/updateTrolley",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public String updateTrollyByUserId(HttpServletRequest request){
        HttpSession session = request.getSession();
        int userId = 0;//测试
//        int number = Integer.parseInt(request.getParameter("number"));
        int number = 0;
        BookstoreService.updateTrollyByUserId(userId,number);
        return "success";
    }

    @RequestMapping(value = "/insertTrolley",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public void insertTrollyByUserId(HttpServletRequest request){
        HttpSession session = request.getSession();
        int userId = 0;//测试
//        int bookId = Integer.parseInt(request.getParameter("bookId"));
//        int number = Integer.parseInt(request.getParameter("number"));
        int bookId = 0;
        int number = 22;
        BookstoreService.insertTrollyByUserId(userId,bookId,number);
    }
}
