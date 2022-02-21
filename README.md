# Interview

# Data Engineer Questions

## Question 4:
### We have an array of n elements A[1..n]. This array contains n different numbers from 0 to n. Given that there are totally n + 1 numbers from 0 to n, there is a missing number. (15 points)

- Input: array: [0, 1, 2, 4] -> Output: 3
- Input: array: [1, 4, 2, 3] -> Output: 0

I think the best solution for this question is to use math.
We have the mathematical formula to sum all of the elements of an array.
Total = N * (N + 1) / 2  with n is length of an array and Sum = 1 + 2 + missing number + ... + N ( not care about 0 in array). With N existing => missing number =  Total - Sum
* This solution only uses one loop. So the complexity of this solution is O(n)

## Question 5:
### Given two sorted arrays nums1 and nums2 of size m and n respectively,return the median of the two sorted arrays. (20 points)

#### - Input: nums1 = [1, 3, 6]; nums2 = [1, 2, 10] -> Output: Merged array: [1, 1, 2, 3, 6, 10]. Median is (2 + 3)/2 = 2,5.
#### - Input: nums1 = [1, 3]; nums2 = [1, 2, 10] -> Output:  Merged array: [1, 1, 2, 6, 10]. Median is 2. 


#### There are a lot of good solutions to solve this question. But for me, the first idea is to use one loop with many conditions.
#### This solution is divided into 2 phases:
##### Phases 1: merge 2 sorted arrays
** Create an array for the final  result :  merge_array[] **
	- initlize index for A array : index_A = 0 for A array 
	- initlize index for B array: index_B = 0  for B array
General case:
	I walk through all of the array elements with a  condition:
    + index_A have to be less than the length of A array and 
		+ index_B have to be less than the length of B array
			after through above condition, we will pick smaller of 				current elements in A array  and B array, copy this smaller element to next position in merge_array
Special case:
	After looping all of the array elements, there are several remaining elements in array_1 or array_2, copy them also in merge_array[]
##### Phases 2: return the median of the two sorted arrays
we have to find a midpoint for the two sorted arrays
if the length of the two sorted arrays is an odd number, the final result is easy to find. 
		 the final result = merge_array[midpoint]
On the contrary, the final result is slightly more difficult than usual.
		the final result = (merge_array[midpoint] + merge_array[(midpont - 1)]) / 2
This solution only uses one loop. So the complexity of this solution is O(n)
