package com.practice.algorithms.datastructures;

public class Stack<T> {

    private Node first = null;

    private int size = 0;

    private class Node{
        T item;
        Node next;
    }

    public void push(T item){
        Node i = new Node();
        i.item = item;
        i.next = first;
        first = i;
        size++;
    }

    public T pop(){
        if(first == null) return null;
        Node i = first;
        first = i.next;
        size--;
        return i.item;
    }

    public T peek(){
        if(first == null) return null;
        return first.item;
    }

    public int size(){
        return size;
    }

    public static void main(String... args){
        Stack<String> s = new Stack<>();
        s.push("item1");
        s.push("item2");
        s.push("item3");
        System.out.println(s.pop());
        System.out.println(s.size());
        System.out.println(s.pop());
        System.out.println(s.pop());
        s.push("new item");
        System.out.println(s.peek());
        System.out.println(s.size());
        System.out.println(s.pop());
        System.out.println(s.size());
    }
}
