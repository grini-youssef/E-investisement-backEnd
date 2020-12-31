package com.grini.investisement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

import com.grini.investisement.dto.CommentsDto;
import com.grini.investisement.service.CommentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentsController {
	
	private final CommentService commentService;
	
	@PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentsDto commentsDto) {
        commentService.createComment(commentsDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
	@GetMapping("/byIdee/{ideeId}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForIdee(@PathVariable Long ideeId) {
        return status(OK).body(commentService.getCommentByIdee(ideeId));
    }
	
	@GetMapping("/byUser/{userName}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsByUser(@PathVariable String userName) {
        return status(OK).body(commentService.getCommentsByUser(userName));
    }


}










