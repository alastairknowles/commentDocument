package uk.co.comment.document.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
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
    public void shouldGenerateSequentialIdentifiers() {
        IdGenerator generator = IdGenerator.instantiate();
        for (long i = 1L; i < 101L; i++) {
            Long id = generator.get(CommentLike.class);
            Assert.assertEquals(i, id.longValue());
        }
    }
    
    @Test
    public void shouldGenerateSequentialIdentifiersAcrossMultipleThreads() throws InterruptedException {
        Set<Thread> threads = new HashSet();
        Set<Long> identifiers = new TreeSet();
        IdGenerator generator = IdGenerator.instantiate();
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
        Iterator<Long> identifiersIterator = identifiers.iterator();
        while (identifiersIterator.hasNext()) {
            Long identifier = identifiersIterator.next();
            Assert.assertEquals(increment, identifier.longValue());
            increment++;
        }
    }
    
}
