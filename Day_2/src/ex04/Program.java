package ex04;

public class Program {
    public static void main(String args[]) {
        TransactionsService service = new TransactionsService();

        User one = new User("One", 1000);
        User two = new User("Two", 2000);

        service.addUser(one);
        service.addUser(two);

        service.performTransaction(1, 2, 200);
        service.performTransaction(1, 2, 300);

        System.out.println(one);
        System.out.println(two);

        System.out.println("Before");
        System.out.println("One transactions");
        for (Transaction data : one.getTrList().toArray()) {
            System.out.println(data);
        }
        System.out.println("------------------");
        System.out.println("Two transactions");
        for (Transaction data : two.getTrList().toArray()) {
            System.out.println(data);
        }

        two.getTrList().removeTransaction(two.getTrList().toArray()[1].getId());

        System.out.println("After");
        System.out.println("One transactions");
        for (Transaction data : one.getTrList().toArray()) {
            System.out.println(data);
        }
        System.out.println("------------------");
        System.out.println("Two transactions");
        for (Transaction data : two.getTrList().toArray()) {
            System.out.println(data);
        }

        System.out.println("Not valid transaction");
        for (Transaction data : service.checkValidity()) {
            System.out.println(data);
        }
    }
}