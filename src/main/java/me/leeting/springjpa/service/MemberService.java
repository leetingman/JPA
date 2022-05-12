package me.leeting.springjpa.service;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.leeting.springjpa.domain.Member;
import me.leeting.springjpa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//JPA 안에 데이터변경 은 모두 트랜잭션 안에 있어야

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

     private final MemberRepository memberRepository;

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }//setter injection

    //sign up
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers=memberRepository.findByName(member.getName());
        if(findMembers.size()>0){
            throw new IllegalStateException("This user name already exist please try again lol");
        }
    }


    //find all member
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //find member by userId
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }


}
