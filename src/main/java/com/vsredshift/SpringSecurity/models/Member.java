package com.vsredshift.SpringSecurity.models;

public class Member {

    private final Integer memberId;
    private final String fullName;

    public Member(Integer memberId, String fullName) {
        this.memberId = memberId;
        this.fullName = fullName;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
