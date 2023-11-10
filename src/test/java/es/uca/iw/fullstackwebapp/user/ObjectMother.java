package user;

public class ObjectMother {

    public static User createTestUser() {
        User testUser = new User();
        testUser.setUsername("student1");
        testUser.setEmail("student1@iw.uca.es");
        testUser.setPassword("password");
        return testUser;
    }


    public static User createTestUser(String username) {

        User testUser = new User();
        testUser.setUsername(username);
        testUser.setEmail(username + "@iw.uca.es");
        testUser.setPassword("password");
        return testUser;

    }
}
