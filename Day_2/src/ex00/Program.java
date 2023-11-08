package ex00;

public class Program {
    public static void main(String args[]) {

        User Dante = new User(1, "Dante", 5000);
        User Virgil = new User(2, "Virgil", 1000);

        Transaction tr1 = new Transaction(Dante, Virgil, Transaction.Type.DEBIT, -100);
        Transaction tr2 = new Transaction(Dante, Virgil, Transaction.Type.CREDIT, 100);

        System.out.println(tr1);
        System.out.println(tr2);
        System.out.println(Dante);
        System.out.println(Virgil);
    }
}