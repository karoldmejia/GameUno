package structures;

public class Stack<T> {
    private DoubleLinkedList<T> list;
    private T top;

    public Stack(){
        this.list=new DoubleLinkedList();
        this.top=null;
    }

    public void push(T data) { // Se inserta una carta al stack
        list.insertAtEnd(data);
        top=data; // Mantenemos el registro de la última carta en entrar
    }

    public T pop() { // Al remover la última carta, también debemos modificar el último elemento agregado como el penúltimo
        if (isEmpty()) {
            System.out.println("La pila está vacía");
            return null;
        }
        T removedNode = top;
        list.removeAtEnd();
        top=list.getLastNode().getData();
        return removedNode;
    }

    public boolean isEmpty() { // Si top está vacío, entonces la lista está vacía
        return top == null;
    }

    public T peek() { // Retornamos el último elemento agregado
        return top;
    }

    public void deque(T data) {
        list.insertAtHead(data);
    }

}
