//package com.shoppify.entity;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
//public class CustomUserDetails extends User implements UserDetails {
//    private String username;
//    private String email;
//    private String password;
//    private String phoneNumber;
//
//    public CustomUserDetails(User byUsername){
//        this.username = byUsername.getUsername();
//        this.email = byUsername.getEmail();
//        this.phoneNumber = byUsername.getPhoneNumber();
//        this.password = byUsername.getPassword();
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}
