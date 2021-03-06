package by.kolbun.andersen.hibernate_example_annotations;


import by.kolbun.andersen.hibernate_example_annotations.users_mock.User1;
import by.kolbun.andersen.hibernate_example_annotations.users_mock.User2;
import by.kolbun.andersen.hibernate_example_annotations.users_mock.User3;
import by.kolbun.andersen.hibernate_example_annotations.users_mock.User4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HibernateExample {

    public static boolean stopThreads = false;

    public void execThreads() {

        BookService service = new BookService();
        service.clearTables();
        service.insertData(5);

        Thread t1 = new Thread(new User1("user select ", 600));
        Thread t2 = new Thread(new User2("user ins&del", 510));
        Thread t3 = new Thread(new User3("user up titl", 720));
        Thread t4 = new Thread(new User4("user up isbn", 430));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            Thread.sleep(3000);
            stopThreads = true;
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("- ! Main thread ends ! -");
        }
    }

    public void execCrud() {
        BookService service = new BookService();
//        service.clearTables();
//        service.insertData(5);

        String input;
        printLegend();
        try (BufferedReader rdr = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("choose:");
            input = rdr.readLine();

            while (!"e".equals(input)) {
                //
                switch (input) {
                    case "0":
                        printLegend();
                        break;
                    case "1":
                        service.getTable().forEach(System.out::println);
                        break;
                    case "2":
                        System.out.println("Enter book info: title, ISBN and count of authors, via spaces:");
                        String[] data = rdr.readLine().split(" ");
                        System.out.println("Enter authors information:");
                        String[][] aut_data = new String[Integer.parseInt(data[2])][];
                        for (int i = 0; i < Integer.parseInt(data[2]); i++) {
                            System.out.print((i + 1) + ": name, age and status: ");
                            aut_data[i] = rdr.readLine().split(" ");
                        }
                        service.insertRecord(data, aut_data);
                        break;
                    case "3":
                        System.out.println("Enter id and parameter [title/ISBN/author] that you want to update (via space): ");
                        String[] data2 = rdr.readLine().split(" ");
                        switch (data2[1]) {
                            case "title":
                            case "ISBN":
                            case "isbn":
                                System.out.println("Enter new value: " + data2[1]);
                                String val1 = rdr.readLine();
                                service.updateById(data2, val1);
                                break;
                            case "author":
                                System.out.println("Enter id to update: ");
                                int aId = Integer.parseInt(rdr.readLine());
                                System.out.println("Enter new values [name, age, status]: ");
                                String[] val2 = rdr.readLine().split(" ");
                                service.updateById(data2, val2, aId);
                                break;
                            default:
                                break;
                        }
                        break;
                    case "4":
                        System.out.println("Enter id of record to delete: ");
                        int id = Integer.parseInt(rdr.readLine());
                        service.deleteById(id);
                        break;
                    case "5":
                        System.out.println("Enter id of book: ");
                        int id2 = Integer.parseInt(rdr.readLine());
                        System.out.println("Enter author information[name, age, status]: ");
                        String[] s = rdr.readLine().split(" ");
                        service.addAuthor(id2, s);
                        break;
                    case "6":
                        System.out.println(service.getGeneralReport());
                        break;
                    default:
                        System.out.println("There is no such command. Try again");
                        break;

                }
                System.out.print("choose:");
                input = rdr.readLine();
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        service.finish();
    }

    private void printLegend() {
        System.out.println("List of commands:\n" +
                " * 1 : show all records\n" +
                " * 2 : create new record\n" +
                " * 3 : update record by id\n" +
                " * 4 : delete record by id\n" +
                " * 5 : add author to book\n" +
                " * 6 : get a general report\n" +
                " * 0 : print this tips again\n" +
                " * e : exit");
    }
}
