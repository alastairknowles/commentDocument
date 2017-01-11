package uk.co.comment.document.service;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.co.comment.document.domain.Comment;
import uk.co.comment.document.domain.CommentLike;
import uk.co.comment.document.domain.IdGenerator;
import uk.co.comment.document.repository.CommentRepository;
import uk.co.comment.document.rest.CommentDTO;
import uk.co.comment.document.rest.CommentsDTO;

@Service
@Transactional
public class CommentService {
    
    private CommentRepository commentRepository;
    
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    
    public Comment getComment(Long id) {
        return commentRepository.findOne(id);
    }
    
    public Long createComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setName(commentDTO.getName());
        comment.setPosted(DateTime.now());
        comment = commentRepository.save(comment);
        return comment.getId();
    }
    
    public CommentsDTO getComments() {
        return null;
    }
    
    public synchronized Long likeComment(Long id) {
        Comment comment = getComment(id);
        CommentLike like = new CommentLike();
        like.setId(IdGenerator.instantiate().get(CommentLike.class));
        comment.getLikes().add(like.getId());
        commentRepository.save(comment);
        return like.getId();
    }
    
}
