/*
 * Name: Rattandeep Ruprai
 * Java Variable Validator
 * Class: CMSC 256 (Online section)
 * 11/17/2023
 * This code takes in a file and creates a AVLTree out of the keywords in that text. It can return the pre, post and in order traversal of the AVLTree.
 */
package cmsc256;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VariableValidator implements ProgramParserInterface {
    private File javaFile;
    private File keywordFile;
    private AVLTree tree;
    public VariableValidator(String keywords) {
        this.javaFile = null;
        setKeywordFile(keywords);
    }
    public VariableValidator(String keywords, String code) {
        setKeywordFile(keywords);
        setJavaFile(code);
    }
    public String getJavaFileName() {
        return javaFile.getName();
    }

    public void setJavaFile(String javaFileName) {
        if (javaFileName == null) {
            throw new IllegalArgumentException("Java file cannot be null");
        }
        javaFile = new File(javaFileName);
        if (!javaFile.exists()) {
            throw new IllegalArgumentException("Java File does not exist");
        }
    }

    public String getKeywordFileName() {
        return keywordFile.getName();
    }
    public void setKeywordFile(String keywordFileName) {
        if (keywordFileName == null) {
            throw new IllegalArgumentException("Keyword FIle cannot be null");
        }
        keywordFile = new File(keywordFileName);
        if (!keywordFile.exists()) {
            throw new IllegalArgumentException("Keyword File does not exist");
        }
    }

    public AVLTree<String> createKeywordTree() throws FileNotFoundException {
        tree = new AVLTree<>();
        try {
            Scanner in = new Scanner(keywordFile);
            while (in.hasNext()) {
                String nextWord = in.next();
                tree.insert(nextWord);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found, verify location");
        }
        return tree;
    }

    public String getInorderTraversal() {
        if (tree != null) {
            return inorderTraversal(tree.getRoot());
        }
        return "";
    }

    public String getPreorderTraversal() {
        if (tree != null) {
            return preorderTraversal(tree.getRoot());
        }
        return "";
    }

    public String getPostorderTraversal() {
        if (tree != null) {
            return postorderTraversal(tree.getRoot());
        }
        return "";
    }
    private String inorderTraversal(AVLTree.AVLNode node) {
        if (node != null) {
            return inorderTraversal(node.getLeft()) + node.getElement() + " " + inorderTraversal(node.getRight());
        }
        return "";
    }
    private String preorderTraversal(AVLTree.AVLNode node) {
        if (node != null) {
            return node.getElement() + " " + preorderTraversal(node.getLeft()) + preorderTraversal(node.getRight());
        }
        return "";
    }
    private String postorderTraversal(AVLTree.AVLNode node) {
        if (node != null) {
            return postorderTraversal(node.getLeft()) + postorderTraversal(node.getRight()) + node.getElement() + " ";
        }
        return "";
    }
}
