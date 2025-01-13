package cafeboard.Member;

import cafeboard.Member.Dto.CreateMember;
import cafeboard.Member.Dto.CreateMemberResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    //회원가입
    @PostMapping("/members")
    public CreateMemberResponse createMember(CreateMember createMember) {
        return memberService.createMember(createMember);
    }
}
