package structures;

import java.util.Iterator;

public class Queue<T> {
    private DoubleLinkedList<T> list;

    public Queue(){
        this.list=new DoubleLinkedList<>();
    }
    public void enqueue(T data) {
        list.insertAtEnd(data);
    }

    public void dequeue() {
        list.removeAtHead();
    }

    public boolean isEmpty() {
        return list.getNodeHead()==null;
    }

    public DoubleLinkedList<T>.Node<T> peek() {
        if (!isEmpty()) {
            return list.getNodeHead();
        }
        return null;
    }

    public void changePeek(T data) {
        DoubleLinkedList<T>.Node<T> newHead = list.findNode(data);
        list.swapNodes(newHead); // Establecer el nuevo nodo como el nodeHead
    }

    public String seeElements() {
        StringBuilder elementsInfo = new StringBuilder();
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            T item = iterator.next();
            elementsInfo.append(item.toString()).append("\n");
        }
        return elementsInfo.toString();
    }

    public Iterator<T> iterator() {
        return list.iterator();
    }

    public int size(){
        return list.getNodeCounter();
    }

}
