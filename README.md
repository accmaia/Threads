# Threads

On this project I will explore threads in Java, they way they work and interact with each other

Every option in the table of contents will have a corresponding folder in the /src folder with exercises and examples

## Table of Contents  
[1. Fundamentals](#fundamentals)  

<a name="fundamentals"/>

## 1. Fundamentals

### 1.1. Breef introduction to threads, thread-safety and a thread's lifecycle

Threads allows the execution of multiple blocks of code in an asynchronous way, this makes it so that we can use our resources more efficiently, allowing us to meaximize performance. This doesn't come without downside, it's important to make sure if a shared resource needs to be thread safe which likely comes with a loss in performance. A perfect example is [StringBuilder](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html) vs [StringBuffer](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuffer.html), where the former is thread safe but often replaced by the [StringBuilder](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html) with one of the reasons being the synchronized methods in the [StringBuffer](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuffer.html) have a significantly greater cost in terms of effiency.

In the [example 1](https://github.com/accmaia/Threads/tree/master/src/fundamentals/examples/one), I will simulate a very slow append, where [Resource](https://github.com/accmaia/Threads/blob/master/src/fundamentals/examples/one/Resource.java) acts like a StringBuilder and [SynchronizedResource](https://github.com/accmaia/Threads/blob/master/src/fundamentals/examples/one/SynchronizedResource.java) like StringBuffer. The two classes both have a method .execute() which appends a String to a static variable, besides the append they both wait half a second to simulate a more demanding process in terms of processing time.  
The static variable should look: "1;2;3;4;5;...;n+1;" when LOOP_SIZE = 100

**Output**

Total time:
>Total time without synchronization: 6 seconds  
Total time with synchronization: 50 seconds

Average time:
>Synchronization is 7x slower than without synchronization  

Reliability:
>It's reliable without synchronization: true  
It's reliable with synchronization: false

It's possible to observe a few things with this example, we are using synchronization to make the append method more reliable by sacrificing perfomance. This happens because when multiple threads are trying to access the same method, some will have to wait until the first to access it frees the "lock", this waiting time doesn't exist when the method is not synchronized, therefore it generally takes less time to compute.

Before understanding how to implement threads in Java, it's importante to understand the states a thread may be in, in other words it's lifecycle.

#### Here is a visual representation of a thread's lifecycle:


![This is an image](https://www.baeldung.com/wp-content/uploads/2018/02/Life_cycle_of_a_Thread_in_Java.jpg)
<sub>[source]</sub>
