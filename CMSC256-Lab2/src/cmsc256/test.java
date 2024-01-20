package cmsc256;

import org.w3c.dom.ls.LSOutput;

public class test {
    public static void main(String[] args) {
        int prologue = 1;
        int sequel = 3;
        int epilogue = 5;
        epilogue = sequel;
        sequel = prologue * epilogue;
        prologue = epilogue;
        epilogue = sequel;
        prologue = sequel + epilogue;
        epilogue = prologue / sequel;
        sequel = prologue;
        System.out.println(sequel);
    }
}

