package com.vsredshift.SpringSecurity.services;

import com.google.common.collect.Lists;
import com.vsredshift.SpringSecurity.auth.AppMember;
import com.vsredshift.SpringSecurity.auth.AppMemberDao;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.vsredshift.SpringSecurity.security.UserRole.*;

@Repository("fake")
@AllArgsConstructor
public class FakeAppMemberService implements AppMemberDao {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<AppMember> selectAppMemberByUsername(String username) {
        return getAppMembers()
                .stream()
                .filter(appMember -> username.equals(appMember.getUsername()))
                .findFirst();
    }

    private List<AppMember> getAppMembers() {
        List<AppMember> appMembers = Lists.newArrayList(
                new AppMember(
                        "annasmith",
                        passwordEncoder.encode("password"),
                        CUSTOMER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new AppMember(
                        "admin",
                        passwordEncoder.encode("admin"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new AppMember(
                        "junior",
                        passwordEncoder.encode("junior"),
                        JUNIOR_ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new AppMember(
                        "guest",
                        passwordEncoder.encode("guest"),
                        GUEST.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );
        return appMembers;
    }
}
