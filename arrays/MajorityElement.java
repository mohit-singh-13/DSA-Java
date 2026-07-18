package arrays;

class MajorityElement {
  public int majorityElement(int[] nums) {
    int currentMajorityElement = nums[0];
    int currentMajorityCount = 1;

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == currentMajorityElement) {
        currentMajorityCount++;
      } else {
        currentMajorityCount--;

        if (currentMajorityCount == 0) {
          currentMajorityElement = nums[i];
          currentMajorityCount = 1;
        }
      }
    }

    return currentMajorityElement;
  }
}