package com.stussy.stussyclone20220929HDH.service;

import com.stussy.stussyclone20220929HDH.dto.account.RegisterReqDto;

public interface AccountService {
    public boolean checkDuplicateEmail(String email);

    public boolean register(RegisterReqDto registerReqDto) throws Exception;
}
