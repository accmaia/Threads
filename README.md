# Threads

On this project I will explore threads in Java, they way they work and interact with each other

Every option in the table of contents will have a corresponding folder in the /src folder with exercises and examples

## Table of Contents  
[1. Fundamentals](#fundamentals)  

<a name="fundamentals"/>

## 1. Fundamentals

### Why use threads?

Threads allows to execute multiple blocks of code in an asynchronous way, this makes it so that we can use our resources more efficiently, allowing us to meaximize performance. This doesn't come without downside, it's important to make sure if shared resources need to be thread safe which comes with a deficit in performance. A perfect example is [StringBuilder](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html) vs [StringBuffer](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuffer.html), where the former is thread safe but often replaced by the [StringBuilder](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html) with one of the reasons being the synchronized methods in the [StringBuffer](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuffer.html) have a significantly greater cost in term of effiency.

In the [example 1](https://github.com/accmaia/Threads/tree/master/src/fundamentals/examples/one), without going in depth on what the code is doing (or if it's good or bad), it is evident that these classes behave differently when being accessed by multiple threads simultaneously.

**Output**

Total time:
>(MULTI THREAD) Builder took:  155661000 milliseconds to append 1000 elements  
(MULTI THREAD) Buffer took:   180642200 milliseconds to append 998 elements  
(SINGLE THREAD) Builder took: 4327000 milliseconds to append 1000 elements  
(SINGLE THREAD) Buffer took:  3594600 milliseconds to append 1000 elements  

Average time:
>(MULTI THREAD) Builder took on average:  155661 milliseconds/append  
(MULTI THREAD) Buffer took on average:   181004 milliseconds/append  
(SINGLE THREAD) Builder took on average: 4327 milliseconds/append  
(SINGLE THREAD) Buffer took on average:  3594 milliseconds/append  

Reliability:
>(MULTI THREAD) Builder has repeated or invalid number stored? true  
(MULTI THREAD) Buffer has repeated or invalid number stored? false  
(SINGLE THREAD) Builder has repeated or invalid number stored? false  
(SINGLE THREAD) Buffer has repeated or invalid number stored? false  

__Note: these values change from each iteration__


Although the example isn't the best to show the true power of multithreading or it's downsides since the average runtime of the syncronized method is very low, we can still retrieve some very imporant conclusions from the example. Although these values can fluctuate slightly between iterations of the example, a few things are constant:

* With a single thread there is little to no difference in the time it takes both classes to execute all appends
* With multiple threads StringBuilder is not reliable at all
* With multiple threads the [StringBuffer](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuffer.html) consistently takes significantly longer to run through all iterations compared to the [StringBuilder](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html)

This happens because in a single thread it doesn't really matter if a method is tread safe or not, because everything runs in a synchronous fashion no synchronized method will ever be locked. When multiple threads are trying to access the same method, some will have to wait until the first to access it frees the lock, this waiting time doesn't exist when the method is not synchronized, therefore it generally takes less time to compute.
Although synchronization comes at a perfomance cost it can fix realiability issues related to multithreading.


### How to use threads in Java

In Java a class is a thread if it implements the interface [Runnable](https://docs.oracle.com/javase/7/docs/api/java/lang/Runnable.html), which enforces the implementation of the method [run()](https://docs.oracle.com/javase/7/docs/api/java/lang/Runnable.html#run()), there is a [Thread](https://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html) that we can also extend that implements this interface and also a bunch of useful methods.

![This is an image](https://www.baeldung.com/wp-content/uploads/2018/02/Life_cycle_of_a_Thread_in_Java.jpg)
<sub>[source]</sub>
