import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

import java.util.Locale;

public class ZBug extends Bug{
    private int steps;
    private int sideLength;
    private int count;

    public ZBug(int length)
    {
        setDirection(Location.EAST);
        steps = 0;
        sideLength = length;
        count = 0;
    }

    public void act()
    {
        if(steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else if(steps == sideLength)
        {
            if(count == 0)
            {
                setDirection((Location.SOUTHWEST));
                steps = 0;
                count++;
            }
            else if(count == 1)
            {
                steps = 0;
                setDirection(Location.EAST);
                count++;
            }
        }
    }


}
