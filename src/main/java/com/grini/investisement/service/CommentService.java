package com.grini.investisement.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.grini.investisement.dto.CommentsDto;
import com.grini.investisement.entity.Comment;
import com.grini.investisement.entity.Idee;
import com.grini.investisement.entity.NotificationEmail;
import com.grini.investisement.entity.User;
import com.grini.investisement.exception.IdeeNotFoundException;
import com.grini.investisement.mapper.CommentMapper;
import com.grini.investisement.repository.CommentRepository;
import com.grini.investisement.repository.IdeeRepository;
import com.grini.investisement.repository.UserRepository;

import static java.util.stream.Collectors.toList;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {
	
	//TODO: Construct POST URL
    private static final String POST_URL = "";
	
	private final CommentMapper commentMapper;
    private final IdeeRepository ideeRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;
	
	public void createComment(CommentsDto commentsDto) {
        Idee idee = ideeRepository.findById(commentsDto.getIdeeId())
                .orElseThrow(() -> new IdeeNotFoundException(commentsDto.getIdeeId().toString()));
        Comment comment = commentMapper.map(commentsDto, idee, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(idee.getUser().getUsername() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, idee.getUser());
    }
	
	private void sendCommentNotification(String message, User user) {
		// TO DO : correct username
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

	public List<CommentsDto> getCommentByIdee(Long ideeId) {
        Idee idee = ideeRepository.findById(ideeId)
                .orElseThrow(() -> new IdeeNotFoundException(ideeId.toString()));
        return commentRepository.findByIdee(idee)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }
	
	public List<CommentsDto> getCommentsByUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }

}
