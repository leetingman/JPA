package me.leeting.springjpa.repository;

import lombok.RequiredArgsConstructor;
import me.leeting.springjpa.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;//injection

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    public List<Member> findAll(){
        String jpql = "select m from Member m";
        List<Member> result = em.createQuery(jpql, Member.class)//Jpql find all query (query,class)
                .getResultList();
        return result;
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name)
                .getResultList();

    }



}
