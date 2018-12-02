package com.bookstore.dao;

import com.bookstore.domain.Order;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    @Select("select id, userid, books, allmoney, status from user_account where userid = #{userid} and status = #{status}")
    List<Order> slctOdrsByUsridAndStat(int userid, int status); //select orders by userid and status

    @Insert("insert into orders (id, userid, books, allmoney, status) values (null,#{userId},#{books},#{allMoney},#{status})")
    void insertOrders(Order order);

    @Select("select * from orders where userid=#{userId} and status=#{status}")
    List<Order> selectOrdersByStatus(@Param("userId")int userId, @Param("status")int status);

    @Update("update orders set status=#{status} where id=#{id}")
    void updateOrderStatus(@Param("id")int id,@Param("status")int status);

    @Select("select * from orders where status=#{status}")
    List<Order> selectAllOrdersByStatus(@Param("status")int status);
}
