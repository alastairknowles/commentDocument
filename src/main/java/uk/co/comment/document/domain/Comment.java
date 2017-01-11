package uk.co.comment.document.domain;

import org.assertj.core.util.Sets;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "comments")
public class Comment extends DatabaseEntity {
    
    private String comment;
    
    private String name;
    
    private DateTime posted;
    
    private Set<Long> likes = Sets.newHashSet();
    
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
    
    public Set<Long> getLikes() {
        return likes;
    }
    
    public void setLikes(Set<Long> likes) {
        this.likes = likes;
    }
    
}
