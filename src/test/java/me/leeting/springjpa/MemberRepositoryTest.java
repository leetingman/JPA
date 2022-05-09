package me.leeting.springjpa;

import me.leeting.springjpa.commons.TestDescription;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MockMvc mockMvc;


    @Test
    @Transactional // after test , rollback
    @TestDescription("H2 Test Member")
    public void testMember() throws Exception{
        //Given
        Member member= new Member();
        member.setUsername("memberA");
        //When
        Long saveId = memberRepository.save(member);
        Member findMember=memberRepository.find(saveId);
        //Then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }

//    @Test
//    @Transactional
//    @TestDescription("test member api")
//    public void testMemberApi() throws Exception{
//        String username = "memberB";
//        Member member=Member.builder()
//                .username(username)
//                .build();
//        //UserService -- 1. create account
//
//
//    }


}