package com.barclays.lpras.test.thread;

public class MyLinkedList<T> {

    Node<T> head;
    public T removeFirst(){
        if(head == null){
            return null;
        }else{
            Node<T> tmp = head;
            head=head.next;
            return  tmp.data;
        }
    }

    public T removeLast(){
        if(head == null){
            return null;
        }else if(head.next ==null){
            T t=head.next.data;
            head.next=null;
            return t;
        }
        else{
            Node<T> tmp=head;
            Node<T> prvTemp=head;
            int count=1;
            while(tmp.next.next != null){
                tmp=tmp.next;
            }
            T t=tmp.next.data;
            tmp.next=null;
            return t;
        }
    }


    public void print(){
        Node<T>  current = head;
        while(current != null){
            System.out.print(current.data + "-->");
            current = current.next;
        }
    }

    public void addFirst(T data){
        Node<T> newNode = new Node<>(data,null);
        if(head == null)
            head = newNode;
        else{
            newNode.next=head;
            head=newNode;
        }
    }
    public void addLast(T data){
        Node<T> newNode = new Node<>(data,null);
        if(head == null)
            head = newNode;
        else{
            Node<T> tmp = head;
            while(tmp.next != null){
                tmp=tmp.next;
            }
            tmp.next=newNode;
        }
    }

    public void addAtIdx(T data, int idx){
        Node<T> newNode = new Node<>(data,null);
        Node<T> current=head;
        Node<T> prev=head;

        if(idx < 1){
            throw new RuntimeException("Index out of range");
        }else if(idx ==1){
            addFirst(data);
        }else{
            int count =1;
            while(current != null){
                if(count >1)
                    prev = prev.next;
                count++;
                current=current.next;
                if(count == idx){
                    newNode.next=prev.next;
                    prev.next=newNode;
                    break;
                }
                if(count>idx) break;
            }
            if(idx > count)
                throw new RuntimeException("Idx out of range");

        }
        current=null;
    }

    public int size(){
        int size=0;
        Node<T> tmp = head;
        while(tmp != null){
            tmp = tmp.next;
            size++;
        }
        return size;
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data, Node<T>  next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        /*public void setData(T data) {
            this.data = data;
        }*/

        public Node getNext() {
            return next;
        }

        /*public void setNext(Node next) {
            this.next = next;
        }*/
    }
}
