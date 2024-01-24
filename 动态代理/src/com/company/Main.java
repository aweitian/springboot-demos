package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        UserManager userManager = new UserManagerImpl();
        UserManager proxy = (UserManager) new UserManagerProxy().newProxyInstance(userManager);
        proxy.addUser("test", 20);
    }
}
