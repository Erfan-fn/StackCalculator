package com.example.stackcalculator;

public interface IQueue<E> {

        //Stack push function-Insert an element at the top of the stack
        public abstract void push(E element);
        //Stack peek function-Returns,but not remove the element of the top of the stack
        public abstract E peek();
        //Stack pop function-Returns,and remove the element of the top of the stack
        public abstract E pop();
        //size function-Returns the current size of the stack
        public abstract int size();
        //Return true if stack is empty and false if stack is not empty
        public abstract boolean isEmpty();

        public abstract int getTop();

}
