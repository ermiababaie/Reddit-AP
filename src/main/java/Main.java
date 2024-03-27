import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        runMenu();

    }
    public static void runMenu() {
        System.out.println("enter cancel for cancel :)");
        Scanner in = new Scanner(System.in);
        int inputNumber = 0;
        while (true) {
            while (inputNumber <= 0 || inputNumber >= 3) {
                System.out.println("select one: 1)login 2)signUp");
                inputNumber = in.nextInt();
            }
            if (inputNumber == 1) {
                Account account = Reddit.logIn();
                if (!account.getEmail().equals("LOGINfaild")) {
                    while (true) {
                        Reddit.timeLine(account.getAccountUUID());
                        inputNumber = 0;
                       while (inputNumber <= 0 || inputNumber >= 4) {
                           System.out.println("select one : 1)newPost 2)NewComment 3)newSubReddit 4)logOut 5)Search 6)editProfile 7)deletePost 8)newAdmin");
                           inputNumber = in.nextInt();
                           if (inputNumber == 1) {
                                Reddit.newPost(account.getAccountUUID());
                           }
                           else if (inputNumber == 2) {
                               Reddit.newComment(account.getAccountUUID());
                           }
                           else if (inputNumber == 3) {
                               Reddit.newSubReddit(account.getAccountUUID());
                           }
                           else if (inputNumber == 4) {
                               break;
                           }
                            else if (inputNumber == 5) {
                                Reddit.Search(account.getAccountUUID());
                           }
                            else if (inputNumber == 6) {
                                Reddit.editProfile(account.getAccountUUID());
                           }
                            else if (inputNumber == 7) {
                                Reddit.deletePost(account.getAccountUUID());
                           }
                            else if (inputNumber == 8) {
                                Reddit.newAdmin(account.getAccountUUID());
                           }
                       }
                    }
                }
                else {
                    System.out.println("Login faild");
                }
            }
            else {
                Reddit.newAccount();
            }
        }
    }
}