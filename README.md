# Threads

On this project I will explore threads in Java, they way they work and interact with each other
Every option in the table of contents will have a corresponding folder in the /src folder with exercises and examples

##### Table of Contents  
[1. Fundamentals](#fundamentals)  

<a name="fundamentals"/>

## 1. Fundamentals

### Why use threads?

Threads allows to execute multiple blocks of code in an asynchronous way, this makes it so that we can use our resources in a more efficiently, allowing us to meaximize performance. This doesn't come without downside, it's important to make sure if shared resources need to be thread safe which comes with a deficit in performance. A perfect example is [StringBuilder](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html) vs [StringBuffer](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuffer.html), where the former is thread safe but often replaced by the [StringBuilder](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html) with one of the reasons being the synchronized methods in the [StringBuffer](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuffer.html) have a significantly greater cost in term of effiency.

