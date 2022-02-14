/**
 * a class for Runner: a ship that tries to avoid all other ships.
 * This spaceship attempts to run away from the fight. It will always accelerate, and
 * will constantly turn away from the closest ship. If the nearest ship is closer than 0.25 units,
 * and if its angle to the Runner is less than 0.23 radians, the Runner feels threatened and will
 * attempt to teleport.
 */
public class RunnerSpaceShip extends SpaceShip {
    //static constant/magic numbers
    
    /* -----=  Instance Methods  =----- */

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {

    }
}