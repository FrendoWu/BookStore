package com.bookstore.controller;

import com.bookstore.domain.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

    @RequestMapping(value = "/allBooksIn",method = RequestMethod.GET, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public String allBooksIn(HttpServletRequest request) throws JsonProcessingException {
        String books = request.getParameter("books");
        List<Book> bookList = new ArrayList<Book>();
        for(int i = 0; i < books.length(); i=i+2) {
            int bookId = books.charAt(i) - '0';
            Book book = BookstoreService.selectBookByID(bookId);
            bookList.add(book);
        }
        ObjectMapper mapper = new ObjectMapper();
        String bookJson = mapper.writeValueAsString(bookList);
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

    @RequestMapping(value = "/insertBook",method = RequestMethod.POST, produces= "application/json;charset=UTF-8")
    @ResponseBody
    public void insertBook(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String introduction = request.getParameter("introduction");
        int money = Integer.parseInt(request.getParameter("money"));
//        String imgurl = "books/活着.jpg";
        String imgurl = "books/" + request.getParameter("imgName");
        File importFile = new File(imgurl);
        InputStream is = file.getInputStream();
        FileOutputStream os = new FileOutputStream(importFile);
        byte[] by = new byte[1024*10];
        while(is.read(by)!=-1){
            os.write(by);
        }
        is.close();
        os.close();
//        String name = "活着";
//        String category = "文学";
//        String introduction = "程序员必读";
//        int money = 55;
//        String imgurl = "books/活着.jpg";
        Book book = new Book();
        book.setName(name);
        book.setCategory(category);
        book.setIntroduction(introduction);;
        book.setMoney(money);
        book.setImgurl(imgurl+"活着.jpg");
        BookstoreService.insertBook(book);
        response.sendRedirect("/admin2.html");
    }
}
