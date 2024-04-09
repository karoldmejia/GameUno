package model;
import structures.Queue;

import java.util.Iterator;

public class Player {
    private String name;
    private Queue<Card> deckPlayer;

    public Player(String name){
        this.name=name;
        this.deckPlayer=new Queue<Card>();
    }

    public String getName() {
        return name;
    }

    public Queue<Card> getDeckPlayer() {
        return deckPlayer;
    }

    public String seeCards() {
        StringBuilder cardsInfo = new StringBuilder();
        cardsInfo.append(name).append("'s deck:\n");
        cardsInfo.append(deckPlayer.seeElements());
        return cardsInfo.toString();
    }


    public String seeMatch(Card cardToMatch) {
        StringBuilder matchCards = new StringBuilder();
        Iterator<Card> iterator = deckPlayer.iterator(); // Obtener un iterador para recorrer la lista
        while (iterator.hasNext()) { // Iterar mientras haya más elementos en la lista
            Card card = iterator.next(); // Obtener el siguiente elemento
            if (card.getTypeCard().equals(TypeCard.CLASSIC)) {
                if (card.getColor() == cardToMatch.getColor() || card.getNumber() == cardToMatch.getNumber()) {
                    matchCards.append(card.toString()).append("\n");
                }
            } else {
                if (card.getColor() == cardToMatch.getColor() || card.getTypeCard() == TypeCard.WILD){
                    matchCards.append(card.toString()).append("\n");
                }
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
