package com.compnay.stp.board.entity;

import com.compnay.stp.member.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    private String subject;
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    public static Board create (String subject, String contents, Member member){
        Board board = new Board();
        board.subject = subject;
        board.contents = contents;
        board.member = member;

        return board;
    }

    public void update (String subject, String contents){
        this.subject = subject;
        this.contents = contents;
    }

}
