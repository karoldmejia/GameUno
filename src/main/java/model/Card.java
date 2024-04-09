package model;

public class Card {
    private int ind;
    private ColorCard color;
    private int number;
    private TypeCard typeCard;

    public Card(int ind, ColorCard color, int number, TypeCard typeCard){
        this.ind=ind;
        this.color=color;
        this.number=number;
        this.typeCard=typeCard;
    }

    public int getInd() {
        return ind;
    }

    public ColorCard getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    public TypeCard getTypeCard() {
        return typeCard;
    }

    public void setColor(ColorCard color) {
        this.color = color;
    }

    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("Índice: ").append(ind)
                .append(", Color: ").append(color);
        if (typeCard.equals(TypeCard.CLASSIC)) {
            info.append(", Number: ").append(number);
        }
        info.append(", Type: ").append(typeCard);
        return info.toString();
    }

}
