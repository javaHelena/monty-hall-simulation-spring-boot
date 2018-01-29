package se.javajive.monty.domain;


import se.javajive.monty.exception.MontyHallGameException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Game {

    private final static int NUMBER_OF_BOXES = 3;
    private Map<String, Box> availableBoxes = new HashMap<>(NUMBER_OF_BOXES);


    public Game() {
        this.initBoxes();
    }

    public Map<String, Box> getAvailableBoxes() {
        return availableBoxes;
    }

    private void initBoxes() {
        // CREATE AND SHUFFLE THE BOXES
        List<Box> boxes = new ArrayList<>(NUMBER_OF_BOXES);
        Box box1 = new Box(true, "BoxOne");
        Box box2 = new Box(false, "BoxTwo");
        Box box3 = new Box(false, "BoxThree");

        boxes.add(box1);
        boxes.add(box2);
        boxes.add(box3);

        Collections.shuffle(boxes);

        // PLACE THE BOXES READY FOR THE GAME
        int i = 1;
        for (Box box : boxes) {
            availableBoxes.put(String.valueOf(i), box);
            i++;
        }
    }


    public boolean playGame(GameStrategy gameStrategy) throws MontyHallGameException {
        String firstBoxKey = pickPlayersFirstBoxKey();
        Box playersFirstBox = this.select(firstBoxKey);
        boolean playByKeeping;

        switch (gameStrategy) {
            case KEEP:
                playByKeeping = true;
                break;
            case SWITCH:
                playByKeeping = false;
                break;
            case RANDOM:
                Random r = new Random();
                playByKeeping = r.nextBoolean();
                break;
            default:
                throw new IllegalArgumentException("Incorrect game strategy.");
        }

        if (playByKeeping) {
            return playersFirstBox.containsPrize;
        } else {
            //Game removes the one picked by the player.
            availableBoxes.remove(firstBoxKey);

            //Game removes one remaining empty box:
            String remaningEmptyBoxKey = getKeyForNextEmptyBox();
            availableBoxes.remove(remaningEmptyBoxKey);

            //Open the last box
            boolean isWinner = findLastBoxContent();

            return isWinner;
        }
    }

    private boolean findLastBoxContent() throws MontyHallGameException {
        if(availableBoxes.keySet().size() == 1){
            return availableBoxes.values().iterator().next().containsPrize;
        } else {
            throw new MontyHallGameException("There are too many boxes in the game. Start over. ");
        }
    }

    private String getKeyForNextEmptyBox() {
        String remaningEmptyBoxKey = null;
        boolean stillLooking = true;
        Iterator<Map.Entry<String, Box>> iter = availableBoxes.entrySet().iterator();
        while (iter.hasNext() && stillLooking) {
            Map.Entry<String, Box> entrySet = iter.next();
            if (!entrySet.getValue().containsPrize) {
                remaningEmptyBoxKey = entrySet.getKey();
                stillLooking = false;
            }
        }
        return remaningEmptyBoxKey;
    }

    private String pickPlayersFirstBoxKey() {
        //Player picks first box - select random key number:
        int max = 3;
        int min = 1;
        Random random = new Random();
        return String.valueOf(random.nextInt(max - min + 1) + 1);
    }

    private Box select(String position) {
        Box box = this.availableBoxes.get(position);
        box.selectBox();
        return box;
    }
}
