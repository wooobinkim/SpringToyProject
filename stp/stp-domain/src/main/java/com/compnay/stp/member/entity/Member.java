package com.compnay.stp.member.entity;

import com.compnay.stp.board.entity.Board;
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
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;
    private String siteId;
    private String sitePwd;
    private String name;
    private String age;
    private String role;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Board> boardList = new ArrayList<>();

    public static Member create(String siteId, String sitePwd, String name, String age){
        Member member = new Member();
        member.siteId = siteId;
        member.sitePwd = sitePwd;
        member.name = name;
        member.age = age;
        member.role = "USER";

        return member;
    }
}
