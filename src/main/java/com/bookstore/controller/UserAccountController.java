package com.bookstore.controller;

import com.bookstore.domain.UserAccount;
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("account")
public class UserAccountController {
    //自动注入service
    @Autowired
    @Qualifier("BookstoreService")
    private BookstoreService BookstoreService;

    @RequestMapping(value = "/allUser",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public String selectAllUserAccount() throws JsonProcessingException {
        List<UserAccount> userAccounts = BookstoreService.selectAllUserAccount();
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(userAccounts);
        String result = "{\"userAccounts\":" + userJson + "}";
        return result;
    }

    @RequestMapping(value = "/loginUser",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public void selectUserAccount(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
//        String account = "account1";
        UserAccount userAccount = BookstoreService.selectUserAccountByAccount(account);
        if(userAccount == null) {
//            request.getRequestDispatcher("redirect:/loginFailed.html？status=1");
//            return "redirec/loginFailed.html?status=1";
            response.sendRedirect("/login.html？status=1");
        } else {
            if (password.equals(userAccount.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user",userAccount);
//                request.getRequestDispatcher("/index.html?account="+userAccount.getAccount()).forward(request,response);
//                JSONObject userJson = JSONObject.fromObject(userAccount);
//                return userJson.toString();
//                return "/index.html?account="+userAccount.getAccount();
                int adminlevel = userAccount.getAdminlevel();
                System.out.println(adminlevel);
                if(adminlevel==1) {
                    response.sendRedirect("/index.html?account=" + userAccount.getAccount());
                } else if(adminlevel == 0) {
                    response.sendRedirect("/admin2.html");
                }
            } else {
//                request.getRequestDispatcher("/loginFailed.html?status=2");
//                return  "redirect:/loginFailed.html?status=2";
                response.sendRedirect("/login.html？status=2");
            }
        }
    }

    @RequestMapping(value = "/loginStep",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public void loginStep(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserAccount userAccount = (UserAccount)session.getAttribute("user");
        response.sendRedirect("/index.html?account="+userAccount.getAccount());
    }

    @RequestMapping(value = "/registerUser",method = RequestMethod.POST, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public void insertUserAccount(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        int adminLevel = 1;
        UserAccount userAccount = new UserAccount();
        userAccount.setAccount(account);
        userAccount.setAdminlevel(adminLevel);
        userAccount.setPassword(password);
        UserAccount userAccount1 = BookstoreService.selectUserAccountByAccount(account);
        if(userAccount1!=null) {
            response.sendRedirect("/register.html?status=1");
        } else {
            BookstoreService.insertUserAccount(userAccount);
            response.sendRedirect("/account/loginUser?account="+account+"&password="+password);
        }
    }

    @RequestMapping(value = "/cancellation",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public void cancellation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        response.sendRedirect("/index.html");
    }

    @RequestMapping(value = "/adminSelectUser",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public String adminSelectUser(){
//        String account = request.getParameter("account");
        String account = "account1";
        UserAccount userAccount = BookstoreService.selectUserAccountByAccount(account);
        if(userAccount == null) {
            return "none";
        } else {
            JSONObject userJson = JSONObject.fromObject(userAccount);
            return userJson.toString();
        }
    }

    @RequestMapping(value = "/adminUpdateUser",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public void adminUpdateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String account = request.getParameter("account");
//        String account = "account1";
//        int userId = 2;
        int userId = Integer.parseInt(request.getParameter("userId"));
        String password = "123456";
        BookstoreService.updateUserAccount(userId,password);
        response.sendRedirect("/admin2.html?status=1");
    }
}
