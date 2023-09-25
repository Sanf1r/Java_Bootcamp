public interface UsersList {
    public void addUser(User add);

    public User getUserById(int id);

    public User getUserByIndex(int index);

    public int numberUsers();
}
