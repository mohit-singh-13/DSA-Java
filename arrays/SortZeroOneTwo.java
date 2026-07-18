package arrays;

class SortZeroOneTwo {
  public void swap(int[] nums, int ind1, int ind2) {
    int temp = nums[ind1];
    nums[ind1] = nums[ind2];
    nums[ind2] = temp;
  }

  public void sortColors(int[] nums) {
    int low = 0;
    int mid = 0;
    int high = nums.length - 1;

    while (mid <= high) {
      if (nums[mid] == 0) {
        swap(nums, mid, low);
        mid++;
        low++;
      } else if (nums[mid] == 1) {
        mid++;
      } else {
        swap(nums, mid, high);
        high--;
      }
    }
  }
}