package com.ssm.dao;

import com.ssm.damain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IProductDao {

    @Select("SELECT * FROM product")
    List<Product> findAllProduct();

    @Insert("INSERT INTO product VALUES (NULL ,#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void addProduct(Product product);

    @Select("SELECT * FROM product WHERE id = #{id}")
    Product findProductById(Integer id);

    @Delete("DELETE FROM product WHERE id = #{id}")
    void deleteProduct(Integer id);

    @Update("UPDATE product SET productnum=#{productNum},productname=#{productName},cityname=#{cityName},departuretime=#{departureTime},productprice=#{productPrice},productdesc=#{productDesc},productstatus=#{productStatus}  where id=#{id}")
    void updateProduct(Product product);
}
