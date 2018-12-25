UTD CS5V81-001 - Short Project 9

Project By : Achyut Bhandiwad (aab180004) and Mythri Thippareddy (mxt172530)

Submitted on: 11/4/2018

This Project was implemented as part of the course CS5V81-001 Implementation of Data Structures and Algorithms at University of Texas at Dallas.

The goal of the project was to create the functionality of insertion Sort and MergeSort.

File Name: SP9.java
Package name: mxt172530

The following cases are implemented: 
1. Insertion Sort 
2. MergeSort take 1
3. MergeSort take 2 
4: MergeSort take 3

To compile and run, use the following commands:
javac ./mxt172530/SP9.java 
java ./mxt172530/SP9 <Size of the array> <Choice>

Results:
Insertion sort worked well till n = 100000. 
It starts taking more than 2 minutes for n = 1M

Trial number has been included to help in verification.
Time is in msec.


	InsertionSort	MergeSort1		MergeSort2					MergeSort3
				
N = 8M	infinity	Time: 1511		Theshold: 20;  Time:1042	Theshold: 20;  Time:1016
				
				
N = 16M	infinity	Time: 3051		Theshold: 20;  Time:2131	Theshold: 20;  Time:2110
				
				
N = 32M	infinity	Time: 6312		Theshold: 20;  Time:4743	Theshold: 20;  Time:4796
				
				
N = 64M	infinity	Time:13222		Theshold: 20;  Time: 9446	Theshold: 20;  Time: 9026
				
				
N = 128M infinity	Time:27052		Theshold: 20;  Time: 20226	Theshold: 20;  Time: 19559
					


