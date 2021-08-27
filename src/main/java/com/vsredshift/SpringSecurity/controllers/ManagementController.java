package com.vsredshift.SpringSecurity.controllers;

import com.vsredshift.SpringSecurity.models.Member;
import com.vsredshift.SpringSecurity.services.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/api/v1/members")
@AllArgsConstructor
public class ManagementController {

    private final MemberService memberService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_JUNIOR_ADMIN')")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('member:write')")
    public void registerNewMember(@RequestBody Member member) {
        memberService.registerNewMember(member);
    }

    @DeleteMapping(path = "{memberId}")
    @PreAuthorize("hasAnyAuthority('member:write')")
    public void deleteMember(@PathVariable("memberId") Integer memberId) {
        memberService.deleteMember(memberId);
    }

    @PutMapping(path = "{memberId}")
    @PreAuthorize("hasAnyAuthority('member:write')")
    public void updateMember(@PathVariable("memberId") Integer memberId, @RequestBody Member member) {
        memberService.updateMember(memberId, member);
    }

}
