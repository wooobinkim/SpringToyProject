package com.compnay.stp.member.entity;

import com.compnay.stp.board.entity.Board;
import com.compnay.stp.common.BaseEntity;
import com.compnay.stp.member.Provider;
import com.compnay.stp.member.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;
    private String siteId;
    private String sitePwd;
    //소셜아이디
    private String name;
    @Enumerated(EnumType.STRING)
    private Provider provider;
    private String providerId;
    private String nickName;
    private String age;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Board> boardList = new ArrayList<>();

    public static Member create(String siteId, String sitePwd, String nickName, String age){
        Member member = new Member();
        member.siteId = siteId;
        member.sitePwd = sitePwd;
        member.nickName = nickName;
        member.age = age;
        member.role = Role.ROLE_USER;

        return member;
    }

    public static Member createGoogle(String name, String providerId, Provider provider, String email, String nickName){
        Member member = new Member();
        member.name = name;
        member.provider = provider;
        member.nickName = nickName;
        member.providerId = providerId;
        member.email = email;

        return member;
    }

    public void updateGoogle(String name, String providerId, Provider provider, String email, String nickName){
        this.name = name;
        this.providerId=providerId;
        this.provider=provider;
        this.email=email;
        this.nickName=nickName;
    }
}
