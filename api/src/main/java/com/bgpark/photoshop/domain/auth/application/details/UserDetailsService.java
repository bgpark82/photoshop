package com.bgpark.photoshop.domain.auth.application.details;

import com.bgpark.photoshop.domain.auth.domain.UserDetails;

public interface UserDetailsService {

    UserDetails loadByUsername(String username);
}
