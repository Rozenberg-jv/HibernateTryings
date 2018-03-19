package by.kolbun.andersen.hibernate_example_annotations.users_mock;

import by.kolbun.andersen.hibernate_example_annotations.BookService;
import by.kolbun.andersen.hibernate_example_annotations.HibernateExample;

public class User4 extends AUser {
    BookService service = new BookService();
    private int invokeCount = 0;

    public User4(String name, int delay) {
        super(name, delay);
    }

    @Override
    public void run() {
        while (!HibernateExample.stopThreads) {
            try {
                Thread.sleep(getDelay());
                int rId = service.getRandomIdOfBooks();
                service.updateById(new String[]{String.valueOf(rId), "ISBN"}, 2000 + "" + invokeCount);
                incInvCount();
                System.out.println(this.getName() + " update ISBN");
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
