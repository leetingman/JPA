package me.leeting.springjpa.service;

import me.leeting.springjpa.domain.Member;
import me.leeting.springjpa.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    public void sign_up() throws Exception{
        //Given
        Member member = new Member();
        member.setName("ye");
        //When
        Long saveId=memberService.join(member);

        //Then
        em.flush();
        assertEquals(member,memberRepository.findOne(saveId));//result = true
        // rollback
    }

    @Test
    public void signUpWithException() throws Exception{
        //Given
        Member memberOne= new Member();
        memberOne.setName("ye");

        Member memberTwo =new Member();
        memberTwo.setName("ye");
        //When
        memberService.join(memberOne);
        //Then
        // Junit 5 @Test(expected =IllegalStateException.class) -> this
        IllegalStateException thrown = assertThrows(IllegalStateException.class,()->memberService.join(memberTwo));
    }
}