package student_player;

import Saboteur.SaboteurBoard;
import Saboteur.SaboteurBoardState;
import Saboteur.cardClasses.SaboteurCard;
import Saboteur.cardClasses.SaboteurTile;
import java.util.*;


public class MyTools {

    public static final int originPos = 5;

    private ArrayList<Integer> y_goal;
    private Map<String,Integer> dead_ends; // {"1","2","2_flip","3","3_flip","11","11_flip","13","14","14_flip","15"};
    private Map<String,Integer> good_cards;

    public MyTools() {
        y_goal = new ArrayList<Integer>();
        y_goal.add(originPos - 2);
        y_goal.add(originPos);
        y_goal.add(originPos + 2);

        dead_ends = new HashMap<String, Integer>();
        dead_ends.put("1", 0);
        dead_ends.put("2", 0);
        dead_ends.put("2_flip", 0);
        dead_ends.put("3", 0);
        dead_ends.put("3_flip", 0);
        dead_ends.put("4", 0);
        dead_ends.put("4_flip", 0);
        dead_ends.put("11", 0);
        dead_ends.put("11_flip", 0);
        dead_ends.put("12", 0);
        dead_ends.put("12_flip", 0);
        dead_ends.put("13", 0);
        dead_ends.put("14", 0);
        dead_ends.put("14_flip", 0);
        dead_ends.put("15", 0);

        good_cards = new HashMap<String, Integer>();
        good_cards.put("0", 0);
        good_cards.put("5", 0);
        good_cards.put("5_flip", 0);
        good_cards.put("6", 0);
        good_cards.put("6_flip", 0);
        good_cards.put("7", 0);
        good_cards.put("7_flip", 0);
        good_cards.put("8", 0);
        good_cards.put("9", 0);
        good_cards.put("9_flip", 0);
        good_cards.put("10", 0);
    }

    public int getRandomGoalPosition(){
        if(y_goal.size() > 0) {
            Random startRand = new Random();
            int idx = startRand.nextInt(y_goal.size());
            return y_goal.remove(idx);
        }
        return -1;
    }

    public int getWorstCard(SaboteurBoardState bs, String list){
        int index = -1;
        int min_value = Integer.MAX_VALUE;
        Map<String,Integer> cards = new HashMap<String,Integer>();

        if(list.equals("dead_end")){
            cards.putAll(dead_ends);
        }
        else if (list.equals("good_card")){
            cards.putAll(good_cards);
        }
        else{
            return index;
        }
        calculatePossiblePositions(bs,cards);
        for(int i = 0; i < bs.getCurrentPlayerCards().size(); i++){
            String name = bs.getCurrentPlayerCards().get(i).getName();
            if(name.contains("Tile:")){
                name = name.split("Tile:")[1];
            }
            if(cards.containsKey(name)){
                if(cards.get(name) < min_value) {
                    min_value = cards.get(name);
                    index = i;
                }
                if(cards.containsKey(name+"_flip")){
                    if(cards.get(name+"_flip") < min_value) {
                        min_value = cards.get(name);
                        index = i;
                    }
                }
            }
        }
        return index;
    }

    public int checkDistance(SaboteurTile tile, SaboteurTile goal){
        return 0;
    }

    private void calculatePossiblePositions(SaboteurBoardState bs, Map<String,Integer> list_cards){
        for(Map.Entry<String,Integer> entry : list_cards.entrySet()){
            SaboteurTile tile = new SaboteurTile(entry.getKey());
            int numPositions = bs.possiblePositions(tile).size();
            entry.setValue(numPositions);
            System.out.println("Entry : " + entry.getKey() + " | Value : " + entry.getValue());
        }
    }

}