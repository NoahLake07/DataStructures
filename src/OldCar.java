public class OldCar extends Car {


    public OldCar(int health, int level) {
        super(health, level);
    }

    @Override
    protected void start() {
        System.out.println("the old car has some problems starting.");
    }

    @Override
    protected void driving() {
        System.out.println("it goes about 50 MPH.");
    }

    @Override
    protected void stopping() {
        System.out.println("it takes a couple seconds to stop.");
    }
}

