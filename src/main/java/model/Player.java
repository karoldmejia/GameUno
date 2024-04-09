package model;
import java.util.Iterator;

public class Player {
    private String name;
    private Queue deckPlayer;

    public Player(String name){
        this.name=name;
        this.deckPlayer=new Queue();
    }

    public String getName() {
        return name;
    }

    public Queue getDeckPlayer() {
        return deckPlayer;
    }

    public void setDeckPlayer(Queue deckPlayer) {
        this.deckPlayer = deckPlayer;
    }

    public void seeCards() {
        System.out.println("Cartas del jugador " + name + ":");
        deckPlayer.seeCards();
    }

    public String seeMatch(Card cardToMatch) {
        StringBuilder matchCards = new StringBuilder();
        Iterator<Card> iterator = deckPlayer.iterator(); // Obtener un iterador para recorrer la lista
        while (iterator.hasNext()) { // Iterar mientras haya más elementos en la lista
            Card card = iterator.next(); // Obtener el siguiente elemento
            if (card.getColor() == cardToMatch.getColor() || card.getNumber() == cardToMatch.getNumber() || card.getTypeCard() == TypeCard.WILD) {
                matchCards.append(card.getCardInfo()).append("\n");
            }
        }
        return matchCards.toString();
    }

    public Card getCardByIndex(int index) {
        Iterator<Card> iterator = deckPlayer.iterator();
        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card.getInd() == index) {
                return card; // Retorna la carta en el índice especificado
            }
        }
        return null; // Retorna null si el índice está fuera de rango
    }

    public void moveCardToFirstPosition(Card cardToMove) {
        Iterator<Card> iterator = deckPlayer.iterator();
        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card.equals(cardToMove)) {
                deckPlayer.changePeek(card);
                break; // Terminamos el bucle después de mover la carta
            }
        }
    }
}
