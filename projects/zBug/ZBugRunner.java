import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;


public class ZBugRunner {
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        ZBug z = new ZBug(3);
        z.setColor(Color.ORANGE);
        world.add(new Location(5, 5), z);
        world.show();
    }
}
