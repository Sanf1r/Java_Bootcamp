public class UserIdsGenerator {
    private static volatile UserIdsGenerator instance;
    private int identifier = 0;

    private UserIdsGenerator() {
    }

    public static UserIdsGenerator getInstance() {
        UserIdsGenerator localInstance = instance;
        if (localInstance == null) {
            synchronized (UserIdsGenerator.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new UserIdsGenerator();
                }
            }
        }
        return localInstance;
    }

    public int generateId() {
        return ++identifier;
    }
}
