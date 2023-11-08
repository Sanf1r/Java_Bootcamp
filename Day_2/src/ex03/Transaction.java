package ex03;

import java.util.UUID;

public class Transaction {
    private UUID identifier;
    private User recipient;
    private User sender;
    private Type transferCategory;
    private Integer transferAmount;

    enum Type {
        DEBIT,
        CREDIT
    }

    public Transaction(User recipient, User sender, Type transferCategory, Integer transferAmount) {
        this.identifier = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transferCategory = transferCategory;
        checkingTransfer(transferAmount);
    }

    public void checkingTransfer(Integer transferAmount) {
        if (transferCategory == Type.DEBIT &&
                (sender.getBalance() < -1 * transferAmount || transferAmount > 0)) {
            this.transferAmount = 0;
        } else if (transferCategory == Type.CREDIT &&
                (sender.getBalance() < transferAmount || transferAmount < 0)) {
            this.transferAmount = 0;
        } else {
            if (transferCategory == Type.DEBIT) {
                sender.setBalance(sender.getBalance() + transferAmount);
            } else {
                recipient.setBalance(recipient.getBalance() + transferAmount);
            }
            this.transferAmount = transferAmount;
        }
    }

    public UUID getId() {
        return identifier;
    }

    @Override
    public String toString() {
        return identifier + " " + recipient.getName() + " " + sender.getName() + " " + transferCategory + " "
                + transferAmount;
    }
}