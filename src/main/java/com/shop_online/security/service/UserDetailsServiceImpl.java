package com.shop_online.security.service;

import com.shop_online.entity.SysManager;
import com.shop_online.mapper.SysManagerMapper;
import com.shop_online.service.SysManagerDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 账号登录 UserDetailsService
 *
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SysManagerDetailsService sysManagerDetailsService;
    private final SysManagerMapper sysManagerMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysManager sysManager = sysManagerMapper.getByUsername(username);
        if (sysManager == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return sysManagerDetailsService.getManagerDetails(sysManager);
    }
}
