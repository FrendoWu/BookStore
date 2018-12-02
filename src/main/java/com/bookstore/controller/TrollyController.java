package com.bookstore.controller;

import com.bookstore.domain.Book;
import com.bookstore.domain.Trolley;
import com.bookstore.domain.UserAccount;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/11/21.
 */
@Controller
@RequestMapping("trolley")
public class TrollyController {
    @Autowired
    @Qualifier("BookstoreService")
    private com.bookstore.service.BookstoreService BookstoreService;

    @RequestMapping(value = "/selectTrolley",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public String selectTrolleyByUserId(HttpServletRequest request) throws JsonProcessingException {
        HttpSession session = request.getSession();
        UserAccount userAccount = (UserAccount) session.getAttribute("user");
        int userId = userAccount.getId();
        List<Trolley> trolleyList = BookstoreService.selectTrolleyByUserId(userId);
        List<Book> bookList = new ArrayList<Book>();
        for(int i = 0; i < trolleyList.size();i++) {
            int bookId = trolleyList.get(i).getBookId();
            Book book = BookstoreService.selectBookByID(bookId);
            bookList.add(book);
        }
        if(bookList.size()==0) {
            return "none";
        } else {
            ObjectMapper mapper = new ObjectMapper();
            String booksJson = mapper.writeValueAsString(bookList);
            return booksJson;
        }
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

    @RequestMapping(value = "/deleteTrolley",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public void deleteTrolly(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("bookId"));
        HttpSession session = request.getSession();
        UserAccount userAccount = (UserAccount)session.getAttribute("user");
        int userId = userAccount.getId();
//        int id = 2;//测试
        BookstoreService.deleteTrolley(id,userId);
        response.sendRedirect("/trolley.html");
    }

    @RequestMapping(value = "/deleteAll",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public void deleteAll(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserAccount userAccount = (UserAccount) session.getAttribute("user");
//        int userId = userAccount.getId();
        int userId = 2;
        BookstoreService.deleteAllTrolleyByUserId(userId);
        response.sendRedirect("/trolley.html");
    }

    @RequestMapping(value = "/insertTrolley",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public void insertTrolly(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserAccount userAccount = (UserAccount) session.getAttribute("user");
        if(userAccount==null) {
            response.sendRedirect("/login.html");
        } else {
            int userId = userAccount.getId();
            int bookId = Integer.parseInt(request.getParameter("bookId"));
//        int userId = 0;
//        int bookId = Integer.parseInt(request.getParameter("bookId"));
//        int number = Integer.parseInt(request.getParameter("number"));
//        int bookId = 0;
//        int number = 22;
            List<Trolley> trolleys = BookstoreService.selectTrolleyByUserIdAndBookId(userId,bookId);
            if(trolleys.size() == 0) {
                int number = Integer.parseInt(request.getParameter("number"));
                BookstoreService.insertTrolly(userId, bookId, number);
                response.sendRedirect("/index.html?account=" + userAccount.getAccount() + "&status=1");
            } else {
                response.sendRedirect("/index.html?account=" + userAccount.getAccount() + "&status=2");
            }
        }
    }
}
