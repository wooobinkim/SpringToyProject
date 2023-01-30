package com.compnay.stp.board.service;

import com.compnay.stp.board.request.BoardCreateRequest;
import com.compnay.stp.board.request.BoardUpdateRequest;

public interface BoardService {
    void createBoard(BoardCreateRequest boardCreateRequest);
    void updateBoard(Long id, BoardUpdateRequest boardUpdateRequest);
}
