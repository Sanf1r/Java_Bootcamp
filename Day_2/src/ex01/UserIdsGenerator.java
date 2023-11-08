package ex01;

public class UserIdsGenerator {
    private static volatile UserIdsGenerator instance;
    private Integer identifier = 0;

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

    public Integer generateId() {
        return ++identifier;
    }
}
