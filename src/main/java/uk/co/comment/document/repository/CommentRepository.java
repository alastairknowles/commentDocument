package uk.co.comment.document.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;
import uk.co.comment.document.domain.Comment;

import java.math.BigInteger;

@Transactional
public interface CommentRepository extends MongoRepository<Comment, BigInteger> {
    
}

