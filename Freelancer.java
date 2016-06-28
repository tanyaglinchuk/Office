import java.util.List;

/**
 * Created by Admin on 19.06.2016.
 */
public class Freelancer extends Employee {

    private int id;
    private int prof;
    private boolean is_busy = false;

    private static int freeid = 38;

    public Freelancer (int id, int prof)
    {
        this.id = id;
        this.prof = prof;
        freeid++;
        System.out.println("Новый фрилансер, сотрудник под № " + id + ", " + strProf());
    }

    public void work (Task task, List<CompleteTask> clist, int num)
    {
        System.out.println("Фрилансер № " + id + " выполнил задачу № " + task.getNum() + " ," +
                TaskNames.values()[task.getType()-1].getName());
        clist.add(new CompleteTask(task, this, num));
        this.is_busy = true;
    }

    private String strProf()
    {
        String res = "";
        switch (prof)
        {
            case 1:
                res = "бухгалтер";
                break;
            case 3:
                res = "дизайнер";
                break;
            case 4:
                res = "менеджер";
                break;
            case 5:
                res = "программист";
                break;
            case 6:
                res = "тестировщик";
                break;

        }
        return res;
    }

    @Override
    public int getId() {
        return id;
    }

    public int getProf() {
        return prof;
    }

    @Override
    public boolean is_busy() {
        return is_busy;
    }



    public static int getFreeid() {
        return freeid;
    }

    @Override
    public void setIs_busy(boolean is_busy) {
        this.is_busy = is_busy;
    }

    @Override
    public String toString() {

        String s = String.format("Фрилансер №%4d, профессия: %-20s", id, strProf());

        return s;
    }
}
