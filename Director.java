import java.util.List;

/**
 * Created by Admin on 14.06.2016.
 */
public class Director extends Employee{

    private int id;
    private boolean[][] schedule;
    private static final int ACCVALUE = 700;
    private static final int CLEVALUE = 150;
    private static final int DESVALUE = 600;
    private static final int MANVALUE = 600;
    private static final int PROGVALUE = 800;
    private static final int TESTVALUE = 500;

    public Director (int id)
    {
        this.id = id;
        this.schedule = Office.getStaff().get(id - 1).getSchedule();
    }

    public int getId ()
    {
        return id;
    }

    public boolean[][] getSchedule() {
        return schedule;
    }

    public void printSchedule()
    {
        super.printSchedule(schedule);
    }

    public Task giveTask ()
    {
        //System.out.println("Даю задание");
        int type = rndType();
        int hours = (int) (Math.random()*2) + 1;
        int priority = (int) (Math.random()*10) + 1;
        int value = getValue(type);
        Task task = new Task (type, hours, priority, value);
        return task;
        }

    private int getValue(int type)
    {
        int res = 0;
        switch (type)
        {
            case 1:
                res = ACCVALUE/2 + (int) (Math.random()*ACCVALUE/2);
                break;
            case 2:
                res = CLEVALUE/2 + (int) (Math.random()*CLEVALUE/2);
                break;
            case 3:
                res = DESVALUE/2 + (int) (Math.random()*DESVALUE/2);
                break;
            case 4:
                res = MANVALUE/2 + (int) (Math.random()*MANVALUE/2);
                break;
            case 5:
                res = PROGVALUE/2 + (int) (Math.random()*PROGVALUE/2);
                break;
            case 6:
                res = TESTVALUE/2 + (int) (Math.random()*TESTVALUE/2);
                break;
        }
        return res;
    }

    private int rndType()
    {
        int rnd = (int) (Math.random()*10);
        int type = 1;
        if (rnd == 0) type = 1;
        if (rnd == 1) type = 2;
        if ((rnd == 2) || (rnd == 3)) type = 3;
        if ((rnd == 4) || (rnd == 5)) type = 4;
        if ((rnd == 6) || (rnd == 7)) type = 5;
        if ((rnd == 8) || (rnd == 9)) type = 6;
        return type;
    }
}
