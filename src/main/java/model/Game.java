package model;
import java.util.Random;
import java.util.Scanner;

public class Game {
    HashTable allCards;
    Stack gameDeck;
    Stack discardDeck;
    PriorityQueue<Player> playersTurns;
    Card cardToMatch;

    public Game(){
        this.allCards=new HashTable();
        this.gameDeck=new Stack();
        this.discardDeck=new Stack();
        allCards.addCards();
    }

    Scanner scanner = new Scanner(System.in);


    public void initializeGame(int numPlayers, String[] namesPlayers){
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
        Scanner scanner = new Scanner(System.in);
        boolean isPlayerEmpty = false;
        while (!isPlayerEmpty) {
            BinaryHeap<Player>.Element<Player> playerElement = playersTurns.peek();
            Player currentPlayer = playerElement.getElement();
            System.out.println("It's " + currentPlayer.getName() + "'s turn!");

            Card cardToMatch = discardDeck.peek();
            System.out.println("Card to match: \n" + cardToMatch.getCardInfo());

            System.out.println("Current player's cards:");
            currentPlayer.seeCards();

            if (!currentPlayer.seeMatch(cardToMatch).isEmpty()) {
                System.out.println("Matching cards found:");
                System.out.println(currentPlayer.seeMatch(cardToMatch));
                System.out.println("Enter the index of the card you want to select: ");
                int indSelectedCard = scanner.nextInt();
                Card selectedCard = currentPlayer.getCardByIndex(indSelectedCard);
                if (selectedCard != null) {
                    currentPlayer.moveCardToFirstPosition(selectedCard);
                    currentPlayer.getDeckPlayer().dequeue();
                    discardDeck.push(selectedCard);
                }
            } else {
                System.out.println("No matching cards found. Drawing a card...");
                Card drawnCard = gameDeck.pop();
                currentPlayer.getDeckPlayer().enqueue(drawnCard);
                System.out.println("Drew a card: " + drawnCard.getCardInfo());
            }
            playersTurns.passTurn();
            isPlayerEmpty = currentPlayer.getDeckPlayer().isEmpty();
        }
    }

}
