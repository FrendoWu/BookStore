package com.bookstore.controller;

import com.bookstore.domain.Order;
import com.bookstore.domain.Trolley;
import com.bookstore.domain.UserAccount;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.List;

@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    @Qualifier("BookstoreService")
    private com.bookstore.service.BookstoreService BookstoreService;

    @RequestMapping(value = "/insertOrder",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public void insertOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserAccount userAccount = (UserAccount) session.getAttribute("user");
        int userId = userAccount.getId();
//        int userId = 2;
        List<Trolley> trolleys = BookstoreService.selectTrolleyByUserId(userId);
        String books = "";
        int allmoney=0;
        for(int i = 0; i < trolleys.size();i++) {
            books = books + trolleys.get(i).getBookId() + trolleys.get(i).getNumber();
            allmoney = allmoney + BookstoreService.selectBookByID(trolleys.get(i).getBookId()).getMoney() * trolleys.get(i).getNumber();
        }
//        String books = "1234";
//        int allmoney = 500;
        int status = 0;
        Order order = new Order();
        order.setUserId(userId);
        order.setAllMoney(allmoney);
        order.setBooks(books);
        order.setStatus(status);
        BookstoreService.deleteAllTrolleyByUserId(userId);
        BookstoreService.insertOrders(order);
        response.sendRedirect("/order.html?flag=1");
    }

    @RequestMapping(value = "/selectOrdersByStatus",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public String selectOrdersByStatus(HttpServletRequest request) throws JsonProcessingException {
        int flag=Integer.parseInt(request.getParameter("flag"));
        if(flag==1) {
            HttpSession session = request.getSession();
            UserAccount userAccount = (UserAccount) session.getAttribute("user");
            int userId = userAccount.getId();
            int status = Integer.parseInt(request.getParameter("status"));
//        int status = 0;
//        int userId = 2;
            List<Order> orderList = BookstoreService.selectOrdersByStatus(userId, status);
            ObjectMapper objectMapper = new ObjectMapper();
            String ordersJson = objectMapper.writeValueAsString(orderList);
            return ordersJson;
        } else if(flag==0) {
            int status = Integer.parseInt(request.getParameter("status"));
            List<Order> orderList = BookstoreService.selectAllOrdersByStatus(status);
            ObjectMapper objectMapper = new ObjectMapper();
            String ordersJson = objectMapper.writeValueAsString(orderList);
            return ordersJson;
        }
        return "";
    }

    @RequestMapping(value = "/updateStatus",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public void updateStatus(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserAccount userAccount = (UserAccount) session.getAttribute("user");
        int userId = userAccount.getId();
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int flag= Integer.parseInt(request.getParameter("flag"));
//        int status = Integer.parseInt(request.getParameter("status"));
//        int orderId = 2;
        int status = 1;
        BookstoreService.updateStatus(orderId,status);
        if(flag==1) {
            response.sendRedirect("/admin2.html?status=2&flag=1");
        } else {
            response.sendRedirect("/order.html?flag=1");
        }
    }
}
