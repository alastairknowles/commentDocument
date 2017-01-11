package uk.co.comment.document.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;
import uk.co.comment.document.domain.Comment;
import uk.co.comment.document.rest.CommentDTO;

import java.util.List;

@Repository
public class CommentDAO {
    
    private MongoTemplate mongoTemplate;
    
    public CommentDAO(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    public List<CommentDTO> findAllWithLikeCountsOrderByIdDesc() {
        return mongoTemplate.aggregate(Aggregation.newAggregation(
                Aggregation.project("id", "comment", "name", "posted").and("likes").project("size").as("likes"),
                Aggregation.sort(Sort.Direction.DESC, "id")), Comment.class, CommentDTO.class)
                .getMappedResults();
    }
    
}
