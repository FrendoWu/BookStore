package com.bookstore.dao;

import com.bookstore.domain.Trolley;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Component
public interface TrolleyDao {

    @Select("select * from Trolley where userid = #{userId}")
    List<Trolley> selectTrolleyByUserId(int userId);

    @Update("update Trolley set number = #{number} where userid=#{userId}")
    void updateTrollyByUserId(int userId, int number);

    @Select("select * from trolley where userid=#{userId} and bookid=#{bookId}")
    List<Trolley> selectTrolleyByUserIdAndBookId(@Param("userId")int userId,@Param("bookId")int bookId);

    @Insert("insert into Trolley values (null,#{userId},#{bookId},#{number})")
    void insertTrolly(@Param("userId") int userId, @Param("bookId")int bookId, @Param("number")int number);

    @Delete("delete from trolley where bookId=#{id} and userId=#{userId}")
    void deleteTrolley(@Param("id")int id,@Param("userId")int userId);

    @Delete("delete from trolley where userid=#{userId}")
    void deleteAllTrolleyByUserId(int userId);
}
