package com.stussy.stussyclone20220929HDH.controller.admin.api;


import com.stussy.stussyclone20220929HDH.aop.annotation.LogAspect;
import com.stussy.stussyclone20220929HDH.aop.annotation.ValidAspect;
import com.stussy.stussyclone20220929HDH.dto.CMRespDto;
import com.stussy.stussyclone20220929HDH.dto.admin.ProductAdditionReqDto;
import com.stussy.stussyclone20220929HDH.dto.validation.ValidationSequence;
import com.stussy.stussyclone20220929HDH.exception.CustomInternalServerErrorException;
import com.stussy.stussyclone20220929HDH.service.admin.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/admin")
@RestController
public class ProductApi {

    private final ProductService productService;

    @ValidAspect
    @LogAspect
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@Validated(ValidationSequence.class) ProductAdditionReqDto productAdditionReqDto, BindingResult bindingResult) throws Exception {
        try {
            productService.addProduct(productAdditionReqDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity
                .created(null)
                .body(new CMRespDto<>(1, "Successfully", productService.addProduct(productAdditionReqDto)));
    }
}