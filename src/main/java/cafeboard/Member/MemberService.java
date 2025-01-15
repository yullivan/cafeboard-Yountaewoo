package cafeboard.Member;

import cafeboard.Member.Dto.MemberResquest;
import cafeboard.Member.Dto.MemberResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
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
}
