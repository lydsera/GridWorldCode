import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;


public class DancingBugRunner {
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        int[] array = {1,2,3,4,5};
        DancingBug d = new DancingBug(array);
        world.add(new Location(0, 0), d);
        world.show();
    }
}
