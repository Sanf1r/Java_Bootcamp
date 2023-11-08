package ex05;

public class UsersArrayList implements UsersList {
    private int size = 0;
    private int cap = 10;
    private User[] users = new User[cap];

    @Override
    public void addUser(User add) {
        if (size == cap) {
            cap = (int) (cap * 1.5);
            User[] tmpUsers = new User[cap];
            for (int i = 0; i < size; ++i) {
                tmpUsers[i] = users[i];
            }
            users = tmpUsers;
        }
        users[size++] = add;
    }

    @Override
    public User getUserById(int id) {
        for (int i = 0; i < size; ++i) {
            if (id == users[i].getIdentifier()) {
                return users[i];
            }
        }
        throw new UserNotFoundException("User Not Found!");
    }

    @Override
    public User getUserByIndex(int index) {
        if (index >= 0 && index < size) {
            return users[index];
        }
        throw new UserNotFoundException("User Not Found!");
    }

    @Override
    public int numberUsers() {
        return size;
    }
}
