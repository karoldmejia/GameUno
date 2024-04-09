package model;
import structures.BinaryHeap;
import structures.HashTable;
import structures.PriorityQueue;
import structures.Stack;

import java.util.Random;
import java.util.Scanner;

public class Game {
    HashTable allCards;
    Stack<Card> gameDeck;
    Stack<Card> discardDeck;
    PriorityQueue<Player> playersTurns;
    Card cardToMatch;

    public Game(){
        this.allCards=new HashTable();
        this.gameDeck=new Stack<Card>();
        this.discardDeck=new Stack<Card>();
        allCards.addCards();
    }

    Scanner scanner = new Scanner(System.in);


    public void initializeGame(int numPlayers, String[] namesPlayers){
        if (!areNumberPlayersValid(numPlayers)){
            System.out.println("Min 2 and max 5 players!");
            return;
        }
        playersTurns=new PriorityQueue<Player>(numPlayers);
        int addedCounter=0;
        Random random = new Random();
        ColorCard[] colors = ColorCard.values();
        for (int i = 0; i < numPlayers; i++) {
            Player newPlayer = new Player(namesPlayers[i]);
            playersTurns.enqueue(i, newPlayer);
            for (int j = 0; j < 7; j++) {
                Card newCard = null;
                while (newCard == null) {
                    newCard = allCards.getRandomCard(colors[random.nextInt(5)]);
                }
                newPlayer.getDeckPlayer().enqueue(newCard);
                addedCounter++;
            }
        }
        while (addedCounter<99) {
            Card newCard = allCards.getRandomCard(colors[random.nextInt(5)]);
            if (newCard!=null){
                addedCounter++;
                gameDeck.push(newCard);
            }
        }
        Card firstCard=gameDeck.peek();
        while (!(firstCard.getTypeCard().equals(TypeCard.CLASSIC))){
            gameDeck.deque(gameDeck.pop());
            firstCard=gameDeck.peek();
        }
        discardDeck.push(gameDeck.pop());
        cardToMatch=firstCard;
    }

    public void playGame() {
        boolean isPlayerEmpty = false;
        while (!isPlayerEmpty) {
            BinaryHeap<Player>.Element<Player> playerElement = playersTurns.peek();
            Player currentPlayer = playerElement.getElement();
            playerTurn(currentPlayer);
            isPlayerEmpty = currentPlayer.getDeckPlayer().isEmpty();
        }
    }


    private void playerTurn(Player currentPlayer) {
        System.out.println("It's " + currentPlayer.getName() + "'s turn!");

        Card cardToMatch = discardDeck.peek();
        System.out.println("Card to match: \n" + cardToMatch.toString());

        System.out.println("Current player's cards:");
        currentPlayer.seeCards();

        if (!currentPlayer.seeMatch(cardToMatch).isEmpty()) {
            handleMatchingCards(currentPlayer, cardToMatch);
        } else {
            handleNoMatchingCards(currentPlayer);
        }

        playersTurns.passTurn();
    }
    private void handleMatchingCards(Player currentPlayer, Card cardToMatch) {
        System.out.println("Matching cards found:");
        String matchCardsInfo = currentPlayer.seeMatch(cardToMatch);
        System.out.println(matchCardsInfo);

        Scanner scanner = new Scanner(System.in);
        int indSelectedCard;
        boolean validIndex = false;

        while (!validIndex) {
            System.out.print("Enter card's index: ");
            indSelectedCard = scanner.nextInt();
            Card selectedCard = currentPlayer.getCardByIndex(indSelectedCard);

            if (selectedCard != null && matchCardsInfo.contains(selectedCard.toString())) {
                currentPlayer.moveCardToFirstPosition(selectedCard);
                currentPlayer.getDeckPlayer().dequeue();
                discardDeck.push(selectedCard);
                validIndex = true;
            } else {
                System.out.println("Invalid index or card selection. Please try again.");
            }
        }
    }

    private void handleNoMatchingCards(Player currentPlayer) {
        System.out.println("No matching cards found. Drawing a card...");
        Card drawnCard = gameDeck.pop();
        currentPlayer.getDeckPlayer().enqueue(drawnCard);
        System.out.println("Drew a card: " + drawnCard.toString());
    }

    private boolean areNumberPlayersValid(int numPlayers){
        if (numPlayers>1 && numPlayers<=5){
            return true;
        }
        return false;
    }


}
