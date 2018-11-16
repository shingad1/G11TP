package com.sps.game.dialogue;

import java.util.HashMap;

public class Dialogue {

    private HashMap<Integer, DialogueNode> nodes = new HashMap<Integer, DialogueNode>();

    public DialogueNode getNode(int id){
        return nodes.get(id);
    }

    public void addNode(DialogueNode node){
        this.nodes.put(node.getID(), node);
    }

    public int getStart(){//getting the first bit of dialogue e.g. "hello"
        return 0;
    }

    public int size(){
        return nodes.size();
    }
}
