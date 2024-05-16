package com.example.board.service;

import com.example.board.dto.CustomUserDetails;
import com.example.board.entity.UserEntity;
import com.example.board.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    // 생성자 추가
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<UserEntity> byUserId = userRepository.findByUserId(userId);
        if (byUserId == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + userId);
        }
        return null;
    }
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 여기서는 임시로 "ROLE_USER" 권한을 부여합니다.
        // 실제로는 사용자의 권한을 데이터베이스나 다른 소스에서 가져와야 합니다.
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }
}
