package com.bookstore.dao;

import com.bookstore.domain.UserAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Update("update user_account set password=#{password} where id=#{id}")
    void updateUserAccount(@Param("id")int id, @Param("password")String password);
}
