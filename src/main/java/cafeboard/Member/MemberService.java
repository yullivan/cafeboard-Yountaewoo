package cafeboard.Member;

import cafeboard.Member.Dto.LogInRequest;
import cafeboard.Member.Dto.MemberResquest;
import cafeboard.Member.Dto.MemberResponse;
import cafeboard.SecurityUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.net.http.HttpHeaders;
import java.util.NoSuchElementException;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    public MemberService(MemberRepository memberRepository, JwtProvider jwtProvider) {
        this.memberRepository = memberRepository;
        this.jwtProvider = jwtProvider;
    }

    //회원가입
    public MemberResquest createMember(MemberResponse memberRequest) {
        Member member = new Member(memberRequest.password(), memberRequest.userId(), memberRequest.name());
        memberRepository.save(member);
        return new MemberResquest(member.getUserName(), member.getPassword(), member.getUserId());
    }

    // 회원탈퇴
    @Transactional
    public void deleteMember(Long memberId) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(
                () -> new NoSuchElementException("ID를 찾을 수 없습니다:" + memberId));
        memberRepository.delete(findMember);
    }

    //로그인
    public String logIn(LogInRequest logInRequest) {
        Member findMember = memberRepository.findByUserId(logInRequest.userId()).orElseThrow(
                () -> new NoSuchElementException("해당하는 유저가 없습니다"));
        findMember.findByPassword(logInRequest.password());


        return jwtProvider.createToken(logInRequest.userId());
    }

    // 가입한 회원이 자신의 가입 정보를 조회하는 API
    public String getProfile(String authorization) {
        String[] tokenFormat = authorization.split(" ");
        String tokenType = tokenFormat[0];
        String token = tokenFormat[1];
        // Bearer 토큰인지 검증
        if (tokenType.equals("Bearer") == false) {
            throw new IllegalArgumentException("로그인 정보가 유효하지 않습니다");
        }
        // 유효한 JWT 토큰인지 검증
        if (jwtProvider.isValidToken(token) == false) {
            throw new IllegalArgumentException("로그인 정보가 유효하지 않습니다");
        }
        // JWT 토큰에서 userId 끄집어냄
        String userId = jwtProvider.getSubject(token);

        return userId;
    }
}
