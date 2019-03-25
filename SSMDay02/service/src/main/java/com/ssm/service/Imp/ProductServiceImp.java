package com.ssm.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.damain.Product;
import com.ssm.dao.IProductDao;
import com.ssm.service.IProductService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImp implements IProductService{
    @Autowired
    private IProductDao iProductDao;
    @Override
    public List<Product> findAllProduct() {
        return iProductDao.findAllProduct();
    }

    @Override
    public void addProduct(Product product) {
        iProductDao.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        iProductDao.updateProduct(product);
    }

    @Override
    public Product findProductById(Integer id) {
        return iProductDao.findProductById(id);
    }

    @Override
    public void deleteProduct(Integer id) {
        iProductDao.deleteProduct(id);
    }

    /**
     * 分页查询
     * @param pageNum 当前页
     * @param pageSize 页面大小
     * @return
     */
    @Override
    public PageInfo<Product> findProductByPage(int pageNum, int pageSize) {
        //使用PageHelper分页,会自动对其后的第一行查询进行分页
        PageHelper.startPage(pageNum,pageSize);
        //该查询会被分页
        List<Product> products = iProductDao.findAllProduct();
        //使用pageInfo包装查询后的结果
        return new PageInfo<Product>(products);
    }


}
