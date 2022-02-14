import java.awt.*;

import oop.ex2.*;


/**
 * The API spaceships need to implement for the SpaceWars game.
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 * a base class for the other spaceships or any other option you will choose.
 *
 * @author student
 */
public abstract class SpaceShip {
    //static constant/magic numbers
    /**
     * constans for the health level at the begin
     */
    private static final int HEALTH_AT_THE_BEGIN = 22;
    /**
     * constans for shot damage/or collide damage(mostly 1)
     */
    private static final int NORMAL_SHOT_DAMAGE = 1;
    /**
     * constans for maximal possible amount of energy
     */
    private static final int MAX_ENERGY_LEVEL = 210;
    /**
     * constans for starting amount of energy
     */
    private static final int START_ENERGY_LEVEL = 190;
    /**
     * constant for the energy cost of shot
     */
    private static final int FIRE_SHOT_COST_FOR_ENERGY = 19;
    /**
     * constant for the energy cost of teleporting
     */
    private static final int TELEPORTING_COST = 140;
    /**
     * constant for the energy cost of shild up
     */
    private static final int SHIELD_UP_CONSUME_ENERGY = 3;
    /**
     * constant for the time we need to wait after a fire shot to shoot again
     */
    private static final int FIRE_STOP_TIME = 7;
    /**
     * constant for the energy cost of bashing
     */
    private static final int BASHING_ENERGY_COST = 18;
    /**
     * constant for the energy damage of hit.
     */
    private static final int ENERGY_HIT_DAMAGE = 10;
    /* -----= fields = -------*/
    /**
     * member that include the daya on the location and orient
     * of the space ship
     */
    private SpaceShipPhysics pysics_data;
    /**
     * member for the maximal possible energy level
     */
    private int maxEnergyLevel;
    /**
     * member for the current energy level
     */
    private int curEnergyLevel;
    /**
     * member for the current health level
     */
    private int healthLevel;
    /**
     * member that inicate if the shilds up or not
     */
    private boolean isShieldUp;
    /**
     * member for the time that left for next possible shot
     */
    private int fireStopTime;
    //more?
    /* -----=  Constructors  =----- */

    /**
     * a default constructor for SpaceShip
     */
    public SpaceShip() {
        this.pysics_data = new SpaceShipPhysics();
        this.maxEnergyLevel = MAX_ENERGY_LEVEL;
        this.curEnergyLevel = START_ENERGY_LEVEL;
        this.healthLevel = HEALTH_AT_THE_BEGIN;
        this.isShieldUp = false;
        this.fireStopTime = 0;
    }
    /* -----=  Instance Methods  =----- */

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        //change fire stop time
        if (this.fireStopTime > 0) {
            this.fireStopTime--;
        }
        //change energy
        if (this.maxEnergyLevel > this.curEnergyLevel) {
            this.curEnergyLevel += 1;
        }
    }

    /**
     * This method is called every time a collision with this ship occurs
     */
    public void collidedWithAnotherShip() {
        //double code?
        if (this.isShieldUp == false) {
            gotDamage();
        } else {
            this.curEnergyLevel += BASHING_ENERGY_COST;
            this.maxEnergyLevel += BASHING_ENERGY_COST;
        }
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset() {
        //this func will be implement a bit similiar to default contructor
        this.pysics_data = new SpaceShipPhysics();
        this.maxEnergyLevel = MAX_ENERGY_LEVEL;
        this.curEnergyLevel = START_ENERGY_LEVEL;
        this.healthLevel = HEALTH_AT_THE_BEGIN;
        this.isShieldUp = false;
        this.fireStopTime = 0;
    }

    /**
     * Checks if this ship is dead.
     *
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        if (this.healthLevel == 0) {
            return true;
        }
        return false;
    }

    /**
     * Gets the physics object that controls this ship.
     *
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return this.pysics_data;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (isShieldUp == false) {
            gotDamage();
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public Image getImage() {
        /**
         * I will implement this func by using the API of the givan
         * helper class of GameGUI
         */
        if (this.isShieldUp == false) {
            return GameGUI.ENEMY_SPACESHIP_IMAGE;
        }
        return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
    }

    /**
     * Attempts to fire a shot.
     *
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if (this.curEnergyLevel >= FIRE_SHOT_COST_FOR_ENERGY && this.fireStopTime == 0) {
            this.curEnergyLevel -= FIRE_SHOT_COST_FOR_ENERGY;
            this.fireStopTime = FIRE_STOP_TIME;
            game.addShot(this.pysics_data);
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (this.curEnergyLevel < SHIELD_UP_CONSUME_ENERGY) {
            this.isShieldUp = false;
        } else {
            this.curEnergyLevel -= SHIELD_UP_CONSUME_ENERGY;
            this.isShieldUp = true;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (this.curEnergyLevel >= TELEPORTING_COST) {
            this.curEnergyLevel -= TELEPORTING_COST;//get new location
            this.pysics_data = new SpaceShipPhysics();
        }
    }

    /**
     * helper func for gotHit() and collidedWithAnotherShip()
     */
    public void gotDamage() {
        this.maxEnergyLevel -= ENERGY_HIT_DAMAGE;
        if (this.curEnergyLevel > this.maxEnergyLevel) {
            this.curEnergyLevel = this.maxEnergyLevel;
        }
        this.healthLevel -= NORMAL_SHOT_DAMAGE;
    }
}
