package com.compnay.stp.board.service;

import com.compnay.stp.board.request.BoardCreateRequest;
import com.compnay.stp.board.request.BoardUpdateRequest;
import com.compnay.stp.member.entity.Member;

public interface BoardService {
    void createBoard(BoardCreateRequest boardCreateRequest, Long memberId);
    void updateBoard(Long id, BoardUpdateRequest boardUpdateRequest);
}
