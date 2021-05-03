package com.bgpark.photoshop.domain.auth.application;

public interface UserDetailsService {

    UserDetails loadByUsername(String username);
}
