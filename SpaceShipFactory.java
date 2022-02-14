/**
 * a class that create new space ships
 * @author student
 */
public class SpaceShipFactory {
    /**
     * this func gets an array of string and return the appropriate space ship
     * array for this string array
     *
     * @param args
     * @return array of space ship which depends on the given string array.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        int size = args.length;
        SpaceShip[] retSpaceShipArr = new SpaceShip[size];
        for (int i = 0; i < size; i++) {
            if (args[i] == "a") {
                retSpaceShipArr[i] = new AggressiveSpaceShip();
            } else if (args[i] == "b") {
                retSpaceShipArr[i] = new BasherSpaceShip();
            } else if (args[i] == "d") {
                retSpaceShipArr[i] = new DrunkardSpaceShip();
            } else if (args[i] == "h") {
                retSpaceShipArr[i] = new HumanSpaceShip();
            } else if (args[i] == "r") {
                retSpaceShipArr[i] = new RunnerSpaceShip();
            } else if (args[i] == "s") {
                retSpaceShipArr[i] = new SpecialSpaceShip();
            }
        }
        return retSpaceShipArr;
    }
}
