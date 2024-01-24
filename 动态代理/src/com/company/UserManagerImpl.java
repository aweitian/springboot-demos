package com.company;

public class UserManagerImpl implements UserManager {
    @Override
    public void addUser(String name, int age) {
        System.out.println("添加新用户：" + name + "，" + age + "岁");
    }
}
