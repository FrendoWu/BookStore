package com.bookstore.dao;

import com.bookstore.domain.Order;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    @Select("select id, userid, books, allmoney, status from user_account where userid = #{userid} and status = #{status}")
    List<Order> slctOdrsByUsridAndStat(int userid, int status); //select orders by userid and status

    @Insert("insert into orders (id, userid, books, allmoney, status) values (null,#{userid},#{books},#{allmoney},#{status})")
    void InsertOrders(Order order);
}
