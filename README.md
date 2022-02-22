# Interview

## First of all, I would like to begin by thanking you for giving me an opportunity to answer questions for Data Engineer Intern position. This is a great  opportunity for me to remmember what I know. But with limited ability and time, I attemped to answer as many these questions as possible I could. Below are my result, there are still several remaining questions is not completed.

# Data Engineer Questions


## Question.1: 
### Assume that we have a table storing scores of athletes in a competition: Performance(AthleteId, Gender, Country, Score).Write an SQL to find the second highest score of athletes. (15 points)

The first idea when I'm facing this problem is to create a temporary table to store scores except for the highest score of athletes. After that, We  can easily select  the second highest score of athletes
```sql
SELECT AthleteId, Score
FROM Performance
WHERE Score= (
	SELECT MAX(Score)
    	FROM  Performance
    	WHERE Score NOT IN  (SELECT  MAX(Score) FROM Performance)
)
```
- You can access this link to view the result:  http://sqlfiddle.com/#!9/557aba4/1

##### This solution is convenient, but it's not optimal. Especially if we want to find all athletes with the third or fourth highest scores.

## Question.2 : Assume that we have Customers(id, name) and Orders(id, customerId) tables. Write an SQL query to report all customers who never order anything. (10 points) 

This question is quite simple,  my quickest way is:
```sql
SELECT id
FROM Customer
WHERE id NOT IN (SELECT customerId FROM Orders)
```
 - You can access this link to view the result: http://sqlfiddle.com/#!9/9823d0/3
## Question 3: Assume that we have Employee(id, name, salary, departmentId) and Department(id, name). A company's executives are interested in seeing who earns the most money in each of the company's departments. A high earner in a department is an employee who has a salary in the top three unique salaries for that department. Write an SQL query to find the employees who are high earners in each of the departments. (20 points)

### This question is quite challenging for me. After a lot of searching, it's my solution. Instead of solving the question according to the problem. I solve the lower problem.

```
ID	Name	Salary	DepartmentId
1	Joe	70000	1
2	Henry	80000	2
3	Sam	60000	2
4	Max	90000	1
5	Janet	69000	1
6	Randy	85000	1
```
- We have the above table. For example.I'll find the top three biggest salary of employees.
- Salary = [70000, 80000, 60000, 90000, 69000, 85000]
	- 70000 < [80000, 90000, 85000] => count = 3
 	- 80000 < [90000, 85000] => count = 2
 	- 60000: any element in array are greater than 60000 => count = 5
 	- 90000 < [] => count  = 0
 	- 69000 < [70000, 80000, 90000, 85000] => count = 4
 	- 85000 < [90000] => count = 1
=> the top three biggest salary of employees: 80000 , 85000 , 90000.

##### With the above explanation we have SQL query:
```sql
SELECT e1.Name , e1.Salary
FROM Employee e1
WHERE 3 >
(
    SELECT COUNT(DISTINCT e2.Salary)
    FROM Employee e2
    WHERE e2.Salary > e1.Salary
)
;
```



##### Finally, we need to join the Employee table with Department in order to retrieve the department information.
```sql
SELECT
    d.Name AS 'Department', e1.Name AS 'Employee', e1.Salary
FROM
    Employee e1
        JOIN
    Department d ON e1.DepartmentId = d.Id
WHERE
    3 > (SELECT
            COUNT(DISTINCT e2.Salary)
        FROM
            Employee e2
        WHERE
            e2.Salary > e1.Salary
                AND e1.DepartmentId = e2.DepartmentId
        )
;
```
 - You can access this link to view the result: http://sqlfiddle.com/#!9/c75705
 
## Question 4:
### We have an array of n elements A[1..n]. This array contains n different numbers from 0 to n. Given that there are totally n + 1 numbers from 0 to n, there is a missing number. (15 points)

- Input: array: [0, 1, 2, 4] -> Output: 3
- Input: array: [1, 4, 2, 3] -> Output: 0

