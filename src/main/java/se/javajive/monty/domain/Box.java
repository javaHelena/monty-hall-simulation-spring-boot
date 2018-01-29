package se.javajive.monty.domain;


import java.util.UUID;

public class Box {

    private boolean isSelected;
    boolean containsPrize;
    private String boxName;

    public Box(boolean containsPrize) {
        this.containsPrize = containsPrize;
        boxName = UUID.randomUUID().toString();
    }

    public Box(boolean containsPrize, String boxName) {
        this.containsPrize = containsPrize;
        this.boxName = boxName;

    }

    private String getContent(){
        return containsPrize ? "**** FIRST PRIZE ****" : "------- GOAT -------";
    }

    public void selectBox() {
        this.isSelected = true;
    }

    @Override
    public String toString() {
        return this.getContent();
    }



}
