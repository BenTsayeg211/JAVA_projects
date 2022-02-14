/**
 * a class for Basher: a ship that deliberately tries to collide with other ships.
 * Basher: This ship attempts to collide with other ships. It will always accelerate, and will
 * constantly turn towards the closest ship. If it gets within a distance of 0.19 units (inclusive)
 * from another ship, it will attempt to turn on its shields.
 */
public class BasherSpaceShip extends SpaceShip {
    //static constant/magic numbers
    //constant for the max dist when the spaceShip shilds up
    private static final double SHILD_UP_DIST = 0.19;

    /* -----=  Instance Methods  =----- */

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        //using the API of SpaceWars
        SpaceShip mostClostShip = game.getClosestShipTo(this);
        double dis =getPhysics().getDista
        double angleToMostClostShip = getPhysics().angleTO(mostClostShip.getPhysics());
        super.doAction(game)
    }
}