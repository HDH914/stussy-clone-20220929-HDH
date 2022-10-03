package com.stussy.stussyclone20220929HDH.controller.api;

import com.stussy.stussyclone20220929HDH.dto.account.RegisterReqDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountApi {

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReqDto registerReqDto){  //json은 @RequestBody 필요

        log.info("{}", registerReqDto);

        return ResponseEntity.ok(null);
    }
}
