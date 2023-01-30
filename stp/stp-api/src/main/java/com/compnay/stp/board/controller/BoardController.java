package com.compnay.stp.board.controller;

import com.compnay.stp.board.request.BoardCreateRequest;
import com.compnay.stp.board.request.BoardUpdateRequest;
import com.compnay.stp.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/board")
@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping()
    public ResponseEntity<?> createBoard(@RequestBody BoardCreateRequest boardCreateRequest){
        boardService.createBoard(boardCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{boardid}")
    public ResponseEntity<?> updateBoard(@RequestBody BoardUpdateRequest boardUpdateRequest,
                                         @PathVariable("boardid") Long id){
        boardService.updateBoard(id, boardUpdateRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
