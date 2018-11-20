package com.bookstore.dao;

import com.bookstore.domain.Test;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDao {
    @Select("select * from user_account")
    List<Test> selectAllUserAccount();

    @Select("select id,account,password from user_account where account = #{account}")
    Test selectUserAccount(String account);
}
