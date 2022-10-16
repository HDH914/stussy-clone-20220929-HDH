package com.stussy.stussyclone20220929HDH.dto.admin;

import com.stussy.stussyclone20220929HDH.domain.ProductImgFile;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ProductListRespDto {
    private int id;
    private String category;
    private String name;
    private int price;
    private String color;
    private String size;

    private String infoSimple;
    private String infoDetail;
    private String infoOption;
    private String infoManagement;
    private String infoShipping;

    private List<ProductImgFile> product_image_files;

    private int productTotalCount;
}
