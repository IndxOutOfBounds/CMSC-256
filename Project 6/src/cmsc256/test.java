package cmsc256;

import java.io.FileNotFoundException;
import java.util.Map;

public class test {
    public static void main(String[] args) throws FileNotFoundException {
        String javaFile = "MathExpressions2.java";
        VariableValidator validator = new VariableValidator("src/cmsc256/JavaKeywordList.txt", javaFile);
        AVLTree<String> aTree = validator.createKeywordTree();
        // display root, height
        System.out.println("\nRoot of tree is "   + aTree.getRoot().getElement());
        System.out.println("Height of tree is " + aTree.getRoot().getHeight());

        // display traversals
        String inorder = validator.getInorderTraversal().trim();
        System.out.println("Inorder: " + inorder);
        System.out.println(inorder.startsWith("ABS") && inorder.endsWith("WHERE"));
        String preorder = validator.getPreorderTraversal().trim();
        System.out.println("Preorder: " + preorder);
        System.out.println(preorder.startsWith("FROM") && preorder.endsWith("WHERE"));
        String postorder = validator.getPostorderTraversal().trim();
        System.out.println("Postorder: " + postorder);
        System.out.println(postorder.startsWith("ABS") && postorder.endsWith("FROM"));

        Map<String, Integer> wordList = validator.getValidJavaIdentifiers();
        System.out.println("\nThe following tokens are not Java keywords in the file, " + javaFile + ":");
        validator.displayValidKeywords(wordList);  // private helper method to print the Map
        System.out.printf("There are %d variables in the java file\n", wordList.size());
        System.out.printf("The variable, System, appears %d times.\n", wordList.get("System"));
        System.out.printf("The variable, system, appears %d times.\n", wordList.get("system"));
        System.out.println("\nThe following variables are invalid Java variables in the file, " + javaFile + ":");
//        validator.displayInvalidVariables();   // private helper method to print the Map
        System.out.println();
    }
}
