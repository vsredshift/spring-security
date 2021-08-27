package com.vsredshift.SpringSecurity.auth;

import java.util.Optional;

public interface AppMemberDao {

    public Optional<AppMember> selectAppMemberByUsername(String username);
}
