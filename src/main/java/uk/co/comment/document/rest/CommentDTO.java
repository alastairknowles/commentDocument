package uk.co.comment.document.rest;

import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;

public class CommentDTO extends EntityDTO {
    
    @NotNull
    private String comment;
    
    @NotNull
    private String name;
    
    private DateTime posted;
    
    public Long likes;
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public DateTime getPosted() {
        return posted;
    }
    
    public void setPosted(DateTime posted) {
        this.posted = posted;
    }
    
    public Long getLikes() {
        return likes;
    }
    
    public void setLikes(Long likes) {
        this.likes = likes;
    }
    
}
