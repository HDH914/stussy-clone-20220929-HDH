package com.stussy.stussyclone20220929HDH.repository;

import com.stussy.stussyclone20220929HDH.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountRepository {
    public int save(User user);
    public User findUserByEmail(String Email);
}
