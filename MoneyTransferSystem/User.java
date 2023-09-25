public class User {
    private final int identifier;
    private String name;
    private int balance;

    private TransactionsLinkedList transactionsList;

    public User(String name, int balance) {
        this.identifier = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        transactionsList = new TransactionsLinkedList();
        if (balance < 0) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    public int getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public TransactionsLinkedList getTrList() {
        return transactionsList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return identifier + " " + name + " " + balance;
    }
}