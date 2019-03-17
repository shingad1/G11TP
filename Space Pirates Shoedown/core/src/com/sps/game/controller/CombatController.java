<<<<<<< HEAD:Space Pirates Shoedown/core/src/com/sps/game/controller/CombatController.java
package com.sps.game.Controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.sps.game.Sprites.BasicEnemy;
import com.sps.game.Sprites.Player;
import com.sps.game.Controller.CombatSystem;

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

    private BasicEnemy enemy;
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

    public CombatController(Player p, BasicEnemy e, CombatSystem cs){
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
                    combatSystem.doMove("basicAttack");
                    break;
                case Input.Keys.W:
                    combatSystem.doMove("block");
                    break;
            }
        }
        return false;
    }

}
=======
package com.sps.game.Controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.sps.game.Sprites.BasicEnemy;
import com.sps.game.Sprites.Player;

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

    private BasicEnemy enemy;
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

    public CombatController(Player p, BasicEnemy e, CombatSystem cs){
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
                    combatSystem.doMove("basicAttack");
                    break;
                case Input.Keys.W:
                    combatSystem.doMove("block");
                    break;
            }
        }
        return false;
    }

}
>>>>>>> parent of e145baf... Merge branch 'master' into testing:Space Pirates Shoedown/core/src/com/sps/game/Controller/CombatController.java
