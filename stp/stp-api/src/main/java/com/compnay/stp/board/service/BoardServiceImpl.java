package com.compnay.stp.board.service;

import com.compnay.stp.board.entity.Board;
import com.compnay.stp.board.repository.BoardRepository;
import com.compnay.stp.board.request.BoardCreateRequest;
import com.compnay.stp.board.request.BoardUpdateRequest;
import com.compnay.stp.member.entity.Member;
import com.compnay.stp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public void createBoard(BoardCreateRequest boardCreateRequest) {
        Member member = memberRepository.findById(1L).orElseThrow();

        Board board = Board.create(boardCreateRequest.getSubject(), boardCreateRequest.getContents(), member);
        boardRepository.save(board);
    }

    @Override
    @Transactional
    public void updateBoard(Long id, BoardUpdateRequest boardUpdateRequest) {
        Board board = boardRepository.findById(id).orElseThrow();
        board.update(boardUpdateRequest.getSubject(), boardUpdateRequest.getContents());
    }
}
