package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;



public class MemberService {

    private final MemberRepository memberRepository;  // final means cannot inherit

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    // sign up 
    public Long join(Member member) {
        // cannot make duplicated member(same name)
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("member already exists");
            });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
