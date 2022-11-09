public class MyCar extends Car {


    public MyCar(int health, int level) {
        super(health, level);
    }

    @Override
    protected void start() {
        System.out.println("It takes an entire minute to start.");
    }

    @Override
    protected void driving() {
        System.out.println("it goes about 10 MPH.");
    }

    @Override
    protected void stopping() {
        System.out.println("It doesn't stop.");
    }
}
