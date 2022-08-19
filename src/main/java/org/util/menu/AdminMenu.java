package org.util.menu;

import org.entity.Admin;
import org.service.AdminService;
import org.util.Input;
import org.util.Print;


public class AdminMenu {
    static AdminService adminService = new AdminService();

     static void login() {
        while (true) {
            Admin admin = new Admin();
            String input = Menu.enterUsername(false);
            if(input == null)
                break;
            admin.setUsername(input);
            admin = enterPassword(admin);
            if (admin == null)
                continue;
            menu(admin);
            return;
        }
    }

    private static void menu(Admin admin) {
        Print.adminMenu();
    }

    private static Admin enterPassword(Admin admin) {
        while (true) {
            Print.enterPassword();
            String input = Input.scanner();
            if (input.equals("0"))
                return null;
            admin.setPassword(input);
            if (adminService.isExist(admin))
                return admin;
            else Print.invalidPassword();
        }
    }
}
