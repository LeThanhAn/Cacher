# AN's CACHING LIBRARY
### WHY CACHE?
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

### How to use
With the cacherProject-0.0.1-SNAPSHOT.jar file sent separately, run the following command

`
java -jar cacherProject-0.0.1-SNAPSHOT.jar
`

The program will run in a command-line interface. These are the following instructions you can do


- set key value
- get key

____________________________________________
### Implementation Strategy
This Cache can currently hold a list of 10 items

This cache project was implemented with an FRU Cache Policy. This was done using a combination of a Hashtable and a Doubly Linked List. The Doubly Linked List is implemented using a Node class in `an.cacherProject.model` package.

On a high level, the Hashtable contains the key of the value that the user try to set, and a Node object of the Node containing that value in the Doubly Linked List.

Whenever the user evokes a `get key` instruction, the cache will get the Node holding the value using the Hashtable. It will then return the user with the value stored in the Node, as well as move the Node on top of the Doubly Linked List.

This strategy allows the cache to keep track of the most frequently visited Node, as any interaction with the Node will bring it to the top of the Doubly Linked List.

When the user evokes a `set key value` instruction, the cache will check if the key exists. 

If it exists, the cache updates the value of the key and puts the corresponding Node to the start of the Doubly Linked List

If the key does not exist, it will check whether the cache limit had been exceeded. 

If it did not exceed, the cache will add a Node containing the value at the top of the Doubly Linked List as well as adding a key, Node pair in the Hashtable.

If the cache limit exceed, the cache will then remove the node at the end of the Doubly Linked List, and add the new Node to the start of the Doubly Linked List as well as a key, Node reference in the Hashtable

### Why Hashtable and Doubly Linked List?
For our cache to work most efficiently, we need
1. A way to keep track of which item had been accessed most recently
2. O(1) insertion
3. O(1) deletion
4. O(1) retrieval

With the condition of O(1) retrieval, HashMap and Hashtable come to mind. However, using them alone will not help us keep track of how recent the item was accessed.

Thus, in order to keep track with the item's recency, LinkedList come to mind. However, Singly Linked List has a deletion of O(N), as it does not keep the information of the node before it.

Thus, I chose to implement the strategy with a Doubly Linked List.

