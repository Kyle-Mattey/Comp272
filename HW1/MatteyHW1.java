package Hw1;/*
 * *** Kyle Mattey / SECTION 002 ***
 *
 * Homework # 1 (Programming Assignment). This Java class defines some basic
 * manipulation operations on Linked-Lists and Stacks.
 *
 * Additionally, there are two algorithm analysis methods where you need
 * to return a specified number as provided in comments of each method indicating
 * the complexity of the code shown. The testing routine will be looking for a
 * specific number returned.
 */

import java.util.Stack;

public class MatteyHW1 {

    static class LinkedList {
        static class Node {
            int data;
            Node next;

            Node(int d) {
                data = d;
                next = null;
            }
        }

        Node head;

        public void sortedInsert(int data) {
            Node new_node = new Node(data);
            new_node.next = null;

            if (this.head == null || head.data >= new_node.data) {
                new_node.next = head;
                head = new_node;
            } else {
                Node current = this.head;
                while (current.next != null && current.next.data < data) {
                    current = current.next;
                }
                new_node.next = current.next;
                current.next = new_node;
            }
        }

        public void removeElementsLT(int ltValue) {
            Node current = head;
            while (current != null && current.data < ltValue) {
                head = current.next; // Remove head if it is less than ltValue
                current = head;
            }
            while (current != null && current.next != null) {
                if (current.next.data < ltValue) {
                    current.next = current.next.next; // Remove nodes less than ltValue
                } else {
                    current = current.next;
                }
            }
        }

        public void removeElement(int value) {
            Node current = head;
            while (current != null && current.data == value) {
                head = current.next; // Remove head if it matches value
                current = head;
            }
            while (current != null && current.next != null) {
                if (current.next.data == value) {
                    current.next = current.next.next; // Remove nodes with value
                } else {
                    current = current.next;
                }
            }
        }

        public String toString() {
            StringBuilder output = new StringBuilder("[");
            Node currNode = this.head;
            while (currNode != null) {
                output.append(currNode.data).append(" ");
                currNode = currNode.next;
            }
            return output.toString().trim() + "]";
        }
    }

    static class Stacks {

        public static boolean isPalindrome(String input) {
            Stack<Character> stack = new Stack<>();
            input = input.toLowerCase().replaceAll("\\s+", ""); // Remove spaces and lowercase

            for (char ch : input.toCharArray()) {
                stack.push(ch); // Push all characters onto the stack
            }

            for (char ch : input.toCharArray()) {
                if (stack.pop() != ch) {
                    return false; // Check if characters match when popped
                }
            }
            return true;
        }

        public static int findLargestK(Stack<Integer> stack, int k) {
            Stack<Integer> tempStack = new Stack<>();
            int largestIndex = -1;
            int currentIndex = stack.size() - 1; // Start from the top of the stack

            // System.out.println("Initial Stack: " + stack);

            // Traverse through the stack
            while (!stack.isEmpty()) {
                int element = stack.pop();
                tempStack.push(element);

                // System.out.println("Element: " + element + " at Index: " + currentIndex);

                if (element == k && currentIndex > largestIndex) {
                    largestIndex = currentIndex; // Update the largest index when k is found
                    // System.out.println("Found Element " + k + " at Index " + currentIndex);
                }
                currentIndex--; // Decrement index as we go through the stack
            }

            // Restore the original stack
            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }

            // System.out.println("Final Stack after restore: " + stack);
            // System.out.println("Largest Index of Element " + k + " is: " + largestIndex);

            return largestIndex;
        }
    }

    public static int algorithmAnalysis1(int n, int m) {
        /*
         * a = O(n)
         * b = O(m)
         * Total complexity = O(n + m) time and O(1) space
         */
        return 3; // O(N + M) time, O(1) space
    }

    public static int algorithmAnalysis2(int n) {
        /*
         * Outer loop runs O(n)
         * Inner loop runs O(log n)
         * Total complexity = O(n log n)
         */
        return 2; // O(N log N) time
    }
}
