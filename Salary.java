/**
 * Created by Admin on 20.06.2016.
 */
public enum Salary {
    DIRECTOR (10000), 
	ACCOUNTANT(4000), 
	CLEANER(0), 
	DESIGNER(0), 
	MANAGER(2000), 
	PROGRAMMER(0), 
	TESTER(0);

    private int salary;

    Salary(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }
}
