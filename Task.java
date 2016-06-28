/**
 * Created by Admin on 14.06.2016.
 */
public class Task {

    private int type;
    private int hours;
    private int priority;
    private int value;
    private int num;
    protected static int number = 0;

    public Task(int type, int hours, int priority, int value) {
        this.type = type;
        this.hours = hours;
        this.priority = priority;
        this.value = value;
        number++;
        this.num = number;
    }

    public int getType() {
        return type;
    }

    public int getHours() {
        return hours;
    }

    public int getPriority() {
        return priority;
    }

    public int getValue() {
        return value;
    }

    public int getNum() {
        return num;
    }

    public static int getNumber() {
        return number;
    }

    private String strtype(int type)
    {
        String res = "";
        switch (type)
        {
            case 1:
                res = "составить отчетность";
                break;
            case 2:
                res = "выполнить уборку в офисе";
                break;
            case 3:
                res = "рисовать макет";
                break;
            case 4:
                res = "продавать услуги";
                break;
            case 5:
                res = "писать код";
                break;
            case 6:
                res = "тестировать программу";
                break;
        }
        return res;
    }



    @Override
    public String toString() {
        return "Task № " + num + " {" +
                "type=" + strtype(type) +
                ", hours=" + hours +
                ", priority=" + priority +
                ", value=" + value +
                '}';
    }
}
