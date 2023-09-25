import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    public class Node {
        Transaction data;
        Node prev;
        Node next;

        Node(Transaction data) {
            this.data = data;
        }
    }

    private Node sentinel;
    private int size = 0;

    TransactionsLinkedList() {
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        Node newNode = new Node(transaction);
        newNode.prev = sentinel.prev;
        newNode.next = sentinel;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        ++size;
    }

    @Override
    public void removeTransaction(UUID id) {
        boolean found = false;
        Node node = sentinel.next;
        for (; node != sentinel; node = node.next) {
            if (node.data.getId().equals(id)) {
                deleteNode(node);
                found = true;
                break;
            }
        }
        if (!found)
            throw new TransactionNotFoundException("Error!");
    }

    @Override
    public Transaction[] toArray() {
        int i = 0;
        Transaction[] array = new Transaction[size];
        Node node = sentinel.next;
        while (node != sentinel) {
            array[i++] = node.data;
            node = node.next;
        }
        return array;
    }

    private void deleteNode(Node erase) {
        erase.prev.next = erase.next;
        erase.next.prev = erase.prev;
        erase = null;
        --size;
    }
}
