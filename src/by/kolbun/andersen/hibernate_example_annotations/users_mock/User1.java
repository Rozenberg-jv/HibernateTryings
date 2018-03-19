package by.kolbun.andersen.hibernate_example_annotations.users_mock;

import by.kolbun.andersen.hibernate_example_annotations.BookService;
import by.kolbun.andersen.hibernate_example_annotations.HibernateExample;

public class User1 extends AUser {
    BookService service = new BookService();
    private int invokeCount = 0;

    public User1(String name, int delay) {
        super(name, delay);
    }

    @Override
    public void run() {
        while (!HibernateExample.stopThreads) {
            try {
                Thread.sleep(getDelay());
                service.getTable();
                incInvCount();
                System.out.println(this.getName() + " select");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(getName() + " : " + invokeCount);
    }

    public void incInvCount() {
        invokeCount++;
    }
}
