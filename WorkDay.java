import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 17.06.2016.
 */
public class WorkDay {

    private int number;
    private String name, dayoff;
    private boolean is_dayoff;

    private static List<Employee> staff = Office.getStaff();
    private static List<Director> dirlist = Office.getDirlist();
    private static List <Task> tasklist = new ArrayList<>();
    private static List <CompleteTask> completeList = new ArrayList<>();
    private static List <Freelancer> freelist = new ArrayList<>();
    private static int weekPayment = 0;
    private List <CompleteTask> complist;

    public WorkDay (int number)
    {
        this.number = number;
    }

    public void work()
    {

        getData();
        System.out.println("Сегодня " + name + ", " + number + " марта, " + dayoff);
        System.out.println("Работа пошла! ");
        complist = new ArrayList<>();
        int maxtasks = staff.size()/dirlist.size();
        if (maxtasks < 2)
        {
            maxtasks = 2;
        }
        int w = (number % 7) - 1;
        if (w == -1) w=6;

        //дневной цикл
        for (int h = 0; h < 12; h++) {
            System.out.println();
            int time = h + 8;
            System.out.println("Сейчас " + time + ":00.");
            System.out.println();
            System.out.println("Директора дают задания: ");
            for (Director director : dirlist) {
                if (director.getSchedule()[w][h]) {
                    int n = (int) (Math.random() * maxtasks) + 1;
                    for (int i = 0; i < n; i++) {
                        tasklist.add(director.giveTask());
                    }
                }
            }

            if (h == 11)
            {
                // сделать ежедневный отчет и выдать зарплату фрилансерам
                Task freeTask = new Task(1, 1, 10, 500);
                tasklist.add(freeTask);
            }

            if ((h == 11) && (w == 6))
            {
                // еженедельная выдача зарплаты сотрудникам
                Task weekTask = new Task(1, 1, 10, 500);
                tasklist.add(weekTask);
            }

            if ((h == 0) && (number == 31))
            {
                // ежемесячный отчет
                Task mTask = new Task(1, 2, 10, 1000);
                tasklist.add(mTask);
            }
            System.out.println("Всего заданий на данный момент: " + tasklist.size());
            System.out.println();
            for (Task task : tasklist) {
                System.out.println(task);
            }
            System.out.println();

            for (Employee employee : staff) {
                if ((Employee.getSchedule()[w][h]) && (!Employee.is_busy())) {
                    Task task = chooseTask(tasklist, Employee);
                    if (task != null) {
                        Employee.work(task);
                        Employee.setIs_busy(true);
                        tasklist.remove(task);
                    }
                }
            }

            System.out.println();
            System.out.println("Нераспределенные задачи: ");
            for (Task task : tasklist) {
                System.out.println(task);
            }
            System.out.println();

            for (Employee Employee : staff) {

                if ((Employee.getCurTask() == null) && (Employee.getSchedule()[w][h]) && (!Employee.is_director()))
                {
                    System.out.println("Сотрудник № " + Employee.getId() + " сидит без дела");
                }

                if ((Employee.getCurTask() == null) && (Employee.getSchedule()[w][h]) && (Employee.is_director()))
                {
                    System.out.println("Сотрудник № " + Employee.getId() + " руководит работой офиса");
                }

                if ((Employee.getCurTask() != null) && (Employee.getSchedule()[w][h])) {
                    if (Employee.getStatus() == 0) {
                        Employee.endWork(Employee.getCurTask(), complist, number);
                    } else {
                        Employee.continueWorking(Employee.getCurTask());
                    }
                }

                if (!Employee.getSchedule()[w][h])
                {
                    System.out.println("Сотрудник № " + Employee.getId() + " отдыхает");
                }

            }
            System.out.println();
            System.out.println("Всего выполнено заданий: " + complist.size());
            for (CompleteTask ctask : complist) {
                System.out.println(ctask);
            }

        }

        //конец дневного цикла
        System.out.println();
        System.out.println("Передача работы фрилансерам: ");
        List <Task> copy = new ArrayList<>(tasklist);
        for (Task task : copy)
        {
            boolean completed = false;
            if (task.getType() != 2)
            {
                for (Freelancer lancer : freelist)
                {
                    if ((task.getType() == lancer.getProf()) && (!lancer.is_busy()))
                    {
                       lancer.work(task, complist, number);
                       completed = true;
                       break;
                    }

                }

                if (!completed)
                {
                    Freelancer lancer = new Freelancer(Freelancer.getFreeid(), task.getType());
                    freelist.add(lancer);
                    lancer.work(task, complist, number);

                }
                tasklist.remove(task);

            }
        }
        for (Freelancer lancer : freelist)
        {
            lancer.setIs_busy(false);
        }
        System.out.println();
        System.out.println("Невыполненные задачи: ");
        for (Task task : tasklist)
        {
            System.out.println(task);
        }
        System.out.println();
        System.out.println("Всего выполнено заданий: " + complist.size());
        for (CompleteTask ctask : complist) {
            System.out.println(ctask);
        }
        completeList.addAll(complist);
        int fp = freelancerPayment(complist);
        System.out.println("Выплачено фрилансерам: " + fp);
        if ((w == 6) || (number == 31))
        {
            System.out.println("Выплачено сотрудникам: " + weekPayment);
            // фиксированная ставка выплачивается в конце месяца. так гораздо удобнее
            weekPayment = 0;
        }
        tasklist = new ArrayList<>(); //офис и так убирают каждый день
        System.out.println();
    }

    private void getData()
    {
        int i = number % 7;
        WeekDays day = WeekDays.values()[i];
        name = day.getName();
        is_dayoff = day.is_dayoff();
        if (is_dayoff)
        {
            dayoff = " выходной день.";
        } else {
            dayoff = " будний день.";
        }
    }

    private Task chooseTask (List <Task> tasklist, Employee employee)
    {
        Task task = null;
        int priority = 0;
        int value = 0;
        for (Task t : tasklist)
        {
            if (eqtype(t.getType(), Employee)) {
                if (t.getPriority() > priority) {
                    task = t;
                    priority = t.getPriority();
                    value = t.getValue();
                } else if (t.getPriority() == priority) {
                    if (t.getValue() > value) {
                        task = t;
                        priority = t.getPriority();
                        value = t.getValue();
                    }
                }
            }

        }
        return task;
    }

    private boolean eqtype (int type, Employee employee)
    {
        boolean res = false;
        if (employee.is_accountant() && (type == 1)) res = true;
        if (employee.is_cleaner() && (type == 2)) res = true;
        if (employee.is_designer() && (type == 3)) res = true;
        if (employee.is_manager() && (type == 4)) res = true;
        if (employee.is_programmer() && (type == 5)) res = true;
        if (employee.is_tester() && (type == 6)) res = true;
        return res;
    }

    private int freelancerPayment(List <CompleteTask> clist)
    {
       int total = 0;
       for (CompleteTask ctask : clist)
       {
           if  (ctask.getId() > 200)
           {
               total += ctask.getPayment();
           } else
           {
               weekPayment += ctask.getPayment();
           }
       }
       return total;
    }

    public static List<Freelancer> getFreelist() {
        return freelist;
    }

    public static List<CompleteTask> getCompleteList() {
        return completeList;
    }
}
