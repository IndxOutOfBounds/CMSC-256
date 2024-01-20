/*
 * Name: Rattandeep Ruprai
 * Java Variable Validator
 * Class: CMSC 256 (Online section)
 * 12/8/2023
 * This code takes in a java file and a keyword file. Returns valid and invalid keywords.
 */
package cmsc256;
import java.io.*;
import java.util.*;

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
    public void displayValidKeywords(Map<String, Integer> wordList) {
        if (wordList.isEmpty()) {
            System.out.println("No valid keywords found.");
        } else {
            for (Map.Entry<String, Integer> entry : wordList.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " occurrences");
            }
        }
    }
    public Map<String, Integer> getValidJavaIdentifiers() {
        Map<String, Integer> validIdentifiers = new HashMap<>();
        if (javaFile == null || tree == null) {
            throw new IllegalStateException();
        }
        try {
            Scanner scanner = new Scanner(javaFile);
            boolean insideBlockComment = false;
            boolean insideStringLiteral = false;
            while (scanner.hasNext()) {
                String token = scanner.next();
                if (token.startsWith("/*")) {
                    insideBlockComment = true;
                }
                if (insideBlockComment && token.endsWith("*/")) {
                    insideBlockComment = false;
                    continue;
                }
                if (!insideBlockComment && token.startsWith("//")) {
                    scanner.nextLine();
                    continue;
                }
                if (!insideBlockComment) {
                    if (token.contains("\"")) {
                        insideStringLiteral = !insideStringLiteral;
                        if (token.endsWith("\"")) {
                            StringBuilder editToken = new StringBuilder();
                            for (int i = 0; i < token.length(); i++) {
                                if (token.charAt(i) != '"') {
                                    editToken.append(token.charAt(i));
                                }
                                if (token.charAt(i) == '"') {
                                    break;
                                }
                            }
                            processToken(editToken.toString(), validIdentifiers);
                            insideStringLiteral = false;
                            continue;
                        }
                        while (scanner.hasNext() && !token.contains("\"")) {
                            token = scanner.next();
                        }
                    }
                    if (insideStringLiteral) {
                        continue;
                    }
                    processToken(token, validIdentifiers);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
        }
        return validIdentifiers;
    }
    private void processToken(String token, Map<String, Integer> validIdentifiers) {
        List<String> words = splitIdentifier(token);
        for (String word : words) {
            if (!isReservedWord(word) && isValidJavaIdentifier(word)) {
                validIdentifiers.merge(word, 1, Integer::sum);
            }
        }
    }
    private List<String> splitIdentifier(String identifier) {
        List<String> words = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();
        for (int i = 0; i < identifier.length(); i++) {
            char ch = identifier.charAt(i);
            if (ch == '.' || ch == ';' || ch == '(' || ch == ')' || ch == '[' || ch == ']') {
                words.add(currentWord.toString());
                currentWord.setLength(0);
                continue;
            }
            currentWord.append(ch);
            if (i == identifier.length() - 1) {
                words.add(currentWord.toString());
            }
        }
        return words;
    }
    private boolean isReservedWord(String token) {
        return tree.find(token) != null;
    }
    private boolean isValidJavaIdentifier(String identifier) {
        if (identifier.isEmpty()) {
            return false;
        }
        char firstChar = identifier.charAt(0);
        if (!(Character.isLetter(firstChar) || firstChar == '_' || firstChar == '$')) {
            return false;
        }
        for (int i = 1; i < identifier.length(); i++) {
            char currentChar = identifier.charAt(i);
            if (!(Character.isLetterOrDigit(currentChar) || currentChar == '_' || currentChar == '$')) {
                return false;
            }
        }
        return true;
    }
    public Map<String, List<Integer>> getInvalidJavaIdentifiers(){
        Map<String, List<Integer>> invalidIdentifiers = new HashMap<>();
        return invalidIdentifiers;
    }
}
