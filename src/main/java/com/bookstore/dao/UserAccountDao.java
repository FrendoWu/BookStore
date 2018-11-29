package com.bookstore.dao;

import com.bookstore.domain.UserAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountDao {
    @Select("select id,account,password,adminlevel from user_account where account = #{account}")
    UserAccount selectUserAccountByAccount(String account);

    @Insert("insert into user_account (id,account,password,adminlevel) values (null,#{account},#{password},#{adminLevel})")
    void insertUserAccount(UserAccount userAccount);

    @Select("select * from user_account")
    List<UserAccount> selectAllUserAccount();
}
