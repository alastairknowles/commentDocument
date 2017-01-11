package uk.co.comment.document.service;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.co.comment.document.dao.CommentDAO;
import uk.co.comment.document.domain.Comment;
import uk.co.comment.document.domain.CommentLike;
import uk.co.comment.document.domain.IdGenerator;
import uk.co.comment.document.repository.CommentRepository;
import uk.co.comment.document.rest.CommentDTO;
import uk.co.comment.document.rest.CommentsDTO;
import uk.co.comment.document.rest.EntityDTO;

import java.math.BigInteger;

@Service
@Transactional
public class CommentService {
    
    private CommentDAO commentDAO;
    
    private CommentRepository commentRepository;
    
    public CommentService(CommentRepository commentRepository, CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
        this.commentRepository = commentRepository;
    }
    
    public Comment getComment(BigInteger id) {
        return commentRepository.findOne(id);
    }
    
    public EntityDTO createComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(IdGenerator.instantiate().get(Comment.class));
        comment.setComment(commentDTO.getComment());
        comment.setName(commentDTO.getName());
        comment.setPosted(DateTime.now());
        comment = commentRepository.save(comment);
        return new EntityDTO().id(comment.getId());
    }
    
    public CommentsDTO getComments() {
        return new CommentsDTO(commentDAO.findAllWithLikeCountsOrderByIdDesc());
    }
    
    public EntityDTO likeComment(BigInteger id) {
        Comment comment = getComment(id);
        CommentLike like = new CommentLike();
        like.setId(IdGenerator.instantiate().get(CommentLike.class));
        comment.getLikes().add(like);
        commentRepository.save(comment);
        return new EntityDTO().id(like.getId());
    }
    
}
