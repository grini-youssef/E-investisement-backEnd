package com.grini.investisement.mapper;

import org.springframework.stereotype.Component;

import com.grini.investisement.dto.CommentsDto;
import com.grini.investisement.entity.Comment;
import com.grini.investisement.entity.Idee;
import com.grini.investisement.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Component
public class CommentMapper {

	public Comment map(CommentsDto commentsDto, Idee idee, User user) {

		Comment comment = new Comment();

		comment.setText(commentsDto.getText());
		comment.setCreatedDate(java.time.Instant.now());
		comment.setIdee(idee);
		comment.setUser(user);

		return comment;
	}
	
	public CommentsDto mapToDto(Comment comment) {
		
		CommentsDto commentDto = new CommentsDto();
		commentDto.setId(comment.getId());
		commentDto.setText(comment.getText());
		commentDto.setCreatedDate(comment.getCreatedDate());
		commentDto.setIdeeId(comment.getIdee().getIdeeId());
		commentDto.setUserName(comment.getUser().getUsername());
		
		return commentDto;
	}

}








