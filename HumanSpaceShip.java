import oop.ex2.*;

/**
 * a class for HumanSpaceShip- Human controlled ship (controlled by the user).
 * Controlled by the user. The keys are: left-arrow and right-arrow to
 * turn, up-arrow to accelerate, ’d’ to fire a shot, ’s’ to turn on the shield, ’a’ to teleport.
 * You can assume there will be at most one human controlled ship in a game, but you’re not
 * required to enforce this.
 */
public class HumanSpaceShip extends SpaceShip {
    //static constant/magic numbers
    /* -----=  Instance Methods  =----- */

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        GameGUI instructions = game.getGUI();
        //Advanced according to user input
        //using the API of GameGUI class
        if (instructions.isLeft)
        super.doAction(game);//complete the action
    }
}