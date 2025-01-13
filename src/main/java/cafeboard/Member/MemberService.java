package cafeboard.Member;

import cafeboard.Member.Dto.CreateMember;
import cafeboard.Member.Dto.CreateMemberResponse;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public CreateMemberResponse createMember(CreateMember createMember) {
        Member member = new Member(createMember.password(), createMember.userId(), createMember.name());
        memberRepository.save(member);
        return new CreateMemberResponse(member.getUserName(), member.getPassword(), member.getUserId());
    }
}
