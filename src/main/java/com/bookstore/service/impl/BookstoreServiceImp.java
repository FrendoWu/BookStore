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

    @Override
    public List<Test> selectAllUserAccount() {
        return testDao.selectAllUserAccount();
    }

    @Override
    public Test selectUserAccount(String account) {
        return testDao.selectUserAccount(account);
    }
}
