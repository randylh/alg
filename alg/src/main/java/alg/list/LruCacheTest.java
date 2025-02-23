package alg.list;

public class LruCacheTest {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(4, 4);
        lruCache.put(5, 5);
        lruCache.put(6, 6);

//        int res = lruCache.get(1);
//        System.out.println(res);

        int res1 = lruCache.get(6);
        System.out.println(res1);
    }


}
