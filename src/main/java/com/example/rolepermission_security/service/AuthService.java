package com.example.rolepermission_security.service;

import com.example.rolepermission_security.entity.Role;
import com.example.rolepermission_security.entity.User;
import com.example.rolepermission_security.entity.enums.RoleEnum;
import com.example.rolepermission_security.repository.RoleRepository;
import com.example.rolepermission_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sun.security.util.Password;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
final UserRepository userRepository;
final PasswordEncoder passwordEncoder;
final RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityContext context= SecurityContextHolder.getContext();
        Authentication authentication=context.getAuthentication();
        Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
//        System.out.println(authorities);//null
//
//        System.out.println(authentication.getAuthorities());//username password
//        System.out.println(authentication.getPrincipal());//bu kirgan odam
//        System.out.println(authentication.getDetails());//info

        return userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
    public String register(RegisterDto dto) {
        User user=new User();
        user.setUserName(dto.getUsername());
        user.setFullName(dto.getFullName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        Optional<Role> byName = roleRepository.findByName(RoleEnum.USER);
        Role role=byName.get();
        user.setRoles(new HashSet<>(Arrays.asList(role)));
        userRepository.save(user);
        return "Registered";
    }
}
