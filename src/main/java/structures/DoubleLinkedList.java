package structures;

import java.util.Iterator;
import java.util.function.Function;

// !! Reescribir todas las explicaciones ya que la clase pasó a ser genérica
public class DoubleLinkedList<T> {
    private Node<T> nodeHead;
    private int nodeCounter;

    public class Node<T> {
        T data;
        Node<T> previousNode;
        Node<T> nextNode;

        Node(T data) {
            this.data = data;
            this.previousNode = null;
            this.nextNode = null;
        }

        public T getData() {
            return data;
        }

        public Node<T> getNextNode() {
            return nextNode;
        }

        public void setPreviousNode(Node<T> previousNode) {
            this.previousNode = previousNode;
        }

        public void setNextNode(Node<T> nextNode) {
            this.nextNode = nextNode;
        }
    }


    public DoubleLinkedList(){
        this.nodeHead=null;
        this.nodeCounter=0;
    }

    public Node<T> getNodeHead() {
        return nodeHead;
    }

    public int getNodeCounter() {
        return nodeCounter;
    }

    public void insertAtHead(T data) {
        Node newNode=new Node<T>(data);
        if (nodeHead == null) {
            nodeHead = newNode;
        } else {
            newNode.nextNode = nodeHead;
            nodeHead.previousNode = newNode;
            nodeHead = newNode;
        }
    }

    public void insertAtEnd(T data){ //Sin importar si es cola o stack, ambas insertan al final
        if (nodeHead==null){ // Si la cabeza de la lista está vacía, lo agregamos ahí
            nodeHead=new Node<T>(data);
        } else {
            Node<T> currentNode=nodeHead; // Si no lo está, empezamos un ciclo con la cabeza de la lista
            while (currentNode.getNextNode()!=null){ // El objetivo es recorrer hasta llegar a la última carta
                currentNode=currentNode.getNextNode();
            }
            Node<T> newNode=new Node(data);
            currentNode.setNextNode(newNode); // Al encontrarlo se sale del ciclo y la carta actual será la última carta
            newNode.setPreviousNode(currentNode); // Añadimos la carta como la última, y ponemos a la penúltima como su anterior
        }
        nodeCounter++;
    }

    public Node removeAtHead(){ //Añadimos este método ya que es útil para las colas
        if (nodeHead==null){ // Si la cabeza está vacía, eso implica que la lista está vacía, o sea que no hay nada que borrar
            return null;
        }
        Node referenceNodeHead=nodeHead;
        nodeHead=nodeHead.getNextNode(); //Reemplazamos la cabeza de la lista con la carta que le sigue
        if (nodeHead != null) { //Ponemos esto en caso tal de que la cabeza haya sido la única carta en la lista, para evitar intentar acceder a un valor nulo
            nodeHead.setPreviousNode(null); // Si hay más de un solo valor, es decir, la lista no queda vacía, entonces borramos la referencia de la zabeza actual a la cabeza anterior
            return referenceNodeHead;
        }
        return null;
    }
    public void removeAtEnd() {
        if (nodeHead == null) {
            return;
        }

        if (nodeHead.getNextNode() == null) {
            nodeHead = null;
            return;
        }

        Node<T> currentNode = nodeHead;
        while (currentNode.getNextNode() != null && currentNode.getNextNode().getNextNode() != null) {
            currentNode = currentNode.getNextNode();
        }
        currentNode.setNextNode(null); // Eliminar el último nodo
    }

    public void swapNodes(Node<T> node) {
        if (node == null || node.data == nodeHead.data) {
            return; // No se puede intercambiar con sí mismo o con un nodo nulo
        }

        Node<T> prevNode = node.previousNode;
        Node<T> nextNode = node.nextNode;
        if (prevNode != null) {
            prevNode.setNextNode(nodeHead);
        }
        if (nextNode != null) {
            nextNode.setPreviousNode(nodeHead);
        }
        Node<T> tempHeadNext = nodeHead.nextNode;
        if (tempHeadNext != null) {
            tempHeadNext.setPreviousNode(node);
        }
        nodeHead.setPreviousNode(node.previousNode);
        nodeHead.setNextNode(node.nextNode);
        node.setPreviousNode(null);
        node.setNextNode(tempHeadNext);
        nodeHead = node;
    }

    public Node<T> findNode(T card) {
        Node<T> currentNode = nodeHead;

        while (currentNode != null) {
            if (currentNode.getData().equals(card)) {
                return currentNode; // Se encontró el nodo que contiene la carta
            }
            currentNode = currentNode.getNextNode();
        }

        return null; // La carta no se encontró en la lista
    }

    public Node<T> getLastNode() {
        if (nodeHead == null) {
            return null; // La lista está vacía
        }

        Node<T> currentNode = nodeHead;
        while (currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();
        }

        return currentNode; // Retorna el último nodo
    }


    public Iterator<T> iterator() { // Se crea un objeto iterador, para poder usar bucles para buscar elementos en la lista doble enlazada
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> { // Clase interna del iterador de la lista enlazada, que define los métodos para que esta funcione
        private Node<T> current = nodeHead; //Inicializa el iterador con la cabeza de la lista

        @Override
        public boolean hasNext() { // Comprueba que hayan más elementos en la lista, es decir, que aún no se haya llegado al final de la lista
            return current != null;
        }

        @Override
        public T next() { // Obtiene los datos del siguiente nodo
            T data = current.data;
            current = current.nextNode;
            return data;
        }
    }
}
