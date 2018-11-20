package com.bookstore.controller;

import com.bookstore.domain.Test;
import com.bookstore.service.BookstoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("test")
public class TestController {
    //自动注入service
    @Autowired
    @Qualifier("BookstoreService")
    private BookstoreService BookstoreService;

    @RequestMapping(value = "/allUser",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public String selectAllUserAccount() throws JsonProcessingException {
        List<Test> userAccounts = BookstoreService.selectAllUserAccount();
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(userAccounts);
        String result = "{\"userAccounts\":" + userJson + "}";
        return result;
    }

    @RequestMapping(value = "/loginUser",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public String selectUserAccount(HttpServletRequest request) throws JsonProcessingException {
//        String account = request.getParameter("account");
        String account = "account1";
        Test userAccount = BookstoreService.selectUserAccount(account);
        JSONObject userJson = JSONObject.fromObject(userAccount);
        return userJson.toString();
    }
}
