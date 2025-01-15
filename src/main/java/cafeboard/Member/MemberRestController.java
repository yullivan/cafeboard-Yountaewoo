package cafeboard.Member;

import cafeboard.Member.Dto.MemberResquest;
import cafeboard.Member.Dto.MemberResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    //회원가입
    @PostMapping("/members")
    public MemberResquest createMember(@RequestBody MemberResponse memberRequest) {
        return memberService.createMember(memberRequest);
    }

    //회원탈퇴
    @DeleteMapping("/members/{memberId}")
    public void deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
    }
}
