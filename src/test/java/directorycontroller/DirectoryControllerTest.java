package directorycontroller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uzBox.filesystem.directory.Directory;
import uzBox.filesystem.directory.DirectoryController;
import uzBox.user.User;
import uzBox.user.authorization.UserAuthorizationController;
import uzBox.user.session.authorization.SessionController;

import java.util.List;

public class DirectoryControllerTest {

    private static SessionController sessionController;
    private static DirectoryController directoryController;
    @BeforeAll
    public static void setUp() {
        User user = new User("testlogin", "testhaslo");
        UserAuthorizationController userAuthorizationController = new UserAuthorizationController(user);
        userAuthorizationController.registerUser();
        userAuthorizationController.loginUser();
        sessionController = new SessionController(userAuthorizationController.retrieveUserSession());
        directoryController = new DirectoryController(sessionController.getSessionUUID());
    }

    @AfterAll
    public static void tearDown(){
        sessionController.logoutSession();
    }

    @Test
    public void createDirectoryTest(){
        String status = directoryController.createDirectory("/test/");
        System.out.println(status);
        String nullstatus = directoryController.createDirectory("");
        System.out.println(nullstatus);
    }

    @Test
    public void deleteDirectoryTest(){
        directoryController.createDirectory("/deletedir/");
        String status = directoryController.deleteDirectory("/deletedir/");
        System.out.println(status);
    }

    @Test
    public void listDirectoryTest(){
        List<Directory> directories = directoryController.listDirectory("/");
        directories.forEach(directory -> {
            System.out.println(directory.getName());
        });
    }
}
