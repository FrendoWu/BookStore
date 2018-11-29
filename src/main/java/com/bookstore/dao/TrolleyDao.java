package com.bookstore.dao;

import com.bookstore.domain.Trolley;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
//@Component
public interface TrolleyDao {

    @Select("select bookid,number from Trolley where userid = #{userId}")
    Trolley selectUserAccount(int userId);

    @Update("update Trolley set number = #{number} where userid=#{userId}")
    void updateTrollyByUserId(int userId, int number);

    @Insert("insert into Trolley values (null,#{userId},#{bookId},#{number})")
    void insertTrollyByUserId(@Param("userId") int userId, @Param("bookId")int bookId, @Param("number")int number);
}
