package com.bookstore.service.impl;

import com.bookstore.dao.*;
import com.bookstore.domain.*;
import com.bookstore.service.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
@Service("BookstoreService")
public class BookstoreServiceImp implements BookstoreService {
    //Dao层自动注入
    @Autowired
    TestDao testDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    UserAccountDao userAccountDao;
    @Autowired
    TrolleyDao trolley_Dao;

    @Override
    public List<UserAccount> selectAllUserAccount() {
        return  userAccountDao.selectAllUserAccount();
    }

    @Override
    public UserAccount selectUserAccountByAccount(String account) {
        return userAccountDao.selectUserAccountByAccount(account);
    }

    @Override
    public void insertUserAccount(UserAccount userAccount) {
        userAccountDao.insertUserAccount(userAccount);
    }

    @Override
    public void updateUserAccount(int id, String password) {
        userAccountDao.updateUserAccount(id,password);
    }

    @Override
    public List<Book> selectAllBooks() {
        return bookDao.selectAllBooks();
    }

    @Override
    public List<Book> selectBooksByCategory(String category) {
        return  bookDao.selectBooksByCategory(category);
    }

    @Override
    public List<Order> slctOdrsByUsridAndStat(int userid, int status) {
        return orderDao.slctOdrsByUsridAndStat(userid, status);
    }

    @Override
    public void insertOrders(Order order) {
        orderDao.insertOrders(order);
    }

    @Override
    public List<Order> selectOrdersByStatus(int userId, int status) {
        return orderDao.selectOrdersByStatus(userId,status);
    }

    @Override
    public List<Order> selectAllOrdersByStatus(int status) {
        return orderDao.selectAllOrdersByStatus(status);
    }

    @Override
    public void updateStatus(int id, int status) {
        orderDao.updateOrderStatus(id, status);
    }

    @Override
    public Book selectBookByID(int id){
        return bookDao.selectBookByID(id);
    }

    @Override
    public void insertBook(Book book){
        bookDao.insertBook(book);
    }

    @Override
    public List<Trolley> selectTrolleyByUserId(int userId) {return trolley_Dao.selectTrolleyByUserId(userId);}

    @Override
    public List<Trolley> selectTrolleyByUserIdAndBookId(int userId, int bookId) {
        return trolley_Dao.selectTrolleyByUserIdAndBookId(userId, bookId);
    }

    @Override
    public void insertTrolly(int userId, int bookId, int number) {
        trolley_Dao.insertTrolly(userId,bookId,number);
    }

    @Override
    public void deleteTrolley(int id, int userId) {
        trolley_Dao.deleteTrolley(id, userId);
    }

    @Override
    public void deleteAllTrolleyByUserId(int userId) {
        trolley_Dao.deleteAllTrolleyByUserId(userId);
    }

    @Override
    public void updateTrollyByUserId(int userId, int number) {
        trolley_Dao.updateTrollyByUserId(userId,number);
    }
}
