import info.gridworld.actor.Bug;

public class DancingBug extends Bug{
    private int[] dancingArray;
    private int index;

    public DancingBug(int[] d)
    {
        dancingArray = d;
        index = 0;
    }

    public void act()
    {
        if(canMove())
            move();
        else
        {
            if(index == dancingArray.length)
                index = 0;

            for(int i = 0; i < dancingArray[index]; i++)
            {
                turn();
            }
            index++;
        }
    }

}
