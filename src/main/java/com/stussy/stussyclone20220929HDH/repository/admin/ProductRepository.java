package com.stussy.stussyclone20220929HDH.repository.admin;

import com.stussy.stussyclone20220929HDH.domain.Product;
import com.stussy.stussyclone20220929HDH.domain.ProductImgFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductRepository {

    public int saveProduct(Product product) throws Exception;
    public int saveImgFiles(List<ProductImgFile> product_img_files) throws Exception;
}
