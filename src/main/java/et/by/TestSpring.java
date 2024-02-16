package et.by;

import et.by.models.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Neil Alishev
 */
public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        User user = context.getBean("user", User.class);

        System.out.println(user.getUserID());

        context.close();
    }
}
