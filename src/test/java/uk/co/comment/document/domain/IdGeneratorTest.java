package uk.co.comment.document.domain;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class IdGeneratorTest {
    
    @Test
    public void shouldInstantiateOnlyOneIdGenerator() {
        IdGenerator one = IdGenerator.instantiate();
        IdGenerator two = IdGenerator.instantiate();
        Assert.assertEquals(one, two);
    }
    
    @Test
    public void shouldGenerateSequentialIdentifiers() throws InterruptedException {
        Set<Thread> threads = new HashSet();
        Set<BigInteger> identifiers = new TreeSet();
        
        IdGenerator generator = IdGenerator.instantiate();
        generator.reset(CommentLike.class);
        
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                synchronized (this) {
                    try {
                        identifiers.add(generator.get(CommentLike.class, 10L));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            
            threads.add(thread);
            thread.start();
        }
        
        for (Thread thread : threads) {
            thread.join();
        }
        
        Assert.assertEquals(100, identifiers.size());
        
        long increment = 1L;
        for (BigInteger identifier : identifiers) {
            Assert.assertEquals(BigInteger.valueOf(increment), identifier);
            increment++;
        }
    }
    
}
