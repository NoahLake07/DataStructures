public abstract class Car {


    int maxSpeed = 60;
    int acceleration = 5;

    public Car(int maxSpeed, int acceleration) {
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
    }

    protected abstract void start();

    protected abstract void driving();

    protected abstract void stopping();




}
