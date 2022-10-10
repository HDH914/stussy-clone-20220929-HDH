package com.stussy.stussyclone20220929HDH.service.auth;

import com.stussy.stussyclone20220929HDH.domain.User;
import com.stussy.stussyclone20220929HDH.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {  //SecurityConfig의 email이 매개값으로 들어옴
        User user = accountRepository.findUserByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("존재하지 않는 아이디입니다.");
        }
        return new PrincipalDetails(user);
    }
}
