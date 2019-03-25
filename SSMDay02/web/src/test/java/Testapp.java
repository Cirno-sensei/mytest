import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Testapp {
        @Test
        public void test(){
            // 加密器
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String str = encoder.encode("123456");
            System.out.println(str);
            boolean flag = encoder.matches("123456","$2a$10$Rtm/uHScMXTkXHWRNnH2J..5DEYdFlFdbW3S/Mpwhac3O.FGx.M02");
            System.out.println(flag);

        }
}
