public class NewCar extends Car {


    public NewCar(int health, int level) {
        super(health, level);
    }

    @Override
    protected void start() {
        System.out.println("It starts flawlessly");
    }

    @Override
    protected void driving() {
        System.out.println("it goes about 120 MPH.");
    }

    @Override
    protected void stopping() {
        System.out.println("it stops with no problem at all");
    }
}
