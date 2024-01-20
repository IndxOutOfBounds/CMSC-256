package cmsc256;
/*
Programmer A: Rattandeep Ruprai
Programmer B: N/A
*/

public class RecursiveMethods {
	/*
	 * @returns a String of character, ch. The length is determined
	 * 			by the second parameter, length.
	 */
	public static String buildStringOfCharacters(char ch, int length) {
		String end = "";
		if (length <= 0) {
			return "";
		} else {
			return ch + buildStringOfCharacters(ch, length-1);
		}
	}

	/*
	 * returns an int array that has the elements in reverse order
	 * 			of the parameter array, nums.
	 * Process this recursively beginning with the last element.
	 */
	public static int[] reverseNumArray(int[] nums, int backIndex) {
		if (backIndex == 0){
			return nums;
		}
		int frontIndex = (nums.length-1) - backIndex;
		if (frontIndex > backIndex) {
			return nums;
		}
		int temp = nums[frontIndex];
		nums[frontIndex] = nums[backIndex];
		nums[backIndex] = temp;
		nums = reverseNumArray(nums, backIndex-1);
		return nums;
	}

	/*
	 * returns true if the int array parameter is sorted from smallest
	 * 			to largest, false otherwise.
	 * Process this recursively beginning with the first element.
	 */
	public static boolean isSmallestToLargest(int[] values, int firstIndex) {
//		if (firstIndex == values.length){
//			return true;
//		}
//		if (values[firstIndex] < values[firstIndex=1] && firstIndex < values.length) {
//			firstIndex++;
//		}
//		isSmallestToLargest(values, firstIndex);
		return false;
	}

	/*
	 * @returns true if the parameter String, str is a palindrome
	 * 			false otherwise
	 */
	public static boolean isPalindrome(String str, int begin, int end) {

		return false;
	}


	public static void main(String[] args) {
		// create tests for your method here
//		int back = 5;
//		int[] temp = {1, 2, 3, 4, 5, 6};
//		temp = reverseNumArray(temp, back);
//		for (int i = 0; i < temp.length; i++) {
//			System.out.println(temp[i]);
//		}
//		char ch = 'J';
//		int number = -1;
//		System.out.println(buildStringOfCharacters(ch, number));
		int[] values = {1,3,5,7};
		int firstIndex = 0;
		System.out.println(isSmallestToLargest(values, firstIndex));
	}
}
