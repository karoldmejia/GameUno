package model;

import java.util.Iterator;

public class Queue {
    private DoubleLinkedList<Card> list;

    public Queue(){
        this.list=new DoubleLinkedList<>();
    }
    public void enqueue(Card card) {
        list.insertAtEnd(card);
    }

    public void dequeue() {
        list.removeAtHead();
    }

    public boolean isEmpty() {
        return list.getNodeHead()==null;
    }

    public DoubleLinkedList<Card>.Node<Card> peek() {
        if (!isEmpty()) {
            return list.getNodeHead();
        }
        return null;
    }

    public void changePeek(Card card) {
        DoubleLinkedList<Card>.Node<Card> newHead = list.findNode(card);
        list.swapNodes(newHead); // Establecer el nuevo nodo como el nodeHead
    }

    public void seeCards() {
        Iterator<Card> iterator = list.iterator(); // Obtener un iterador para recorrer la lista
        while (iterator.hasNext()) { // Iterar mientras haya más elementos en la lista
            Card card = iterator.next(); // Obtener el siguiente elemento
            System.out.println("Índice: "+card.getInd()+", Color: " + card.getColor() +
                   ", Number: " + card.getNumber() + ", Type: " + card.getTypeCard());
        }
    }

    public Iterator<Card> iterator() {
        return list.iterator();
    }

}
