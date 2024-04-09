package structures;

public class BinaryHeap<T> {
    private Element<T>[] heap; // Representamos el heap con un arreglo

    private int size; //Es el encargado de llevar registro de cuántos nodos tiene el árbol

    public BinaryHeap(int maxsize){
        this.size=0; // Inicializamos el tamaño en el comienzo del array
        heap= new Element[maxsize]; // Creamos el array según el tamaño definido por el usuario
    }

    public class Element<T> {
        private int priority;
        private T element;

        public Element(int priority, T element) {
            this.priority = priority;
            this.element = element;
        }

        public int getPriority() {
            return priority;
        }

        public T getElement() {
            return element;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "[" + priority + ": " + element.toString() + "]";
        }
    }

    public void insert(int turn, T element) { // Este método es para insertar un elemento en el árbol y organizarlo adecuadamente
        Element<T> newElement = new Element<>(turn, element);
        heap[size] = newElement; // Ubicamos el nodo que queremos insertar en el último lugar del arreglo
        int currentNode = size; // Inicializamos el proceso de organización del nodo con el índice que conocemos (o sea el de el último elemento ingresado)
        while (currentNode > 0) { // Establecemos como condición que el nodo no sea la raíz ya que vamos a buscar constantemente a los nodos padres, y que sea una raíz causaría una excepción NullPointer
            int parentNode = (currentNode - 1) / 2; // Calculamos el índice del padre del nodo actual según la fórmula
            if (heap[currentNode].getPriority() < heap[parentNode].getPriority()) { // Como estamos en un min heap, si el nodo es menor que su nodo padre, hay que cambiarlo de lugar con el del padre, ya que todo padre debe ser menor que sus hijos
                Element<T> temp = heap[currentNode];
                heap[currentNode] = heap[parentNode];
                heap[parentNode] = temp; // En esas tres líneas hacemos un proceso de reemplazo, y el nodo padre ahora es el hijo y viceversa
                currentNode = parentNode; // Como el nodo actual ahora es el nodo padre, tomamos el índice del nodo padre y reemplazamos, y continuamos este proceso hasta llegar a la raíz del árbol
            } else {
                break;
            }
        }
        size++; // Aumentamos el tamaño del arreglo según el valor insertado
    }

    //Pd: Implementamos un método que organiza desde abajo hacia arriba ya que después de insertar un nuevo
    // elemento se necesita verificar y corregir múltiples nodos para asegurarse de que el nuevo elemento
    // se coloque en la posición correcta en el heap.

    //Pendiente por modificación de detalles
    public void heapify(int i){ // Este método es para hacer que el heap se mantenga su propiedad al cambiar UN nodo. Lo utilizamos más específicamente al eliminar un nodo (la raíz)
        int parent=i; // Ahora el elemento padre es el índice que ingresamos
        if (i<(size/2)){ // La condición dice que si el índice es menor o igual a la mitad del arreglo, es decir, que si está ubicado en la primera mitad del arreglo, puede proceder. Esto es para identificar que el índice no sea una hoja, y que por lo tanto, si tenga nodos hijos
            int rightChild = (i*2)+1;
            int leftChild = ((i+1)*2); // Calculamos los índices de ambos hijos
            int minChild = ((heap[rightChild].getPriority() < heap[leftChild].getPriority()) ? rightChild : leftChild); // En caso de tener que intercambiar nodos, queremos asegurarnos de que el nodo hijo elegido sea el menor de todos, ya que si no lo es, no quedaría bien organizado ya que el padre sería mayor a uno de sus hijos
            if (minChild < size && heap[minChild].getPriority() < heap[parent].getPriority()) {
                parent = minChild;
            }
            // Utilizamos la primera condición para verificar que sí existe un hijo
            // Si existe, si ese hijo es menor que su padre, entonces actualizamos el índice de padre al índice del hijo seleccionado (aún sin mover nada en el arreglo, solo estamos seleccionando)
            if (heap[i].getPriority() > heap[parent].getPriority()){ // Entonces, verificamos nuevamente, y en caso de que el nodo ingresado (o sea el padre inicial) sea mayor al hijo menor anteriormente calculado, hay que intercambiar posiciones
                Element<T> temp=heap[i];
                heap[i]=heap[parent];
                heap[parent]=temp; // Aquí finalmente reemplazamos en el arreglo, teniendo al hijo menor como nuevo padre, y viceversa
                heapify(parent); // Llamamos recursivamente a heapify para asegurarnos que el nuevo padre cumpla la condición del min-heap correctamente
            }
        }
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i].toString() + " ");
        }
        System.out.println();
    }

    //Pendiente por modificación de todo
    public Element<T> remove(){ // Se encarga de eliminar el nodo raíz
        if (size == 0) {
            return null;
        }
        Element<T> elementOut=heap[0];
        heap[0] = heap[size-1]; // Para eliminarlo, reemplazamos la raíz con el último agregado
        size--; // Como eliminamos un nodo, reducimos el tamaño del árbol en uno
        elementOut.setPriority(heap[0].getPriority());
        heapify(0); // Llamamos a heapify para que organice adecuadamente al min heap
        return elementOut; // Retornamos el elemento eliminado para que sea agregado posteriormente a la cola
    }

    public void invertOrder(){ // Este método inverte el orden del árbol (será implementado cuando usemos la carta que provoca este efecto)
        for (int i = 0; i < size; i++) { // El método usado es multiplicar todos los elementos del arreglo por -1, así entonces cada vez que se necesite invertir se reorganizará pero se seguirá manteniendo la forma de min-heap
            int moveTurn = heap[i].getPriority() * -1;
            heap[i].setPriority(moveTurn);
        }
        for (int i = (size / 2) - 1; i >= 0; i--) {
            heapify(i);
        }
        // Después de multiplicar todos los elementos, debemos reorganizarlos para que mantenga la forma de min-heap.
        // Iniciamos el for desde los últimos padres (o los nodos en el nivel más bajo que tengan al menos un hijo), y lo que hacemos es ir organizando
        // el min-heap de abajo hacia arriba, por lo que vamos disminuyendo el i hasta llegar a 0, que sería la raíz del árbol
    }

    public Element<T> peekRoot() {
        return heap[0]; // Devolver el valor de la raíz del BinaryHeap (primer elemento del arreglo)
    }
}
