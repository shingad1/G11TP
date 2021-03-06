package com.sps.game.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.sps.game.sprites.AbstractEnemy;
import com.sps.game.sprites.BasicEnemy;
import com.sps.game.sprites.Player;

/**
 * This class creates the combat system page where the battle takes place
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */

public class CombatController extends InputAdapter
{

    private Player player;
    /**
     * Creates instance of the player, which can be used in order to update the player health
     * @see #keyDown
     */

    private AbstractEnemy enemy;
    /**
     * Creates instance of the enemy, sets the enemy parameter into this local variable
     * @see #CombatController
     */

    private CombatSystem combatSystem;
    /**
     * Creates instance of the combat system, which is used as a battle field for the player and enemy
     * @see #keyDown
     */

    private int keyPressed;
    /**
     * Creates an int variable named keyPressed which is used to detect the keycode the player has used
     * @see #keyDown
     */

    public CombatController(Player p, AbstractEnemy e, CombatSystem cs){
        this.player = p;
        this.enemy = e;
        combatSystem = cs;
    }

    /**
     * Checks to see what the user inputs, then based on that it performs different things, if the key Q is pressed the attack method
     * is called, if the key W is pressed then the basic player block method is called
     * @param <code>int</code>keycode
     * @return <code>Boolean</code>
     */
    @Override
    public boolean keyDown(int keycode){
        if(combatSystem.getPlayerTurn())
        {//was activated
            keyPressed = keycode;
            switch(keycode)
            {
                case Input.Keys.Q:
                    combatSystem.doInput(CombatSystem.Inputs.Q, true);
                    break;
                case Input.Keys.W:
                    combatSystem.doInput(CombatSystem.Inputs.W, true);
                    break;
                case Input.Keys.E:
                    combatSystem.doInput(CombatSystem.Inputs.E, true);
                    break;
                case Input.Keys.R:
                    combatSystem.doInput(CombatSystem.Inputs.R, true);
                    break;
                case Input.Keys.A:
                    combatSystem.doInput(CombatSystem.Inputs.A, true);
                    break;
                case Input.Keys.S:
                    combatSystem.doInput(CombatSystem.Inputs.S, true);
                    break;
                case Input.Keys.D:
                    combatSystem.doInput(CombatSystem.Inputs.D, true);
                    break;
            }
        }
        return false;
    }

}
