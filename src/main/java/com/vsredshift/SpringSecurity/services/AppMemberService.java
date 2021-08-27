package com.vsredshift.SpringSecurity.services;

import com.vsredshift.SpringSecurity.auth.AppMemberDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Qualifier("fake")
public class AppMemberService implements UserDetailsService {

    private final AppMemberDao appMemberDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appMemberDao
                .selectAppMemberByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Username not found: " + username));
    }
}
