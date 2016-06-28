/**
 * Created by Admin on 17.06.2016.
 */
public enum WeekDays {

    SUNDAY ("Воскресенье", true), 
	MONDAY ("Понедельник", false), 
	TUESDAY ("Вторник", false),
    WEDNESDAY("Среда", false), 
	THURSDAY ("Четверг", false), 
	FRIDAY ("Пятница", false), 
	SATURDAY ("Суббота", true);

    private String name;
    private boolean is_dayoff;

    WeekDays(String name, boolean is_dayoff) {
        this.name = name;
        this.is_dayoff = is_dayoff;
    }

    public String getName() {
        return name;
    }

    public boolean is_dayoff() {
        return is_dayoff;
    }
}
