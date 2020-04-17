# AN's CACHING LIBRARY
#### WHY CACHE?
We need cache because calls to DB can be computationally expensive. In addition, cache helps with user experience, as commonly requested information can be loaded quickly.

Distributed Cache vs Global Cache
Where should we put our cache? If we put our cache in the server/browser, cache retrieval is much faster. However, there could be information disparity. For instance, 2 users who retrieves their profiles often will both have different caches in their respective browser, while both front-end would evoke a cache with key “profile”. In this instance, while both cache implementations are the same, the data is different. This is an example of a Distributed Cache usage.

A global cache is 1 cache that is used for the entire application. This is good for fault tolerance. If an instance of a server is down, the cache would still be able to be retrieved as the cache does not lie in any server instance. However, this cache should not be used to hold information that varies, such as user profile. This is because whenever a user accesses his/her profile, and the value inside the cache is not the expected value, the cache would be updated with a DB call. Thus, there is no actual usage for the cache (dead cache). Lastly, Global Cache calls are also slower to be seen by the actual user, as it lies the further from the user (after the server and before the DB)

So, which one to use? The answer is that we must utilize both Global Cache and Distributed Cache. For information that changes from user to user, we could use Distributed Cache living within the server or a browser. This allows for faster calls, and in return better user experience. However, for information that are more critical, needs to be fault tolerated, we could use a Global Cache.

### Caching Eviction Policy
What happens when the Cache has its limits? When a cache reaches its limit, it will have to delete certain values in the cache. The reasoning behind the deletion is called a Caching Eviction Policy.

A very popular Caching Eviction Policy is the Least Recently Used (LRU) Policy, which is also the Policy IMPLEMENTED IN THIS PROJECT. This policy dictates that the least recently used item will be evicted from the cache. However, this policy DOES NOT take into account other factors such as the frequency of the cache being used.

There are other popular Cache Eviction Policies, such as LIFO, FIFO, and most notably Most Recently Used. Each policy has its own assumption, and a successful Caching library would be able to make use of a combination of these policies.

For the programming assignment, LRU was implemented as it is the most popular policy being used in many caching libraries. Given more time, I would use a combination of different policies to suit different needs at different times.

### Fault Tolerance Strategy
Currently in the code, there is no fault tolerance being applied. However, given more time, I would do frequent snapshots of the cache, in the event that a server utilizing the cache goes down in order to prevent the data from being lost.
____________________________________________

### Setting up

### How to use