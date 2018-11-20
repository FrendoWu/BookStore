package com.bookstore.service;

import com.bookstore.domain.Test;

import java.util.List;

public interface BookstoreService {
    /**************管理员功能相关接口*************/
    //显示所有用户账户
    List<Test> selectAllUserAccount();

    /**************用户功能相关接口**************/
    //根据account查询对应密码进行登录验证
    Test selectUserAccount(String account);
}
