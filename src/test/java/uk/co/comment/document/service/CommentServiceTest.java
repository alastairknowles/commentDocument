package uk.co.comment.document.service;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.comment.document.Application;
import uk.co.comment.document.domain.Comment;
import uk.co.comment.document.rest.CommentDTO;

import javax.validation.ConstraintViolationException;
import java.math.BigInteger;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class CommentServiceTest {
    
    @Autowired
    private CommentService commentService;
    
    @Test
    public void shouldPersistComment() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setComment("My first comment");
        commentDTO.setName("Alastair Knowles");
        
        DateTime baseline = DateTime.now().withMillisOfSecond(0);
        BigInteger commentId = commentService.createComment(commentDTO).getId();
        
        Comment comment = commentService.getComment(commentId);
        Assert.assertEquals(commentDTO.getComment(), comment.getComment());
        Assert.assertEquals(commentDTO.getName(), comment.getName());
        
        DateTime posted = comment.getPosted();
        Assert.assertTrue(posted.equals(baseline) || posted.isAfter(baseline));
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void shouldNotPersistCommentWithMissingComment() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setName("Diane Lillis");
        commentService.createComment(commentDTO);
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void shouldNotPersistCommentWithMissingName() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setComment("My second comment");
        commentService.createComment(commentDTO);
    }
    
    @Test
    public void shouldLikeComment() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setComment("My second comment");
        commentDTO.setName("Alastair Knowles");
        BigInteger id = commentService.createComment(commentDTO).getId();
        
        commentService.likeComment(id);
        Comment comment = commentService.getComment(id);
        Assert.assertEquals(1, comment.getLikes().size());
    }
    
}
