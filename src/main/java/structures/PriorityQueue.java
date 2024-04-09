package structures;

import structures.BinaryHeap;

public class PriorityQueue<T> {

    private BinaryHeap<T> heap;

    public PriorityQueue(int capacity) {
        this.heap = new BinaryHeap(capacity);
    }

    public void enqueue(int priority, T element) {
        heap.insert(priority, element);
    }

    public BinaryHeap<T>.Element<T> dequeue() {
        return heap.remove();
    }

    public BinaryHeap<T>.Element<T> peek() {
        return heap.peekRoot();
    }

    public void invertHeapOrder() {
        heap.invertOrder();
    }

    public void printQueue() {
        heap.printHeap();
    }

    public void passTurn(){
        BinaryHeap<T>.Element<T> turn = heap.remove();
        int newTurn=turn.getPriority()+1;
        heap.insert(newTurn, turn.getElement());
    }
}
