package uk.co.comment.document.domain;

import java.math.BigInteger;
import java.util.Objects;

public class DatabaseEntity {
    
    private BigInteger id;
    
    public BigInteger getId() {
        return id;
    }
    
    public void setId(BigInteger id) {
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
