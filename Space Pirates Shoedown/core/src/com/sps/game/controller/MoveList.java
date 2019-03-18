package com.sps.game.controller;

import com.sps.game.screens.Fighter;

import java.util.ArrayList;
import java.util.HashMap;

public class MoveList {
    public enum MoveType {attack,defend,sneak}
    public enum MoveRange {near,far}
    public enum MoveTarget {self,enemy}

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
        movelist.put("Attack",new Move(MoveType.attack, Fighter.Stat.health, MoveRange.near, MoveTarget.enemy, -atkDefDiff,false, 0,false, true));

        movelist.put("Shield Bash", new Move(MoveType.defend, Fighter.Stat.health, MoveRange.near, MoveTarget.enemy, -(user.getDefence()), false, 0, false, true));
        movelist.put("Block",new Move(MoveType.defend, Fighter.Stat.defence, MoveRange.far, MoveTarget.self, 10, true, 3, false, false));
        movelist.put("Bravery",new Move(MoveType.defend, Fighter.Stat.attack, MoveRange.far, MoveTarget.self, 10, true, 3, false, false));
        movelist.put("Wind Speed",new Move(MoveType.defend, Fighter.Stat.speed, MoveRange.far, MoveTarget.self, 10, true, 3, false, false));
        movelist.put("Heal",new Move(MoveType.defend, Fighter.Stat.health, MoveRange.far, MoveTarget.self, 25, false, 0, false, true));
        movelist.put("Patch Up", new Move(MoveType.defend, Fighter.Stat.health, MoveRange.far, MoveTarget.self, 10, true, 3, true, true));

        movelist.put("Quick Attack",new Move(MoveType.sneak, Fighter.Stat.health, MoveRange.near, MoveTarget.enemy, -(user.getSpeed()), false, 0, false, true));
        movelist.put("Poison", new Move(MoveType.sneak, Fighter.Stat.health, MoveRange.near, MoveTarget.enemy, -(user.getSpeed() / 2), true, 3, true, true));
        movelist.put("Frighten", new Move(MoveType.sneak, Fighter.Stat.attack, MoveRange.near, MoveTarget.enemy, -10, true, 3, false, false));
        movelist.put("Weaken", new Move(MoveType.sneak, Fighter.Stat.defence, MoveRange.near, MoveTarget.enemy, -10, true, 3, false, false));
        movelist.put("Slow", new Move(MoveType.sneak, Fighter.Stat.speed, MoveRange.near, MoveTarget.enemy, -10, true, 3, false, false));
    }

    public MoveRange getMoveRange(String move){return movelist.get(move).getRange();}

    public HashMap<String,Move> getMovelist(){return movelist;}

    public void use(String move){
        Move chosenMove = movelist.get(move);
        switch(chosenMove.getTarget()){
            case self:
                chosenMove.use(user);
                break;
            case enemy:
                chosenMove.use(target);
                break;
        }
    }

    public HashMap<String,Move> getMovesHashMapByType (MoveType type){
        HashMap<String,Move> result = new HashMap<String, Move>();
        for(String moveName : movelist.keySet()){
            if(movelist.get(moveName).getType() == type)
                result.put(moveName,movelist.get(moveName));
        }
        return result;
    }

    public MoveType[] getMoveTypes(){
        return MoveType.values();
    }

    public static class Move{

        private MoveType type;
        private Fighter.Stat stat;
        private MoveRange range;
        private MoveTarget target;
        private boolean temporary;
        private int duration;
        private int difference;
        private boolean stacksOverTime;
        private boolean lingeringEffect;

        public Move(MoveType type, Fighter.Stat stat, MoveRange range, MoveTarget target, Integer difference, boolean temporary, int duration, boolean stacksOverTime, boolean lingeringEffect){
            this.type = type;
            this.stat = stat;
            this.range = range;
            this.target = target;
            this.difference = difference;
            this.temporary = temporary;
            this.duration = duration;
        }

        public void use(Fighter target){
            if(temporary) {
                target.inflictEffect(stat, difference, duration, stacksOverTime, !(lingeringEffect));
            } else {
                switch (stat) {
                    case health:
                        target.changeHealth(difference);
                        break;
                    case attack:
                        target.changeAttack(difference);
                        break;
                    case defence:
                        target.changeDefence(difference);
                        break;
                }
            }
        }

        public Fighter.Stat getStat(){return stat;}
        public MoveRange getRange(){return range;}
        public MoveTarget getTarget(){return target;}
        public MoveType getType(){return type;}
        public int getDifference(){return difference;}
    }
}