I think the best solution for this question is to use math.
We have the mathematical formula to sum all of the elements of an array.
Total = N * (N + 1) / 2  with n is length of an array and Sum = 1 + 2 + missing number + ... + N ( not care about 0 in array). With N existing => missing number =  Total - Sum

```java
int total = (arr.length * (arr.length + 1)) / 2;
int sum = 0;
for (int i = 0; i < arr.length; i++) {
  sum += arr[i];
}
return (total - sum);
 ```
* This solution only uses one loop. So the complexity of this solution is O(n)

## Question 5:
### Given two sorted arrays nums1 and nums2 of size m and n respectively,return the median of the two sorted arrays. (20 points)

#### - Input: nums1 = [1, 3, 6]; nums2 = [1, 2, 10] -> Output: Merged array: [1, 1, 2, 3, 6, 10]. Median is (2 + 3)/2 = 2,5.
#### - Input: nums1 = [1, 3]; nums2 = [1, 2, 10] -> Output:  Merged array: [1, 1, 2, 6, 10]. Median is 2. 


#### There are a lot of good solutions to solve this question. But for me, the first idea is to use one loop with many conditions.
#### This solution is divided into 2 phases:
##### Phases 1. : merge 2 sorted arrays
- Create an array for the final  result :  merge_array[]
	- initlize index for A array : index_A = 0 for A array 
	- initlize index for B array: index_B = 0  for B array
- General case: I walk through all of the array elements with a  condition:
    - index_A have to be less than the length of A array and  index_B have to be less than the length of B array
      - after through above condition, we will pick smaller of current elements in A array  and B array, copy this smaller element to next position in merge_array
- Special case: After looping all of the array elements, there are several remaining elements in array_1 or array_2, copy them also in merge_array[]
```java
       int[] merge_array = new int[a.length + b.length];
       int index_a = 0;
       int index_b = 0;
       for (int i = 0; i < merge_array.length; i++){
           if (index_a < a.length && index_b < b.length){
               if (a[index_a]  < b[index_b]){
                   merge_array[i] = a[index_a];
                   index_a++;
               }
               else{
                   merge_array[i] = b[index_b];
                   index_b++;
               }
           } else if (index_a < a.length){
               merge_array[i] = a[index_a];
               index_a++;
           }    
           else{
                merge_array[i] = b[index_b];
                index_b++;
           }
          
       }

```
##### Phases 2: return the median of the two sorted arrays
- we have to find a midpoint for the two sorted arrays, if the length of the two sorted arrays is an odd number, the final result is easy to find. 
		 the final result = merge_array[midpoint]
- On the contrary, the final result is slightly more difficult than usual.
  - the final result = (merge_array[midpoint] + merge_array[(midpont - 1)]) / 2

```java
       int mid = merge_array.length / 2;
       if (merge_array.length % 2 != 0){
           return merge_array[mid];
       }
       else{
            double result = (double) (merge_array[(int) mid] + merge_array[(int) (mid - 1)]) / 2;
            return result;
       }
  
```
  
  
##### This solution only uses one loop. So the complexity of this solution is O(n)

## Question 6: Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well. (20 points)
####  - Input = [2, 2, 5] -> Ouput: [2, 5]
####  - Input = [1, 1, 3, 4, 4, 5, 6, 6] -> Output: [1, 3, 4, 5, 6]

I don't know there have many solutions for this question, my simplest way is to loop all of the array elements. While traversing, compare each node with its next node. if the next node is equal with the current node, keep going. if the next node is not equal to the current, we link the current node with the next node.

For example
- 1 -> 1 -> 3 -> 4 -> 4 -> 6 -> 6
  - the current node is 1
  - the first different node of 1 is 3.
-> we are going to remove element 1 and connect 1 with 3 rather than connect 1. I use while for this step.

```java
        Node current = head;
        while(current != null) {
            Node temp = current;
            while(temp != null && temp.data == current.data) {
                temp = temp.next;
            }
            current.next = temp;
            current = current.next;
        }
```



