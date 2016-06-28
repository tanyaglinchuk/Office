/**
 * Created by Admin on 16.06.2016.
 */
public enum Profs {
    DIRECTOR (true, false, false, false, false, false, false),
    DIR_MANAGER (true, false, false, false, true, false, false),
    ACCOUNTANT (false, true, false, false, false, false, false),
    ACC_MANAGER (false, true, false, false, true, false, false),
    CLEANER (false, false, true, false, false, false, false),
    DESIGNER (false, false, false, true, false, false, false),
    DES_MANAGER (false, false, false, true, true, false, false),
    DES_PROGRAMMER (false, false, false, true, false, true, false),
    DES_TESTER (false, false, false, true, false, false, true),
    DES_MAN_PROG (false, false, false, true, true, true, false),
    DES_MAN_TESTER (false, false, false, true, true, false, true),
    DES_PROG_TESTER (false, false, false, true, false, true, true),
    MASTER (false, false, false, true, true, true, true),
    MANAGER (false, false, false, false, true, false, false),
    MAN_PROG (false, false, false, false, true, true, false),
    MAN_TESTER (false, false, false, false, true, false, true),
    MAN_PROG_TESTER (false, false, false, false, true, true, true),
    PROGRAMMER (false, false, false, false, false, true, false),
    TESTER (false, false, false, false, false, false, true),
    PROG_TESTER (false, false, false, false, false, true, true);

    private final boolean is_director;
    private final boolean is_accountant;
    private final boolean is_cleaner;
    private final boolean is_designer;
    private final boolean is_manager;
    private final boolean is_programmer;
    private final boolean is_tester;



    Profs(boolean is_director, boolean is_accountant, boolean is_cleaner, boolean is_designer, boolean is_manager,
          boolean is_programmer, boolean is_tester) {
        this.is_director = is_director;
        this.is_accountant = is_accountant;
        this.is_cleaner = is_cleaner;
        this.is_designer = is_designer;
        this.is_manager = is_manager;
        this.is_programmer = is_programmer;
        this.is_tester = is_tester;
    }

    public boolean is_director() {
        return is_director;
    }

    public boolean is_accountant() {
        return is_accountant;
    }

    public boolean is_cleaner() {
        return is_cleaner;
    }

    public boolean is_designer() {
        return is_designer;
    }

    public boolean is_manager() {
        return is_manager;
    }

    public boolean is_programmer() {
        return is_programmer;
    }

    public boolean is_tester() {
        return is_tester;
    }
}
