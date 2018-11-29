package com.bookstore.service;

import com.bookstore.domain.*;

import java.util.List;

public interface BookstoreService {
    /**************管理员功能相关接口*************/
    //显示所有用户账户
    List<UserAccount> selectAllUserAccount();

    /**************用户功能相关接口**************/
    //根据account查询对应密码进行登录验证
    UserAccount selectUserAccountByAccount(String account);

    //用户注册
    void insertUserAccount(UserAccount userAccount);

    /**************订单相关接口**************/
    //根据用户及订单状态查询订单
    List<Order> slctOdrsByUsridAndStat(int userid, int status); 
    
    /**************订单相关接口**************/
    //添加新的订单信息
    void InsertOrders(Order order);

    /**************书籍相关接口**************/
    //根据书籍id查询书籍信息
    List<Book> selectBookByID(int id);

    /**************书籍相关接口**************/
    //添加新的书籍信息
    void InsertBooks(Book book);

    //根据用户id查询书本id和书本数量
    Trolley selectTrolleyByUserId(int userId);

    //添加一条用户记录
    void insertTrollyByUserId(int userId,int bookId,int number);

    //修改一条用户记录
    void updateTrollyByUserId(int userId,int number);
}
