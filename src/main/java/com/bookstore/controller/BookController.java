package com.bookstore.controller;

import com.bookstore.domain.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("book")
public class BookController {
    //自动注入service
    @Autowired
    @Qualifier("BookstoreService")
    private com.bookstore.service.BookstoreService BookstoreService;

    @RequestMapping(value = "/allBooks",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public String selectAllBooks() throws JsonProcessingException {
        List<Book> books = BookstoreService.selectAllBooks();
        ObjectMapper mapper = new ObjectMapper();
        String bookJson = mapper.writeValueAsString(books);
        String result =  bookJson;
        return result;
    }

    @RequestMapping(value = "/selectBooksByCategory",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public String selectBooksByCategory(HttpServletRequest request) throws JsonProcessingException {
        String category = request.getParameter("category");
        List<Book> books = BookstoreService.selectBooksByCategory(category);
        ObjectMapper mapper = new ObjectMapper();
        String bookJson = mapper.writeValueAsString(books);
        String result =  bookJson;
        return result;
    }
}
