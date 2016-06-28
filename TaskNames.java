/**
 * Created by Admin on 19.06.2016.
 */
public enum TaskNames {
	
    ACCOUNTING ("составить отчетность", 200), 
	CLEANING("выполнить уборку в офисе", 30), 
	DESIGNING ("рисовать макет", 200),
    MANAGING ("продавать услуги", 150), 
	PROGRAMMING ("писать код", 300), 
	TESTING ("тестировать программу", 120);

    private String name;
    private int payment;


    TaskNames(String name, int payment) {
        this.name = name;
        this.payment = payment;
    }

    public String getName() {
        return name;
    }

    public int getPayment() {
        return payment;
    }
}
