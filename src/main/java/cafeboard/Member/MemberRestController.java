package cafeboard.Member;

import cafeboard.Member.Dto.LogInRequest;
import cafeboard.Member.Dto.MemberResquest;
import cafeboard.Member.Dto.MemberResponse;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    //회원 생성
    @PostMapping("/members")
    public MemberResquest createMember(@RequestBody MemberResponse memberRequest) {
        return memberService.createMember(memberRequest);
    }

    //회원탈퇴
    @DeleteMapping("/members/{memberId}")
    public void deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
    }

    //로그인
    @PostMapping("/logIn")
    public String logIn(@RequestBody LogInRequest logInRequest) {
        return memberService.logIn(logInRequest);
    }

    // 가입한 회원이 자신의 가입 정보를 조회하는 API
    @GetMapping("/me")
    public MemberResponse getProfile(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        return memberService.getProfile(authorization);
    }
}