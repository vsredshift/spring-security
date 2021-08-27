package com.vsredshift.SpringSecurity.services;

import com.vsredshift.SpringSecurity.models.Member;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MemberService {

    public static final List<Member> MEMBERS = Arrays.asList(
            new Member(1, "John Doe"),
            new Member(2, "Jane Doe"),
            new Member(3, "Jim Smith"),
            new Member(4, "Ann Summer")
    );


    public List<Member> getAllMembers() {
        return MEMBERS;
    }

    public void registerNewMember(Member member) {
//        Member newMember = new Member(member.getMemberId(), member.getFullName());
//        MEMBERS.add(newMember);
        System.out.println(member);
    }

    public void deleteMember(Integer memberId) {
        System.out.println(memberId);
    }

    public void updateMember(Integer memberId, Member member) {
        System.out.println(String.format("%s %s", memberId, member));
    }
}
