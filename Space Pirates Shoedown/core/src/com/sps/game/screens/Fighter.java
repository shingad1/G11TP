package com.sps.game.screens;

import java.util.ArrayList;

public abstract class Fighter {

    public enum Stat {health,attack,defence,speed}

    protected int health;

    protected int attack;

    protected int defence;

    protected int speed;

    protected ArrayList<Effect> effectOverTimeList = new ArrayList<Effect>();

    public void changeHealth(int diff){health += diff;}

    public void changeAttack(int diff){
        attack += diff;
        if(attack <= 0)
            attack = 0;
    }

    public void changeDefence(int diff){
        defence += diff;
        if(defence <= 0)
            defence = 0;
    }

    public void changeSpeed(int diff){
        speed += diff;
        if(speed <= 0)
            speed = 0;
    }

    public int getAttack(){return attack;}

    public int getHealth(){return health;}

    public int getDefence(){return defence;}

    public int getSpeed(){return speed;}

    public void combatUpdate(){
        for(int i = 0; i < effectOverTimeList.size(); i++){
            if(!(effectOverTimeList.get(i).hasBeenApplied()) || effectOverTimeList.get(i).doesStackOverTime())
                applyEffect(effectOverTimeList.get(i));

            if(effectOverTimeList.get(i).update()){
                if(effectOverTimeList.get(i).isTemporary()){
                    effectOverTimeList.get(i).reverseEffect();
                    applyEffect(effectOverTimeList.get(i));
                }
                effectOverTimeList.remove(i);
                //i--;
            }
        }

    }

    public void inflictEffect(Stat status,int diff,int duration, boolean stacksOverTime, boolean temporary){
        effectOverTimeList.add(new Effect(status, diff, duration, stacksOverTime, temporary));
    }

    public void applyEffect(Effect effect){
        switch (effect.getStatus()) {
            case health:
                changeHealth(effect.getDiff());
                break;
            case defence:
                changeDefence(effect.getDiff());
                break;
            case attack:
                changeAttack(effect.getDiff());
                break;
            case speed:
                changeSpeed(effect.getDiff());
                break;
        }
    }

    private class Effect{
        private Stat status;
        private int diff;
        private int duration;
        private boolean stacksOverTime;
        private boolean temporary;
        private boolean applied;
        private int accDiff;

        public Effect(Stat status, int diff, int duration, boolean stacksOverTime, boolean temporary){
            this.status = status;
            this.diff = diff;
            this.duration = duration;
            this.stacksOverTime = stacksOverTime;
            this.temporary = temporary;
            applied = false;
            accDiff = 0;
        }

        public Stat getStatus(){return status;}

        public int getDiff(){return diff;}

        public int getDuration(){return duration;}

        public boolean doesStackOverTime(){return stacksOverTime;}

        public boolean isTemporary(){return temporary;}

        public boolean hasBeenApplied(){return applied;}

        public void applyEffect(){applied = true;}

        public void reverseEffect(){diff = -(accDiff);}

        public boolean update(){
            applyEffect();
            if(stacksOverTime || !applied)
                accDiff += diff;
            duration--;
            if(duration == 0) return true;
            return false;
        }

    }
}
