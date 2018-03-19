package by.kolbun.andersen.hibernate_example_annotations.users_mock;

import by.kolbun.andersen.hibernate_example_annotations.BookService;
import by.kolbun.andersen.hibernate_example_annotations.HibernateExample;
import by.kolbun.andersen.hibernate_example_annotations.entity.Book;

public class User2 extends AUser {
    BookService service = new BookService();
    private int invokeCount = 0;

    public User2(String user, int i) {
        super(user, i);
    }

    @Override
    public void run() {
        while (!HibernateExample.stopThreads) {
            try {
                Thread.sleep(getDelay()/2);
                String[][] sa = new String[1][3];
                sa[0] = new String[]{"user2name", "1", "user2status"};
                String[] sb = new String[]{"user2", (1000 + invokeCount) + ""};
                int id = service.insertRecord(sb, sa);
                Thread.sleep(getDelay()/2);
                service.deleteById(id);
                incInvCount();
                System.out.println(this.getName() + " insert and delete");
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
