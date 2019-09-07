# The Summary of the Effective Java (3rd edition)
Effective Java Summary, 3rd Edition

##### Table of Contents  
[Item 67 – Optimize judiciously](#headers)  
[Emphasis](#emphasis)  
...snip...    
<a name="headers"/>
## Item 67 – Optimize judiciously
Strive to write good programs - not fast ones. Speed will follow 

Do think about performance while you’re designing systems, especially while you’re designing APIs, wire-level protocols, and persistent data formats. When you’ve finished building the system, measure its performance. If it’s fast enough, you’re done. If not, locate the source of the problem with the aid of a profiler and go to work optimizing the relevant parts of the system. 

The first step is to examine your choice of algorithms: no amount of low-level optimization can make up for a poor choice of algorithm. Repeat this process as necessary, measuring the performance after every change, until you’re satisfied. 

Strive to avoid design decisions that limit performance 
Changing a fundamental facet of your design after the fact can result in an ill-structured system that is difficult to maintain and evolve. Therefore, you must think about performance during the design process.

Consider the performance consequences of your API design decisions. In general, a good API design is consistent with good performance 

Some of the examples: Making a public type mutable may require a lot of needless defensive copying (Item 50). Using composition over inheritance (Item 18), using an interface rather than implementation type (Item 64).

97% of the time: premature optimization is the root of all evil. 
Often, optimizations have no measurable effect on performance; sometimes, they make it worse. The main reason is that it’s difficult to guess where your program is spending its time. The part of the program that you think is slow may not be at fault, in which case you’d be wasting your time trying to optimize it.


<a name="emphasis"/>
## Emphasis
