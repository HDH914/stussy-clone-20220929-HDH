package com.stussy.stussyclone20220929HDH.service.admin;

import com.stussy.stussyclone20220929HDH.dto.admin.ProductAdditionReqDto;

public interface ProductService {

    public boolean addProduct(ProductAdditionReqDto productAdditionReqDto) throws Exception;
}
