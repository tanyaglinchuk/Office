import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 13.06.2016.
 */
public class Office {

    private static List<Employee> staff = new ArrayList<Employee>();
    private static List<Director> dirlist = new ArrayList<>();


    private static final int FIXED_COSTS = 50000;

      public static void main (String [] args)
    {
        System.out.println("Office");
        int quan;
        quan = (int) (Math.random() * 91) + 10;

        System.out.println("Количество сотрудников: " + quan);
        basicInit();
        staff = createRndStaff(staff, quan, 5);
        printStaff(staff);
        createProfLists();
        System.out.println();

        System.out.println();
        for (int i = 1; i <= 31; i++) {
            WorkDay day = new WorkDay(i);
            day.work();
        }

        List <CompleteTask> clist = WorkDay.getCompleteList();

        System.out.println("Всего выполнено заданий за месяц: " + clist.size());
        Report report = new Report(clist);
        report.make();
    }

    public static void basicInit()
    {
        Employee employee;
        employee = new Employee(1,true,false,false,false,false,false,false);
        staff.add(employee);
        employee = new Employee(2,false,true,false,false,false,false,false);
        staff.add(employee);
        employee = new Employee(3,false,false,false,false,true,false,false);
        staff.add(employee);
        employee = new Employee(4,false,false,true,false,false,false,false);
        staff.add(employee);
    }

    public static void createProfLists()
    {
        dirlist = createDirList(staff);

    }

    public static void printStaff (List<Employee> staff)
    {
        int dirnum = 0, accnum = 0, clenum = 0, desnum = 0, mannum = 0, prognum = 0, testnum = 0;
        for (Employee employee : staff)
        {
            System.out.print(Employee.getId() + " ");
            if (employee.is_director()) {
                System.out.print("Директор ");
                dirnum++;
            }
            if (employee.is_accountant()) {
                System.out.print("Бухгалтер ");
                accnum++;
            }
            if (employee.is_cleaner()) {
                System.out.print("Уборщик ");
                clenum++;
            }
            if (employee.is_designer()) {
                System.out.print("Дизайнер ");
                desnum++;
            }
            if (employee.is_manager()) {
                System.out.print("Менеджер ");
                mannum++;
            }
            if (employee.is_programmer()) {
                System.out.print("Программист ");
                prognum++;
            }
            if (employee.is_tester()) {
                System.out.print("Тестировщик");
                testnum++;
            }
            System.out.println();

        }


        System.out.println();
        System.out.println("Всего сотрудников: " + staff.size());
        System.out.println("Директоров: " + dirnum);
        System.out.println("Бухгалтеров: " + accnum);
        System.out.println("Уборщиков: " + clenum);
        System.out.println("Дизайнеров: " + desnum);
        System.out.println("Менеджеров: " + mannum);
        System.out.println("Программистов: " + prognum);
        System.out.println("Тестировщиков: " + testnum);
        System.out.println();
    }

    public static List<Employee> createRndStaff (List <Employee> staff, int quan, int start)
    {
        Employee employee;
        for (int i = start; i <= quan; i++)
        {
            int rnd = (int) (Math.random()*20);
            Profs profs = Profs.values()[rnd];
            employee = new Employee(i);
            employee.setIs_director(profs.is_director());
            employee.setIs_accountant(profs.is_accountant());
            employee.setIs_cleaner(profs.is_cleaner());
            employee.setIs_designer(profs.is_designer());
            employee.setIs_manager(profs.is_manager());
            employee.setIs_programmer(profs.is_programmer());
            employee.setIs_tester(profs.is_tester());
            staff.add(employee);
        }
        return staff;
    }

    private static List<Director> createDirList(List <Employee> staff)
    {
        List <Director> dirlist = new ArrayList<>();
        Director director;
        System.out.print("Директора: ");
        for (Employee employee : staff)
        {
            if (employee.is_director())
            {
                director = new Director(employee.getId());
                dirlist.add(director);
                System.out.print(employee.getId() + " ");
            }
        }
        System.out.println();
        return dirlist;
    }



    public static List<Director> getDirlist() {
        return dirlist;
    }

    public static List<Employee> getStaff() {
        return staff;
    }

    public static int getFixedCosts() {
        return FIXED_COSTS;
    }
}
