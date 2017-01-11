package uk.co.comment.document.dao;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;
import uk.co.comment.document.rest.CommentDTO;
import uk.co.comment.document.rest.CommentsDTO;

import java.util.List;

@Repository
public class CommentDAO {
    
    private MongoTemplate mongoTemplate;
    
    public CommentDAO(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    public List<CommentDTO> findAllWithLikeCountsOrderByIdDesc() {
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.project(""));
        
        return null;
    }
    
}
