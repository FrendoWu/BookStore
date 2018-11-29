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
    public List<Order> slctOdrsByUsridAndStat(int userid, int status) {
        return orderDao.slctOdrsByUsridAndStat(userid, status);
    }

    @Override
    public void InsertOrders(Order order) {
        orderDao.InsertOrders(order);
    }

    @Override
    public List<Book> selectBookByID(int id){
        return bookDao.selectBookByID(id);
    }

    @Override
    public void InsertBooks(Book book){
        bookDao.InsertBooks(book);
    }

    @Autowired
    TrolleyDao trolley_Dao;
    @Override
    public Trolley selectTrolleyByUserId(int userId) {return trolley_Dao.selectUserAccount(userId);}

    @Override
    public void insertTrollyByUserId(int userId, int bookId, int number) {
        trolley_Dao.insertTrollyByUserId(userId,bookId,number);
    }

    @Override
    public void updateTrollyByUserId(int userId, int number) {
        trolley_Dao.updateTrollyByUserId(userId,number);
    }
}
