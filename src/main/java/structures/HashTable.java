package structures;

import model.Card;
import model.ColorCard;
import model.TypeCard;

import java.util.Iterator;
import java.util.Random;

public class HashTable {

    private DoubleLinkedList<Entry>[] cardsHashTable; // Creamos un arreglo hecho de listas dobles de cartas, ya que nuestra hash table
    // pretende dividir las cartas por color, y usar linked list por cada slot con el objetivo de evitar colisiones y no tener la
    // necesidad de recorrer una complejidad O(n) para poder sacar cartas de forma aleatoria

    class Entry { //Debemos crear una clase interna que representa las entradas para cada slot en la hash table
        ColorCard key; // Como deseamos dividirlo por colores, la llave para localizarse como objeto va a depender del color que tenga la carta
        Card value; //La información/datos de la carta la almacenamos como la carta misma
        boolean added;
        Entry(ColorCard key, Card value){
            this.key = key; // Asignamos el color de la carta como la llave de la entrada
            this.value = value; // Asignamos la carta misma como el valor de la entrada
            this.added=false;
        }
    }

    // El constructor de la hash table inicializa cada slot en la hash con una linked list vacía, con el objetivo de almacenar las
    // cartas que coincidan por color en los mismos slots
    public HashTable(){
        this.cardsHashTable=new DoubleLinkedList[5]; //Inicializamos el arreglo con un tamaño de 5 ya que hay 4 colores más el slot de cartas especiales
        for (int i = 0; i < 5; i++) {
            this.cardsHashTable[i]=new DoubleLinkedList<>(); //Creamos una linked list por slot
        }
    }

    //Función para ubicar el índice en la tabla hash
    private int hash(ColorCard color) {
        return color.ordinal(); // Utiliza el ordinal del color como función de hash tomando en cuenta que la cantidad de colores es
        // igual al tamaño de la hash table
    }

    // Este método agrega una carta en base a su color
    private void put(ColorCard key, Card value){
        int ind = hash(key);//Calculamos el índice según el color/llave dada
        DoubleLinkedList<Entry> list = cardsHashTable[ind]; // Ubicamos la lista correspondiente al color encontrado en el paso anterior
        cardsHashTable[ind].insertAtEnd(new Entry(key, value)); //Agregamos a la linked list una nueva entrada en base a los datos dados
    }

    // Este método lo que hace es, en base a una llave ingresada, saca el primer elemento que no haya sido agregado a una de las colas o pilas
    public Card getRandomCard(ColorCard key) {
        int ind = hash(key);
        DoubleLinkedList<Entry> list = cardsHashTable[ind]; // Obtener la lista correspondiente en la tabla hash
        Random random = new Random();
        int randomIndex = random.nextInt(list.getNodeCounter()-1); // Generar un índice aleatorio dentro del rango válido
        Iterator<Entry> iterator = list.iterator(); // Obtener un iterador para recorrer la lista
        int currentIndex = -1;
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            if (currentIndex == randomIndex && !entry.added) { // Verificar si es el nodo seleccionado y no ha sido agregado
                entry.added = true;
                return entry.value; // Devolver la carta asociada a esta entrada
            }
            currentIndex++;
        }
        return null; // Si no se encuentra ninguna carta disponible para selección aleatoria, devolver null
    }

    // Este método inicializa la tabla hash, agregando todas las cartas en los slots según el color
    public void addCards() {
        int ind = 0;
        ColorCard[] colors = ColorCard.values();
        TypeCard[] typeCards = TypeCard.values();
        for (int i = 0; i < 4; i++) {
            put(colors[i], new Card(ind++, colors[i], 0, TypeCard.CLASSIC)); // Primera carta con valor 0 para cada color
            for (int j = 0; j < 2; j++) { // Repetir dos veces para el primer color
                for (int k = 1; k < 10; k++) { // Añadir 8 cartas con valores del 1 al 8
                    put(colors[i], new Card(ind++, colors[i], k, TypeCard.CLASSIC));
                }
                for (int p = 1; p < 4; p++) { // Para los otros tipos de cartas
                    put(colors[i], new Card(ind++, colors[i], -1, typeCards[p])); // Añadir una carta especial para cada color
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            put(colors[4], new Card(ind++, colors[4], -1, TypeCard.WILD));
        }
    }

    public void showCards() {
        ColorCard[] colors = ColorCard.values();
        for (int i = 0; i < 5; i++) {
            System.out.println("Cartas en el slot de color " + colors[i] + ":");
            DoubleLinkedList<Entry> list = cardsHashTable[i];
            Iterator<Entry> iterator = list.iterator();
            while (iterator.hasNext()) {
                Entry entry = iterator.next();
                System.out.println("Índice: "+entry.value.getInd()+", Color: " + entry.value.getColor() +
                        ", Number: " + entry.value.getNumber() +
                        ", Type: " + entry.value.getTypeCard());
            }
            System.out.println();
        }
    }

}
