package com.ssm.service;

import com.github.pagehelper.PageInfo;
import com.ssm.damain.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAllProduct();

    void addProduct(Product product);

    void updateProduct(Product product);

    Product findProductById(Integer id);

    void deleteProduct(Integer id);

    PageInfo<Product> findProductByPage(int pageNum,int pageSize);
}
