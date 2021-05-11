package com.members.members.firebasedb;

import com.members.members.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = "http://localhost:4200")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/add")
    public String addMember(@RequestBody Member member) throws ExecutionException, InterruptedException {
        return memberService.saveMember(member);
    }

    @GetMapping("/find/{name}")
    public Member getMember(@PathVariable String name) throws ExecutionException, InterruptedException {
        return memberService.getMemberDetails(name);
    }

    @GetMapping("/all")
    public List<Member> getMembers() throws ExecutionException, InterruptedException {
        List<Member> members = memberService.getMembersDetails();
        return members;
    }

    @PutMapping("/update")
    public String updateMember(@RequestBody Member member) throws ExecutionException, InterruptedException {
        return memberService.updateMember(member);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMember(@PathVariable String id) throws ExecutionException, InterruptedException {
        memberService.deleteMember(id);
    }

}
