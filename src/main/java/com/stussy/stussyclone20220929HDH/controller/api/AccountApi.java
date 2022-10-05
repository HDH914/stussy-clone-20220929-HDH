package com.stussy.stussyclone20220929HDH.controller.api;

import com.stussy.stussyclone20220929HDH.aop.annotation.LogAspect;
import com.stussy.stussyclone20220929HDH.aop.annotation.ValidAspect;
import com.stussy.stussyclone20220929HDH.dto.CMRespDto;
import com.stussy.stussyclone20220929HDH.dto.account.RegisterReqDto;
import com.stussy.stussyclone20220929HDH.dto.validation.ValidationSequence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountApi {
    @LogAspect
    @ValidAspect
    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated(ValidationSequence.class) @RequestBody RegisterReqDto registerReqDto, BindingResult bindingResult){  //json은 @RequestBody 필요


        return ResponseEntity.ok(null);
    }


}
