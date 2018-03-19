package by.kolbun.andersen.hibernate_example_annotations.users_mock;

public abstract class AUser implements Runnable {
    private String name;
    private int delay;

    public AUser(String name, int delay) {
        this.name = name;
        this.delay = delay;
    }

    public String getName() {
        return name;
    }

    public int getDelay() {
        return delay;
    }

}
