package com.bookstore.service;

import com.bookstore.domain.*;
import com.sun.org.apache.xpath.internal.operations.Or;

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

    //重置用户密码为123456
    void updateUserAccount(int id,String password);

    //显示所有书籍
    List<Book> selectAllBooks();

    //按种类显示书籍
    List<Book> selectBooksByCategory(String category);

    /**************订单相关接口**************/
    //根据用户及订单状态查询订单
    List<Order> slctOdrsByUsridAndStat(int userid, int status); 
    
    /**************订单相关接口**************/
    //添加新的订单信息
    void insertOrders(Order order);

    //按状态查看订单
    List<Order> selectOrdersByStatus(int userId, int status);

    List<Order> selectAllOrdersByStatus(int status);

    //修改订单状态
    void updateStatus(int id, int status);

    /**************书籍相关接口**************/
    //根据书籍id查询书籍信息
    Book selectBookByID(int id);

    /**************书籍相关接口**************/
    //添加新的书籍信息
    void insertBook(Book book);

    //根据用户id查询购物车信息
    List<Trolley> selectTrolleyByUserId(int userId);

    //根据用户id查询用于判断该书在购物车中是否已经存在
    List<Trolley> selectTrolleyByUserIdAndBookId(int userId, int bookId);

    //加入购物车
    void insertTrolly(int userId,int bookId,int number);

    //从购物车中移除书
    void deleteTrolley(int id,int userId);

    //清空某个用户的购物车
    void deleteAllTrolleyByUserId(int userId);

    //修改一条用户记录
    void updateTrollyByUserId(int userId,int number);
}
