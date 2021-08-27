package com.vsredshift.SpringSecurity.controllers;

import com.vsredshift.SpringSecurity.models.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.vsredshift.SpringSecurity.services.MemberService.MEMBERS;

@RestController
@RequestMapping("api/v1/members")
public class MemberController {

    @GetMapping("{memberId}")
    public Member getMember(@PathVariable("memberId") Integer memberId) {
        return MEMBERS.stream()
                .filter(member -> memberId.equals(member.getMemberId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Member " + memberId + " does not exist"
                ));
    }


}
