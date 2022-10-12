package com.stussy.stussyclone20220929HDH.domain;

import com.stussy.stussyclone20220929HDH.dto.validation.ValidationGroups;
import lombok.Builder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public class Product {
    private int id;
    private String category;
    private int group_id;
    private String name;
    private int price;
    private String color;
    private String size;

    private String info_simple;
    private String info_detail;
    private String info_option;
    private String info_management;
    private String info_shipping;

    private List<ProductImgFile> product_image_files;

    private LocalDateTime create_date;
    private LocalDateTime update_date;


}
