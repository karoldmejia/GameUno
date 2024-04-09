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

    public void seeElements() {
        Iterator<T> iterator = list.iterator(); // Obtener un iterador para recorrer la lista
        while (iterator.hasNext()) { // Iterar mientras haya más elementos en la lista
            T item = iterator.next(); // Obtener el siguiente elemento
            System.out.println(item.toString());
        }
    }

    public Iterator<T> iterator() {
        return list.iterator();
    }

}
