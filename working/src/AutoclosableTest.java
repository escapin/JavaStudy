enum Status {ON, OFF}
class Alarm implements AutoCloseable {

    private Status status;

    public Alarm(Status status) {
        this.status = status;
    }

    public void set() {
        this.status = Status.ON;
    }
    @Override
    public void close() {
        System.out.println("Executing close. The alarm is being switched off.");
        this.status = Status.OFF;
    }

    public Status getStatus() {
        return this.status;
    }
}

public class AutoclosableTest {

    public static void main(String[] args) {

        try(Alarm a = new Alarm(Status.ON)){ System.out.println(a.getStatus() + " Catched!"); a.close();}
    }
}
