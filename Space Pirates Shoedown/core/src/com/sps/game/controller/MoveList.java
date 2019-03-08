package com.sps.game.controller;

import com.sps.game.screens.Fighter;

import java.util.HashMap;

public class MoveList {
    private HashMap<String, Move> movelist;
    private Fighter user;
    private Fighter target;

    public MoveList(Fighter user, Fighter target){
        this.user = user;
        this.target = target;
        int atkDefDiff = this.user.getAttack() - this.target.getDefence();
        if(atkDefDiff <= 0)
            atkDefDiff = 0;
        movelist = new HashMap<String, Move>();
        movelist.put("basicAttack",new Move("HP","close", "enemy", -atkDefDiff));
        movelist.put("block",new Move("HP","far", "self", 10));
    }

    public String getMoveRange(String move){return movelist.get(move).getRange();}

    public void use(String move){
        Move chosenMove = movelist.get(move);
        if(chosenMove.getTarget().equals("self")){
            chosenMove.use(user);
        } else {
            chosenMove.use(target);
        }
    }

    private class Move{
        private String attribute;
        private String range;
        private String target;
        private int difference;

        public Move(String attribute, String range, String target, Integer difference){
            this.attribute = attribute;
            this.range = range;
            this.target = target;
            this.difference = difference;
        }

        public void use(Fighter target){
            if(attribute.equals("HP")){
                target.changeHP(difference);
            } else if(attribute.equals("Defence")){
                target.changeDefence(difference);
            } else if(attribute.equals("Attack")){
                target.changeDefence(difference);
            }
        }

        public String getAttribute(){return attribute;}
        public String getRange(){return range;}
        public String getTarget(){return target;}
        public int getDifference(){return difference;}
    }
}
