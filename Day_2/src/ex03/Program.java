package ex03;

import java.util.UUID;

import ex03.Transaction.Type;

public class Program {
    public static void main(String args[]) {
        TransactionsLinkedList list = new TransactionsLinkedList();

        User one = new User("One", 1000);
        User two = new User("Two", 2000);

        for (int i = 0; i < 5; ++i) {
            list.addTransaction(new Transaction(one, two, Type.CREDIT, i));
            list.addTransaction(new Transaction(one, two, Type.DEBIT, -i));
        }

        Transaction toDel = new Transaction(one, two, Type.CREDIT, 10);
        Transaction toDel2 = new Transaction(one, two, Type.DEBIT, -10);

        list.addTransaction(toDel);
        list.addTransaction(toDel2);

        Transaction[] result = list.toArray();

        for (int i = 0; i < list.getSize(); ++i) {
            System.out.println(result[i]);
        }

        System.out.println(one);
        System.out.println(two);

        list.removeTransaction(toDel.getId());
        list.removeTransaction(toDel2.getId());

        result = list.toArray();

        for (int i = 0; i < list.getSize(); ++i) {
            System.out.println(result[i]);
        }

        try {
            list.removeTransaction(UUID.randomUUID());
        } catch (TransactionNotFoundException e) {
            System.out.println("Error with ID");
        }
    }
}