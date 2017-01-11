package uk.co.comment.document.domain;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class IdGenerator {
    
    private static IdGenerator instance = null;
    
    private static final Map<Class<? extends DatabaseEntity>, BigInteger> increments = new HashMap();
    
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
    
    public synchronized BigInteger get(Class<? extends DatabaseEntity> entityClass) {
        return increment(entityClass);
    }
    
    public synchronized BigInteger get(Class<? extends DatabaseEntity> entityClass, Long delay) throws InterruptedException {
        BigInteger increment = increment(entityClass);
        Thread.sleep(delay);
        return increment;
    }
    
    public synchronized void reset(Class<? extends DatabaseEntity> entityClass) {
        increments.remove(entityClass);
    }
    
    private BigInteger increment(Class<? extends DatabaseEntity> entityClass) {
        BigInteger increment = increments.get(entityClass);
        if (increment == null) {
            increment = BigInteger.valueOf(0L);
        }
        
        increment = increment.add(BigInteger.valueOf(1L));
        increments.put(entityClass, increment);
        return increment;
    }
    
}
