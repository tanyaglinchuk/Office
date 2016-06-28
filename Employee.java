/**
 * Created by Admin on 14.06.2016.
 */
import java.util.*;

public class Employee {

    private int id;
    private boolean is_director;
    private boolean is_accountant;
    private boolean is_manager;
    private boolean is_tester;
    private boolean is_programmer;
    private boolean is_designer;
    private boolean is_cleaner;
    private boolean is_busy;
    private boolean [][] schedule;
    private Task curTask = null;
    private int status = 0;

    public Employee (int id)
    {
        this.id = id;
        this.is_director = false;
        this.is_accountant = false;
        this.is_cleaner = false;
        this.is_designer = false;
        this.is_manager = false;
        this.is_programmer = false;
        this.is_tester = false;
        this.is_busy = false;
        this.schedule = createSchedule();
    }

    public Employee (int id, boolean is_director, boolean is_accountant, boolean is_cleaner, boolean is_designer,
                   boolean is_manager, boolean is_programmer, boolean is_tester)
    {
        this.id = id;
        this.is_director = is_director;
        this.is_accountant = is_accountant;
        this.is_cleaner = is_cleaner;
        this.is_designer = is_designer;
        this.is_manager = is_manager;
        this.is_programmer = is_programmer;
        this.is_tester = is_tester;
        this.is_busy = false;
        this.schedule = createSchedule();
    }

    public void setIs_director(boolean is_director) {
        this.is_director = is_director;
    }

    public void setIs_accountant(boolean is_accountant) {
        this.is_accountant = is_accountant;
    }

    public void setIs_manager(boolean is_manager) {
        this.is_manager = is_manager;
    }

    public void setIs_tester(boolean is_tester) {
        this.is_tester = is_tester;
    }

    public void setIs_programmer(boolean is_programmer) {
        this.is_programmer = is_programmer;
    }

    public void setIs_designer(boolean is_designer) {
        this.is_designer = is_designer;
    }

    public void setIs_cleaner(boolean is_cleaner) {
        this.is_cleaner = is_cleaner;
    }

    public void setIs_busy(boolean is_busy) {
        this.is_busy = is_busy;
    }

    public int getId ()
    {
        return id;
    }

    public boolean is_director() {
        return is_director;
    }

    public boolean is_accountant() {
        return is_accountant;
    }

    public boolean is_manager() {
        return is_manager;
    }

    public boolean is_tester() {
        return is_tester;
    }

    public boolean is_programmer() {
        return is_programmer;
    }

    public boolean is_designer() {
        return is_designer;
    }

    public boolean is_cleaner() {
        return is_cleaner;
    }

    public boolean is_busy() {
        return is_busy;
    }

    public Task getCurTask() {
        return curTask;
    }

    public int getStatus() {
        return status;
    }

    private boolean[][] createSchedule()
    {
        boolean flag = true;
        boolean[][] schedule = new boolean[7][12];
        int daylimit = 8;
        int weeklimit = 40;
        int dhours = 0, whours = 0;
        int rnd;
        while (flag) {
            schedule = new boolean[7][12];
            for (int i = 0; i < 7; i++)
            {
                dhours = 0;
                for (int j = 0; j < 12; j++)
                {
                   rnd = (int) (Math.random()*2);
                   if (rnd > 0)
                   {
                       schedule[i][j] = true;
                       dhours++;
                       whours++;
                   }
                    if (dhours==daylimit) break;
                    if (whours == weeklimit) break;
                }

                if (whours == weeklimit) break;

            }
            //System.out.println(whours);
            if (whours == weeklimit) {
                flag = false;

            } else
            {
                whours = 0;
            }

        }
        return schedule;
    }


    public void printSchedule(boolean [][] sc)
    {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 12; j++) {
                System.out.print(sc[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printSchedule()
    {
        printSchedule(schedule);
    }

    public boolean[][] getSchedule() {
        return schedule;
    }

    public void work(Task task)
    {
        System.out.println("Сотрудник № " + id +" приступил к задаче № " + task.getNum() + " ," +
        TaskNames.values()[task.getType()-1].getName());
        curTask = task;
        status = task.getHours() - 1;
    }

    public void continueWorking(Task task)
    {
        System.out.println("Сотрудник № " + id +" продолжает выполнение задачи № " + task.getNum() + " ," +
        TaskNames.values()[task.getType()-1].getName());
        status = status - 1;
    }

    public void endWork(Task task, List <CompleteTask> clist, int num)
    {
        System.out.println("Сотрудник № " + this.getId() +" выполнил задачу № " + task.getNum() + " ," +
                TaskNames.values()[task.getType()-1].getName());
        clist.add(new CompleteTask(task, this, num));
        this.curTask = null;
        this.is_busy = false;
    }

    @Override
    public String toString() {
        String s = "", prof = "";
        if (this.is_director) prof = prof + "директор ";
        if (this.is_accountant()) prof = prof + "бухгалтер ";
        if (this.is_cleaner()) prof = prof + "уборщик ";
        if (this.is_designer()) prof = prof + "дизайнер ";
        if (this.is_manager()) prof = prof + "менеджер ";
        if (this.is_programmer()) prof = prof + "программист ";
        if (this.is_tester()) prof = prof + "тестировщик ";
        s = String.format("Сотрудник №%3d, профессии: %-40s", id, prof);

        return s;
    }
}
