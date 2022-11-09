public class EpicCar implements Targetable, Blowupable, valuable {


    @Override
    public void selectAsTarget() {
        System.out.println("this car is a target");
    }

    @Override
    public void deselectAsTarget() {
        System.out.println("this car is not a target");
    }

    @Override
    public void selectToBlowup() {
        System.out.println("this car will blow up");
    }

    @Override
    public void deselectToBlowup() {
        System.out.println("this car will not blow up");
    }

    @Override
    public void selectAsValuable() {
        System.out.println("this car is valuable");
    }

    @Override
    public void deselectAsValuable() {
        System.out.println("this car is not valuable.");
    }
}
