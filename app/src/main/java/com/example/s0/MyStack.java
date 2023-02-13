/**
 * Example from the "Pragmatic Unit Testing in Java with JUnit"
 * book by Andrew Hunt and David Thomas
 */

package com.example.s0;

public class MyStack {
    private String[] stack;
    private int nextIndex;

    public MyStack() {
        stack = new String[100];
        nextIndex = 1;
    }

    public String pop() {
        return stack[--nextIndex];
    }

    // Delete n items from the stack en-masse
    public void delete(int n) {
        nextIndex -= n;
    }

    public void push(String string) {
        stack[nextIndex++] = string;
    }

    public String top() {
        return stack[nextIndex - 1];
    }

}

