package com.stussy.stussyclone20220929HDH.service.admin;

import com.stussy.stussyclone20220929HDH.domain.Product;
import com.stussy.stussyclone20220929HDH.dto.admin.ProductAdditionReqDto;

import java.util.List;

public interface ProductService {

    public boolean addProduct(ProductAdditionReqDto productAdditionReqDto) throws Exception;

    public List<Product> getProductList(int pageNumber, String category, String searchText) throws Exception;
}
