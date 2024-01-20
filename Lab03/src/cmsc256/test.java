package cmsc256;

public class test {
    public static void main(String[] args) {
        int[] array = {1, 6, 4};
       boolean temp = array6(array, 0);
        System.out.println(temp);
    }
    public static boolean array6(int[] nums, int index) {
        if (nums[index] == 6) {
            return true;
        }
        if (nums.length > index+1) {
            return array6(nums, index+1);
        }
        return false;

    }



}