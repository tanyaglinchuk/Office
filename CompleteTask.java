/**
 * Created by Admin on 19.06.2016.
 */
public class CompleteTask extends Task {

    private int num;
    private int type;
    private int hours;
    private int priority;
    private int value;
    private int id;
    private int payment;


    public CompleteTask (Task task, Employee employee, int num)
    {
        super(employee.getId(), task.getHours(), task.getPriority(), task.getValue());
        Task.number--;
        boolean is_dayoff;
        this.type = task.getType();
        this.hours = task.getHours();
        this.priority = task.getPriority();
        this.value = task.getValue();
        this.id = employee.getId();
        int i = num % 7;
        WeekDays day = WeekDays.values()[i];
        is_dayoff = day.is_dayoff();
        int mult = 1;
        if (is_dayoff) mult = 2;
        this.payment = mult*hours*TaskNames.values()[task.getType()-1].getPayment();
        this.num = num;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public int getHours() {
        return hours;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    public int getPayment() {
        return payment;
    }

    @Override
    public String toString() {
        String workType = TaskNames.values()[type -1].getName();
        String s = String.format("Дата:%2d марта;    вид работы:%24s;    рабочих часов:%1d;    " +
                "приоритет:%2d;    стоимость:%4d;    id сотрудника: %3d;    оплата:%3d ", num, workType, hours,
                priority, value, id, payment);
        return s;
    }
}
