package com.ssm.web;

import com.github.pagehelper.PageInfo;
import com.ssm.damain.Product;
import com.ssm.service.IProductService;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.annotation.security.RolesAllowed;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RolesAllowed({"ROLE_User"})
@RequestMapping("/product")
public class ProductWeb {
    @Autowired
    private IndexWriter indexWriter;
    @Autowired
    private IProductService productService;



    @RequestMapping("/findProduct")
    public ModelAndView findAllProduct(@RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "2") int pageSize){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo",productService.findProductByPage(pageNum,pageSize));
        modelAndView.setViewName("product-list");
        return modelAndView;
    }

    @RequestMapping("/addProduct")
    public String addProduct(Product product){
        try {
            productService.addProduct(product);
            return "redirect:/product/findProduct";
        } catch (Exception e) {
            throw new RuntimeException("该商品编号已存在!");
        }
    }
    @RequestMapping("/updateProduct")
    public String updateProduct(Product product){
            productService.updateProduct(product);
            return "redirect:/product/findProduct";
    }
    @RequestMapping("/updateShowProduct")
    public ModelAndView updateProduct(Integer id){
        System.out.println(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product",productService.findProductById(id));
        modelAndView.setViewName("product-update");
        return modelAndView;
    }

    @RequestMapping("/deleteProduct")
    public String deleteProduct(String ids){
        if (ids != null || !ids.equals("")){
            String[] idList = ids.split(",");
            for (String id : idList) {
                productService.deleteProduct(Integer.valueOf(id));
            }

        }
        return "redirect:/product/findProduct";
    }

    @RequestMapping("/searchProduct")
    public ModelAndView searchProduct(@RequestParam(defaultValue = "1") int pageNum,
                                       @RequestParam(defaultValue = "2") int pageSize) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        PageInfo<Product> products = productService.findProductByPage(pageNum, pageSize);
        List<Product> productsList = products.getList();
        ArrayList<Document> documents = new ArrayList<Document>();
        for (Product product : productsList) {
            Document document = new Document();
            document.add(new StringField("id",product.getId() + "",Field.Store.YES));
            document.add(new StringField("productNum",product.getProductName() + "",Field.Store.YES));
            document.add(new TextField("productName",product.getProductName(),Field.Store.YES));
            document.add(new TextField("cityName",product.getCityName(),Field.Store.YES));
            document.add(new TextField("departureTime",product.getDepartureTime()+"",Field.Store.YES));
            document.add(new StringField("productPrice",product.getProductPrice()+"",Field.Store.YES));
            document.add(new TextField("productDesc",product.getProductDesc(),Field.Store.YES));
            document.add(new StoredField("productStatus",product.getProductStatus()));
            documents.add(document);
        }
        for (Document document : documents) {
            indexWriter.addDocument(document);
            indexWriter.commit();
        }
        indexWriter.close();
        modelAndView.addObject("pageInfo",productService.findProductByPage(pageNum,pageSize));
        modelAndView.setViewName("product-list");
        return modelAndView;
    }
}
