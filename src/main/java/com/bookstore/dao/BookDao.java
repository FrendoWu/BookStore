package com.bookstore.dao;

import com.bookstore.domain.Book;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao {
    @Select("select id, category, name, introduction, money, imgurl from user_account where id = #{id}")
    List<Book> selectBookByID(int id); //select a book by id or by something else

    @Insert("insert into orders (id, category, name, introduction, money, imgurl) values (#{id},#{category},#{name},#{introduction},#{money},#{imgurl})")
    void InsertBooks(Book book);

    @Select("select * from book")
    List<Book> selectAllBooks();

    @Select("select * from book where category = #{category}")
    List<Book> selectBooksByCategory(String category);
}