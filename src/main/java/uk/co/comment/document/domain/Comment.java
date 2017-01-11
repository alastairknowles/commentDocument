package uk.co.comment.document.domain;

import org.assertj.core.util.Sets;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Document(collection = "comments")
public class Comment extends DatabaseEntity {
    
    @NotNull
    private String comment;
    
    @NotNull
    private String name;
    
    @NotNull
    private DateTime posted;
    
    private Set<CommentLike> likes = Sets.newHashSet();
    
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
    
    public Set<CommentLike> getLikes() {
        return likes;
    }
    
    public void setLikes(Set<CommentLike> likes) {
        this.likes = likes;
    }
    
}
