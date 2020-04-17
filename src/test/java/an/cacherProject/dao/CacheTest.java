package an.cacherProject.dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CacheTest {

    @Test
    void getValue() {
        Cache cache = new Cache();

        // Test for basic test case
        cache.putValue("doja", "cat");
        String result = cache.getValue("doja");
        assertEquals(result,"cat");
    }

    @Test
    void getValueKeyNotFound() {
        // Test for key not found
        Cache cache = new Cache();

        String result = cache.getValue("hola");
        assertEquals(result,null);
    }

    @Test
    void getValueFlushedKey() {
        // Test for key being flushed out of cache
        Cache cache = new Cache();
        for(int i = 0; i < cache.getCAPACITY()+1; i++) {
            cache.putValue('d'+i+"oja", 'c'+i+"at");
        }
        String result = cache.getValue("doja");
        assertEquals(result,null);
    }

    @Test
    void putValue() {
        Cache cache = new Cache();

        // Test for basic test case
        cache.putValue("doja", "cat");
        String result = cache.getValue("doja");
        assertEquals(result,"cat");
    }

    @Test
    void putValueDuplicateKey() {
        Cache cache = new Cache();

        // Test for duplicate key test case
        cache.putValue("doja", "cat");
        cache.putValue("doja", "meow");
        String result = cache.getValue("doja");
        assertEquals(result,"meow");
    }
}