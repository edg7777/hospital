package test111;

import com.fzj.pojo.User;
import com.fzj.service.UserService;
import com.fzj.service.impl.UserServiceImpl;
import com.fzj.utils.MD5;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

//告诉junit spring配置文件
@SpringJUnitConfig(locations = "classpath:Spring.xml")
public class test111 {
    @Autowired
    UserService userService;
    @Test
    public void t1(){
        User user = userService.findUserByEmail("changzhang@163.com");
        System.out.println(user.getPassword());
        String old_password="changzhang";
        System.out.println(MD5.getMD5(old_password));
    }
}
