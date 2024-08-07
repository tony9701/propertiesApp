package com.topHomes.propertiesApp.service.impl;

import com.topHomes.propertiesApp.model.entity.User;
import com.topHomes.propertiesApp.model.entity.UserRole;
import com.topHomes.propertiesApp.model.enums.UserRoleEnum;
import com.topHomes.propertiesApp.model.user.PropertiesAppUserDetails;
import com.topHomes.propertiesApp.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository
                .findByEmail(email)
                .map(UserDetailsServiceImpl::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found!"));

    }

    public static UserDetails map(User user) {

        List<GrantedAuthority> list = user.getRoles().stream().map(UserRole::getRole).map(UserDetailsServiceImpl::map).toList();

        return new PropertiesAppUserDetails(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(UserRole::getRole).map(UserDetailsServiceImpl::map).toList()
        );
    }

    public static GrantedAuthority map(UserRoleEnum role) {
        return new SimpleGrantedAuthority("ROLE_" + role.name());
    }
}
