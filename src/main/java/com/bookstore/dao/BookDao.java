package com.bookstore.dao;

import com.bookstore.domain.Book;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao {
    @Select("select * from book where id = #{id}")
    Book selectBookByID(int id); //select a book by id

    @Insert("insert into book (id, category, name, introduction, money, imgurl) values (null,#{category},#{name},#{introduction},#{money},#{imgurl})")
    void insertBook(Book book);

    @Select("select * from book")
    List<Book> selectAllBooks();

    @Select("select * from book where category = #{category}")
    List<Book> selectBooksByCategory(String category);
}