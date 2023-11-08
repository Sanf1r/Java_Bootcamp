package ex02;

public class UsersArrayList implements UsersList {
    private Integer size = 0;
    private Integer cap = 10;
    private User[] users = new User[cap];

    @Override
    public void addUser(User add) {
        if (size == cap) {
            cap = (int) (cap * 1.5);
            User[] tmpUsers = new User[cap];
            for (Integer i = 0; i < size; ++i) {
                tmpUsers[i] = users[i];
            }
            users = tmpUsers;
        }
        users[size++] = add;
    }

    @Override
    public User getUserById(Integer id) {
        for (Integer i = 0; i < size; ++i) {
            if (id == users[i].getIdentifier()) {
                return users[i];
            }
        }
        throw new UserNotFoundException("User Not Found!");
    }

    @Override
    public User getUserByIndex(Integer index) {
        if (index >= 0 && index < size) {
            return users[index];
        }
        throw new UserNotFoundException("User Not Found!");
    }

    @Override
    public Integer numberUsers() {
        return size;
    }
}
