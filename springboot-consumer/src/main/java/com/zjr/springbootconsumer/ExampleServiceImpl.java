package com.zjr.springbootconsumer;

import com.zjr.common.model.User;
import com.zjr.common.service.UserService;
import com.zjr.jrrpc.springboot.starter.annotation.RpcReference;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 示例服务实现类
 *
 */
@Service
public class ExampleServiceImpl {

    /**
     * 使用 Rpc 框架注入
     */
    @RpcReference
    private UserService userService;

    /**
     * 测试方法
     */
    public void test() throws IOException {
        User user = new User();
        user.setName("zjr");
        User resultUser = userService.getUser(user);
        System.out.println(resultUser.getName());
    }

}
