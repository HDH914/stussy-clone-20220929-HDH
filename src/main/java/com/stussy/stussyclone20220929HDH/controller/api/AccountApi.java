package com.stussy.stussyclone20220929HDH.controller.api;

import com.stussy.stussyclone20220929HDH.aop.annotation.LogAspect;
import com.stussy.stussyclone20220929HDH.aop.annotation.ValidAspect;
import com.stussy.stussyclone20220929HDH.dto.CMRespDto;
import com.stussy.stussyclone20220929HDH.dto.account.RegisterReqDto;
import com.stussy.stussyclone20220929HDH.dto.validation.ValidationSequence;
import com.stussy.stussyclone20220929HDH.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
@RequiredArgsConstructor
public class AccountApi {

    private final AccountService accountService;

    @LogAspect
    @ValidAspect
    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated(ValidationSequence.class) //순서를 정하는 것.
                                          @RequestBody RegisterReqDto registerReqDto,
                                      BindingResult bindingResult) throws Exception {  //json은 @RequestBody 필요

        accountService.checkDuplicateEmail(registerReqDto.getEmail());

        accountService.register(registerReqDto);

        return ResponseEntity.ok().body(new CMRespDto<>(1, "Successfully registered", registerReqDto));
    }
}
