package Hw7;

/******************************************************************
 *
 *   Kyle Mattey / SECTION 002
 *
 *   This java file contains the problem solutions for the methods selectionSort,
 *   mergeSortDivisibleByKFirst, asteroidsDestroyed, and numRescueCanoes methods.
 *
 ********************************************************************/

import java.util.Arrays;

public class ProblemSolutions {

    /**
     * Method SelectionSort
     * <p>
     * This method performs a selection sort. This file will be spot checked,
     * so ENSURE you are performing a Selection Sort!
     * <p>
     * This is an in-place sorting operation that has two function signatures. This
     * allows the second parameter to be optional, and if not provided, defaults to an
     * ascending sort. If the second parameter is provided and is false, a descending
     * sort is performed.
     *
     * @param values    - int[] array to be sorted.
     * @param ascending - if true,method performs an ascending sort, else descending.
     *                  There are two method signatures allowing this parameter
     *                  to not be passed and defaulting to 'true (or ascending sort).
     */

    public void selectionSort(int[] values) {
        selectionSort(values, true);
    }

    public static void selectionSort(int[] values, boolean ascending) {
        int n = values.length;

        for (int i = 0; i < n - 1; i++) {
            // Find the index of the minimum or maximum element based on 'ascending'
            int targetIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (ascending) {
                    if (values[j] < values[targetIndex]) {
                        targetIndex = j;
                    }
                } else {
                    if (values[j] > values[targetIndex]) {
                        targetIndex = j;
                    }
                }
            }

            // Swap the found target with the current element
            int temp = values[targetIndex];
            values[targetIndex] = values[i];
            values[i] = temp;
        }
    }

    /**
     * Method mergeSortDivisibleByKFirst
     * <p>
     * This method will perform a merge sort algorithm. However, all numbers
     * that are divisible by the argument 'k', are returned first in the sorted
     * list. Example:
     * values = { 10, 3, 25, 8, 6 }, k = 5
     * Sorted result should be --> { 10, 25, 3, 6, 8 }
     * <p>
     * values = { 30, 45, 22, 9, 18, 39, 6, 12 }, k = 6
     * Sorted result should be --> { 30, 18, 6, 12, 9, 22, 39, 45 }
     * <p>
     * As shown above, this is a normal merge sort operation, except for the numbers
     * divisible by 'k' are first in the sequence.
     *
     * @param values - input array to sort per definition above
     * @param k      - value k, such that all numbers divisible by this value are first
     */

    public void mergeSortDivisibleByKFirst(int[] values, int k) {
        if (k == 0 || values.length <= 1) {
            return; // Handle edge cases
        }
        mergeSortDivisibleByKFirst(values, k, 0, values.length - 1);
    }

    private void mergeSortDivisibleByKFirst(int[] values, int k, int left, int right) {
        if (left >= right) {
            return; // Base case: single element
        }

        int mid = left + (right - left) / 2;

        // Recursive sort for left and right halves
        mergeSortDivisibleByKFirst(values, k, left, mid);
        mergeSortDivisibleByKFirst(values, k, mid + 1, right);

        // Merge the two halves
        mergeDivisibleByKFirst(values, k, left, mid, right);
    }

    private void mergeDivisibleByKFirst(int[] values, int k, int left, int mid, int right) {
        // Temporary array for merged results
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, t = 0;

        // Divisible by k first logic
        while (i <= mid && j <= right) {
            boolean isLeftDivisible = values[i] % k == 0;
            boolean isRightDivisible = values[j] % k == 0;

            if (isLeftDivisible && !isRightDivisible) {
                temp[t++] = values[i++]; // Left is divisible, add it first
            } else if (!isLeftDivisible && isRightDivisible) {
                temp[t++] = values[j++]; // Right is divisible, add it first
            } else if (isLeftDivisible) {
                if (values[i] <= values[j]) {
                    temp[t++] = values[i++];
                } else {
                    temp[t++] = values[j++];
                }
            } else {
                if (values[i] <= values[j]) {
                    temp[t++] = values[i++];
                } else {
                    temp[t++] = values[j++];
                }
            }
        }


        while (i <= mid) {
            temp[t++] = values[i++];
        }
        while (j <= right) {
            temp[t++] = values[j++];
        }


        System.arraycopy(temp, 0, values, left, temp.length);
    }
    /**
     * Method asteroidsDestroyed
     * <p>
     * You are given an integer 'mass', which represents the original mass of a planet.
     * You are further given an integer array 'asteroids', where asteroids[i] is the mass
     * of the ith asteroid.
     * <p>
     * You can arrange for the planet to collide with the asteroids in any arbitrary order.
     * If the mass of the planet is greater than or equal to the mass of the asteroid, the
     * asteroid is destroyed and the planet gains the mass of the asteroid. Otherwise, the
     * planet is destroyed.
     * <p>
     * Return true if possible for all asteroids to be destroyed. Otherwise, return false.
     * <p>
     * Example 1:
     * Input: mass = 10, asteroids = [3,9,19,5,21]
     * Output: true
     * <p>
     * Explanation: One way to order the asteroids is [9,19,5,3,21]:
     * - The planet collides with the asteroid with a mass of 9. New planet mass: 10 + 9 = 19
     * - The planet collides with the asteroid with a mass of 19. New planet mass: 19 + 19 = 38
     * - The planet collides with the asteroid with a mass of 5. New planet mass: 38 + 5 = 43
     * - The planet collides with the asteroid with a mass of 3. New planet mass: 43 + 3 = 46
     * - The planet collides with the asteroid with a mass of 21. New planet mass: 46 + 21 = 67
     * All asteroids are destroyed.
     * <p>
     * Example 2:
     * Input: mass = 5, asteroids = [4,9,23,4]
     * Output: false
     * <p>
     * Explanation:
     * The planet cannot ever gain enough mass to destroy the asteroid with a mass of 23.
     * After the planet destroys the other asteroids, it will have a mass of 5 + 4 + 9 + 4 = 22.
     * This is less than 23, so a collision would not destroy the last asteroid.
     * <p>
     * Constraints:
     * 1 <= mass <= 105
     * 1 <= asteroids.length <= 105
     * 1 <= asteroids[i] <= 105
     *
     * @param mass      - integer value representing the mass of the planet
     * @param asteroids - integer array of the mass of asteroids
     * @return - return true if all asteroids destroyed, else false.
     */

    public static boolean asteroidsDestroyed(int mass, int[] asteroids) {
        // Sort the asteroids in ascending order
        Arrays.sort(asteroids);

        // Simulate collisions
        long currentMass = mass; // Use long to avoid overflow
        for (int asteroid : asteroids) {
            if (currentMass >= asteroid) {
                currentMass += asteroid;
            } else {
                return false; // Planet cannot destroy this asteroid
            }
        }
        return true; // All asteroids destroyed
    }

    /**
     * Method numRescueSleds
     * <p>
     * You are given an array people where people[i] is the weight of the ith person,
     * and an infinite number of rescue sleds where each sled can carry a maximum weight
     * of limit. Each sled carries at most two people at the same time, provided the
     * sum of the weight of those people is at most limit. Return the minimum number
     * of rescue sleds to carry every given person.
     * <p>
     * Example 1:
     * Input: people = [1,2], limit = 3
     * Output: 1
     * Explanation: 1 sled (1, 2)
     * <p>
     * Example 2:
     * Input: people = [3,2,2,1], limit = 3
     * Output: 3
     * Explanation: 3 sleds (1, 2), (2) and (3)
     * <p>
     * Example 3:
     * Input: people = [3,5,3,4], limit = 5
     * Output: 4
     * Explanation: 4 sleds (3), (3), (4), (5)
     *
     * @param people - an array of weights for people that need to go in a sled
     * @param limit  - the weight limit per sled
     * @return - the minimum number of rescue sleds required to hold all people
     */

    public static int numRescueSleds(int[] people, int limit) {
        // Sort the array of people's weights
        Arrays.sort(people);

        int left = 0; // Pointer for the lightest person
        int right = people.length - 1; // Pointer for the heaviest person
        int sleds = 0; // Count of rescue sleds needed

        while (left <= right) {
            // If the lightest and heaviest person can share a sled, pair them
            if (people[left] + people[right] <= limit) {
                left++;
            }
            // Move the pointer for the heaviest person
            right--;
            // Increment sled count
            sleds++;
        }

        return sleds;
    }
}
