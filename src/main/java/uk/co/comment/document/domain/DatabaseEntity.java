package uk.co.comment.document.domain;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class DatabaseEntity {

    @Id
    private Long id;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !this.getClass().equals(obj.getClass())) {
            return false;
        }
        
        return this == obj || Objects.equals(id, ((DatabaseEntity) obj).getId());
    }
    
}
