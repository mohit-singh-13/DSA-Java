package arrays;

import java.util.Arrays;

class TwoSum {
  public int[] twoSum(int[] nums, int target) {
    int noOfElements = nums.length;
    int[][] numsOriginalIndices = new int[noOfElements][2];

    for (int i = 0; i < noOfElements; i++) {
      numsOriginalIndices[i][0] = nums[i];
      numsOriginalIndices[i][1] = i;
    }

    Arrays.sort(numsOriginalIndices, (a, b) -> a[0] - b[0]);

    int left = 0;
    int right = noOfElements - 1;

    while (left < right) {
      int sum = numsOriginalIndices[left][0] + numsOriginalIndices[right][0];

      if (sum == target) {
        return new int[] { numsOriginalIndices[left][1], numsOriginalIndices[right][1] };
      } else if (sum > target) {
        right--;
      } else {
        left++;
      }
    }

    return new int[] { -1, -1 };
  }
}