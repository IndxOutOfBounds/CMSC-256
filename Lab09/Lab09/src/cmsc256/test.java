package cmsc256;


import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        setup();
    }
    public static void setup() {
        ArrayList <Integer> nums = new ArrayList <Integer>();
        nums.add(3);
        nums.add(-1);
        nums.add(2);
        nums.add(5);
        nums.add(-3);
        mysterySort(nums);
        System.out.println(nums);
    }

    public static void mysterySort(ArrayList<Integer> items) {
        int f, temp;

        for (int outer = 0; outer < items.size() - 1; outer++)
        {
            f = outer;
            for (int inner = outer + 1; inner < items.size(); inner++)
            {
                if (items.get(inner) < items.get(f))
                {
                    f = inner;
                }
            }
            //swap list.get(outer) & list.get(f)
            temp = items.get(outer);
            items.set(outer,items.get(f));
            items.set(f,temp);
            /* end of outer for loop */
        }
    }
}