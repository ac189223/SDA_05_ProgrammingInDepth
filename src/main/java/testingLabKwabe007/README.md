
### Github Task: Filter, Search & Sort
In this assignment, you will be given the task to implement methods, in a test driven manner,
based on object comparison. You will be dealing with filtering objects and the use
of typical search/sorting algorithms. This will be put into practice by using 
arrays in Java.

#### Requirements
- You should use the same method names as specified below.
- Methods should be appropriately documented and tested (see the testing section)
- You should strive to implement the tests first before you implement the code for the methods. Of course 
this will usually mean that the code will not compile unless you have created the method. A workaround for
this is to simply create the method and just leave it empty (alternatively return a dummy value if it requires
a return). See the method `removeRectanglesLargerThan` in 
[`src/RectangleArrayProcessor.java`](../../../../testing-lab-sort-search/src/RectangleArrayProcessor.java).

**Important:** have a look at the [Testing](#testing) section
before you start coding!

#### Exercises I: Filter
A `Rectangle` class has been provided for you in [`src/Rectangle.java`](../../../../testing-lab-sort-search/src/Rectangle.java). Make
yourself familiar with the source code and note that the `compareTo` method has been overriden so that instances of
 `Rectangle` can be compared by their `area`. Information on Object Ordering and
Comparable can be found
[here](https://docs.oracle.com/javase/tutorial/collections/interfaces/order.html).
It's also important to note that we've overridden the `equals` method. According to the method, do we consider 
a `Rectangle` to be the same as another one based on object identity or field values? Hopefully, it 
shouldn't be hard to guess. 


We of course need a way to process `Rectangle`s so therefore you've been given a Java class 
[`src/RectangleArrayProcessor.java`](../../../../testing-lab-sort-search/src/RectangleArrayProcessor.java).
 This class will contain your methods.

More specifically, your task is to implement the following methods:
- A method `removeRectanglesLargerThan` that takes a `Rectangle` array and an `int` n and returns an array
containing only the rectangles with a smaller area than n.
- Another method `removeRectanglesLargerThan` (yes, now we're overloading :) ) that takes a `Rectangle` array 
and a `Rectangle` r and returns an array containing only the rectangles with a smaller area than r's area.
- A method `containsSquare` that takes a `Rectangle` array and returns `true` if the array contains a square
or false otherwise.
 - Another method `filterRectanglesWithEqualArea` that takes a `Rectangle` array 
 and a `Rectangle` r and returns an array containing only the rectangles with an area equal to r's area.

You have been provided with a test class for `Rectangle` at 
[`test/RectangleTest.java`](../../../../testing-lab-sort-search/test/RectangleTest.java) which should pass. The test class
you will be working on is 
[`test/RectangleArrayProcessorTest.java`](../../../../testing-lab-sort-search/test/RectangleArrayProcessorTest.java).
The given tests will not compile unless you have added
the [Junit5](https://junit.org/junit5/) library and the 
[Hamcrest](http://hamcrest.org/JavaHamcrest/) library so make sure you get them either
directly via IntelliJ or by manually downloading the jar files and linking them to
the project. 

#### Exercise II (Extra): Sort

**Note:** the following parts deal with algorithms which is actually part of next week's
module. While they're not obligatory, it would probably not be a bad idea to start getting
acquainted with the material beforehand.

##### Literature
- [Algoritmer(SWEDISH)](http://www.nada.kth.se/~snilsson/algoritmer/algoritmer)
- [What is an algorithm?](http://www.cs.utexas.edu/~mitra/csSpring2014/cs303/lectures/algo.html)
- [Hur snabb är datorn?(SWEDISH)](http://www.nada.kth.se/~snilsson/algoritmer/tid)
- [Complexity and Big-O Notation](http://pages.cs.wisc.edu/~vernon/cs367/notes/3.COMPLEXITY.html)

##### Task
* Choose either the Selection sort or Insertion sort algorithm, as shown below
* In `RectangleArrayProcessor` class, the header for the sorting method
  should be the following:
    * `public void sort(Rectangle[] array)`
* Expected behaviour: list of Rectangle objects will be sorted by increasing area
* You _must_ use `Rectangle.compareTo(Rectangle)` for _all_ comparison operations.
* You should make tests for the functionality before you implement the method.

> **Assistant's note:** Notice how the return type is `void`? This gives away
> the fact that these methods sort _in place_. That is to say, they are
> supposed to _mutate_ the argument array/list, instead of returning a copy.


> **Assistant's 2nd note:** The motivation for using `Comparable<Rectangle>`
> interface earlier in rectangle is simple but perhaps not obvious. The benefit of 
> using it is
> that we can implement generic comparison based algorithms (such as search and
> sort) that don't actually know what they are comparing by. For the sake of
> keeping complexity down, you will only be implementing algorithms
> specifically for the `Rectangle` class here, but with a small change to the method
> signatures, one could make the algorithms work for _any_ class `T` that
> implements `Comparable<T>` (ask your assistant if you are curious). An
> implication, and huge benefit, of the search/sort not having anything to do
> with the comparison is that if we decide that we want to compare by for
> example height alone, we only need to change `compareTo`!

```python
Insertion Sort (A)
------------------
1   for i = 1 to A.length - 1
2       j = i
3       while j > 0 and A[j-1] > A[j]
4           temp = A[j]
5           A[j] = A[j-1]
6           A[j-1] = temp
7           j = j - 1
```
To help you understand how it works, here is a (horribly slow, blame
[Wikipedia](https://en.wikipedia.org/wiki/Insertion_sort)) visualization of
insertion sort:

![Insertion sort gif](https://upload.wikimedia.org/wikipedia/commons/0/0f/Insertion-sort-example-300px.gif)

```python
Selection Sort (A)
------------------
1   for i = 0 to A.length - 1
2       min = i
3       for j = i + 1 to A.length - 1
4           if A[j] < A[min]
5               # minimum element found
6               min = j
7       temp = A[i]
8       A[i] = A[min]
9       A[min] = temp
```
To help you understand how it works, here is a (pretty neat, thank
[Wikipedia](https://en.wikipedia.org/wiki/Selection_sort)) visualization of
selection sort:

![Selection sort gif](https://upload.wikimedia.org/wikipedia/commons/9/94/Selection-Sort-Animation.gif)

#### Exercise III (Extra): Sequential Search
Now you will create an iterative sequential search algorithm that **searches
for a Rectangle that is comparably equal to a specified Rectangle**. This will be a 'brute
force' approach that simply iterates through all elements.

* In `RectangleProcessor` class, implement the search method with the following header:
    * `public int sequentialSearch(Rectangle[] array, Rectangle rectangle)`
* Expected behaviour: return index of the first `Rectangle` that has the same area
  as `rectangle`, or -1 if no such `Rectangle` is present.
* You _must_ use `Rectangle.compareTo(Rectangle)` for _all_ comparison operations.
* You should make tests for the functionality before you implement the method.

#### Exercise IV (Extra): Binary Search
If you assume that the input array/list is sorted, you can use the binary
search algorithm to improve search performance. Create a recursive (or
iterative) version of the binary search algorithm that **searches for a
Rectangle that is comparably equal to a specified Rectangle**.

* In your `RectangleProcessor` class, implement the method with the following header:
    * `public int binarySearch(Rectangle[] array, Rectangle rectangle)`
* You should make tests for the functionality before you implement the method.
* When testing, be sure to always use sorted lists/arrays as input.
* Expected behaviour: return index of _any_ `Rectangle` that has the same area
  as `rectangle`, or -1 if no such `Rectangle` is present.
* You _must_ use `Rectangle.compareTo(Rectangle)` for _all_ comparison operations.

### Testing
For this week's testing, you've been provided with two test classes.
[`test/RectangleTest.java`](../../../../testing-lab-sort-search/test/RectangleTest.java), which you do not have to do any more work on,
is just there to make sure that `Comparable<Rectangle>` is correctly implemented.
Make sure to run it before you make your final push. Comparable is the interface which allows
the user to define how an Object should be compared to another Object of the same type.

The second test class, and the one you should work on, is
[`test/RectangleArrayProcessorTest.java`](../../../../testing-lab-sort-search/test/RectangleArrayProcessorTest.java). You have been given one
 test implementation as an example. You should implement tests for all methods.
 There should be at least one negative and one positive test where applicable.
 
 Questions that you should reflect and discuss with other students:
 - How many tests are enough?
 - What might be important to test?


> **Assistant's note:** It is in your own best interests to take the testing
> seriously, as if you properly implement the test class and then pass all of
> the tests, you can be confident that your code will work as expected.


### More
There are of course more [sorting algorithms](https://www.toptal.com/developers/sorting-algorithms)
and if you have time over, go ahead and implement another one of your choice 
(for example mergesort or quicksort)!
