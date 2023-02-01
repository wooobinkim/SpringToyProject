package com.compnay.stp.member.repository;

import com.compnay.stp.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Member findBySiteId(String siteId);
    public Optional<Member> findByName(String memberName);
}
