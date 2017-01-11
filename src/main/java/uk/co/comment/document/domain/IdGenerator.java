package uk.co.comment.document.domain;

import java.util.HashMap;
import java.util.Map;

public class IdGenerator {
    
    private static IdGenerator instance = null;
    
    private static final Map<Class<? extends DatabaseEntity>, Long> increments = new HashMap();
    
    private IdGenerator() {
    }
    
    public static IdGenerator instantiate() {
        if (instance == null) {
            synchronized (IdGenerator.class) {
                if (instance == null) {
                    instance = new IdGenerator();
                }
            }
        }
        
        return instance;
    }
    
    public synchronized Long get(Class<? extends DatabaseEntity> entityClass) {
        return increment(entityClass);
    }
    
    protected synchronized Long get(Class<? extends DatabaseEntity> entityClass, Long delay) throws InterruptedException {
        Long increment = increment(entityClass);
        Thread.sleep(delay);
        return increment;
    }
    
    private Long increment(Class<? extends DatabaseEntity> entityClass) {
        Long increment = increments.get(entityClass);
        if (increment == null) {
            increment = 0L;
        }
        
        increment++;
        increments.put(entityClass, increment);
        return increment;
    }
    
}
