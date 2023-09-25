import java.util.UUID;

public class TransactionsService {
    private UsersArrayList usersList;

    public TransactionsService() {
        usersList = new UsersArrayList();
    }

    public void addUser(User add) {
        usersList.addUser(add);
    }

    public int userBalance(int id) {
        return usersList.getUserById(id).getBalance();
    }

    public User getUser(int id) {
        return usersList.getUserById(id);
    }

    public void performTransaction(int senderId, int recipienId, int amount) {
        if (senderId == recipienId || amount <= 0 || userBalance(senderId) < amount) {
            throw new IllegalTransactionException("Illegal Transaction!");
        }

        UUID id = UUID.randomUUID();

        Transaction one = new Transaction(id, usersList.getUserById(recipienId),
                usersList.getUserById(senderId),
                Transaction.Type.DEBIT,
                -amount);
        Transaction two = new Transaction(id, usersList.getUserById(recipienId),
                usersList.getUserById(senderId),
                Transaction.Type.CREDIT,
                amount);

        usersList.getUserById(senderId).getTrList().addTransaction(one);
        usersList.getUserById(recipienId).getTrList().addTransaction(two);

    }

    public Transaction[] userTransactions(int id) {
        return usersList.getUserById(id).getTrList().toArray();
    }

    public void removeTransactions(int id, UUID trId) {
        usersList.getUserById(id).getTrList().removeTransaction(trId);
    }

    public Transaction[] checkValidity() {
        TransactionsLinkedList badList = new TransactionsLinkedList();
        for (int i = 0; i < usersList.numberUsers(); ++i) {
            for (Transaction data : usersList.getUserByIndex(i).getTrList().toArray()) {
                badList.addTransaction(data);
            }
        }

        Transaction[] arrBadList = badList.toArray();
        TransactionsLinkedList result = new TransactionsLinkedList();
        boolean found = false;

        for (int i = 0; i < arrBadList.length; ++i) {
            found = false;
            if (arrBadList[i] == null)
                continue;
            for (int j = 0; j < arrBadList.length; ++j) {
                if (i == j || arrBadList[j] == null) {
                    continue;
                }
                if (arrBadList[i].getId() == arrBadList[j].getId()) {
                    arrBadList[i] = arrBadList[j] = null;
                    found = true;
                    break;
                }
            }
            if (!found) {
                result.addTransaction(arrBadList[i]);
                arrBadList[i] = null;
            }
        }

        return result.toArray();
    }
}
