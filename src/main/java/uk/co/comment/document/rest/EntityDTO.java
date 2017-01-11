package uk.co.comment.document.rest;

import java.math.BigInteger;

public class EntityDTO {
    
    private BigInteger id;
    
    public BigInteger getId() {
        return id;
    }
    
    public void setId(BigInteger id) {
        this.id = id;
    }
    
    public EntityDTO id(BigInteger id) {
        this.id = id;
        return this;
    }
    
}
