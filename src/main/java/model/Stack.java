package model;

import java.util.Iterator;

public class Stack {
    private DoubleLinkedList<Card> list;
    private Card top;

    public Stack(){
        this.list=new DoubleLinkedList();
        this.top=null;
    }

    public void push(Card card) { // Se inserta una carta al stack
        list.insertAtEnd(card);
        top=card; // Mantenemos el registro de la última carta en entrar
    }

    public Card pop() { // Al remover la última carta, también debemos modificar el último elemento agregado como el penúltimo
        if (isEmpty()) {
            System.out.println("La pila está vacía");
            return null;
        }
        Card removedNode = top;
        list.removeAtEnd();
        top=list.getLastNode().getData();
        return removedNode;
    }

    public boolean isEmpty() { // Si top está vacío, entonces la lista está vacía
        return top == null;
    }

    public Card peek() { // Retornamos el último elemento agregado
        return top;
    }

    public void deque(Card card) {
        list.insertAtHead(card);
    }

    public void seeCards() {
        Iterator<Card> iterator = list.iterator(); // Obtener un iterador para recorrer la lista
        while (iterator.hasNext()) { // Iterar mientras haya más elementos en la lista
            Card card = iterator.next(); // Obtener el siguiente elemento
            System.out.println("Índice: "+card.getInd()+", Color: " + card.getColor() +
                    ", Number: " + card.getNumber() + ", Type: " + card.getTypeCard());
        }
    }
}
