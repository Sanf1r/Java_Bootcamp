package ex02;

public class Program {
    public static void main(String args[]) {
        UsersArrayList list = new UsersArrayList();

        for (int i = 0; i < 17; ++i) {
            list.addUser(new User("User No - " + i, 10 * i));
        }

        for (int i = 0; i < list.numberUsers(); ++i) {
            System.out.println(list.getUserByIndex(i));
        }

        try {
            list.getUserById(50);
        } catch (UserNotFoundException e) {
            System.out.println("Error with ID");
        }

        try {
            list.getUserByIndex(50);
        } catch (UserNotFoundException e) {
            System.out.println("Error with index");
        }
    }
}