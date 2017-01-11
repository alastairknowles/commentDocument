package uk.co.comment.document.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uk.co.comment.document.rest.CommentDTO;
import uk.co.comment.document.rest.CommentsDTO;
import uk.co.comment.document.service.CommentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class Controller {
    
    private CommentService commentService;
    
    public Controller(CommentService commentService) {
        this.commentService = commentService;
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/comments", method = RequestMethod.POST)
    public Long createComment(@RequestBody @Valid CommentDTO commentDTO) {
        return commentService.createComment(commentDTO);
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/comments", method = RequestMethod.GET)
    public CommentsDTO retrieveComments() {
        return commentService.getComments();
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/comments/{id}/like", method = RequestMethod.POST)
    public Long likeComment(@PathVariable Long id) {
        return commentService.likeComment(id);
    }
    
}
