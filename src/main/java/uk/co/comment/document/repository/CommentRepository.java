package uk.co.comment.document.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uk.co.comment.document.domain.Comment;
import uk.co.comment.document.rest.CommentDTO;

import java.util.List;

@Transactional
public interface CommentRepository extends MongoRepository<Comment, Long> {
    
}

