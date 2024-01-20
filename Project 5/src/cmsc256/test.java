package cmsc256;

import java.io.FileNotFoundException;

public class test {
    public static void main(String[] args) throws FileNotFoundException {
        VariableValidator test = new VariableValidator("src/cmsc256/test.txt");
        test.createKeywordTree();
        System.out.println(test.getPreorderTraversal());
    }
}
