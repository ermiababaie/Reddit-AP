import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reddit {
    static List<Post> postList = new ArrayList<>();
    static List<Account> accountList = new ArrayList<>();
    static List<Comment> commentList = new ArrayList<>();
    static List<SubReddit> subRedditList = new ArrayList<>();


    public static String hash(String pas) {
        String pass = pas + "This Is Salt For My hash";
        long mod = 1000000007, mod2 = 1000000009, mabna = 457, mabna2 = 701;
        long ans = 0, ans2 = 0, pow = 1, pow2 = 1;
        for (int i = 0; i < pass.length(); i++) {
            int save = pass.charAt(i) - ' ';
            ans = (ans + (pow * save)) % mod;
            pow = (pow * mabna) % mod;
            ans2 = (ans2 + (pow2 * save)) % mod2;
            pow2 = (pow2 * mabna2) % mod2;

        }
        return String.valueOf(ans) + "#" + String.valueOf(ans2);
    }

    public static int validEmail(String email) {
//        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}";
        String regex = "[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]{2,4}";
        String regex2 = "[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]{5,}";
        //need to change
        Pattern pattern = Pattern.compile(regex);
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher = pattern.matcher(email);
        Matcher matcher2 = pattern2.matcher(email);
        if (!matcher.find() || matcher2.find()) {
            return 0;
        }
        boolean find = false;
        for (int i = 0; i < accountList.size(); i++) {
            find |= accountList.get(i).getEmail().equals(email);
        }
        if (find) {
            return 1;
        }
        else {
            return 2;
        }
    }
    public static int validUserName(String userName) {
        String regex = "[a-zA-Z]+";
        String regex2 = "[0-9]+[a-zA-Z]+";
        //need to change
        Pattern pattern = Pattern.compile(regex);
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher = pattern.matcher(userName);
        Matcher matcher2 = pattern2.matcher(userName);
        if (!matcher.find() || matcher2.find()) {
            return 0;
        }
        boolean find = false;
        for (int i = 0; i < accountList.size(); i++) {
            find |= accountList.get(i).getUserName().equals("u/" + userName);
        }
        if (find) {
            return 1;
        }
        else {
            return 2;
        }
    }
    public static int validSubReddit(String SubReditName) {
        String regex = "[a-zA-Z]+";
        String regex2 = "[0-9]+[a-zA-Z]+";
        //need to change
        Pattern pattern = Pattern.compile(regex);
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher = pattern.matcher(SubReditName);
        Matcher matcher2 = pattern2.matcher(SubReditName);
        if (!matcher.find() || matcher2.find()) {
            return 0;
        }
        boolean find = false;
        for (int i = 0; i < subRedditList.size(); i++) {
            find |= subRedditList.get(i).getSubRedditName().equals(SubReditName);
        }
        if (find) {
            return 1;
        }
        else {
            return 2;
        }
    }
    public static int validTag(String tag) {
        String regex = "[a-zA-Z]+";
        //need to change
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(tag);
        if (!matcher.find()) {
            return 0;
        }
        else {
            return 1;
        }
    }

    public static Post getPostViaUUID(UUID uuid) {
        for (int i = 0; i < postList.size(); i++) {
            if (postList.get(i).getPostUUID().equals(uuid)) {
                return postList.get(i);
            }
        }
        return postList.get(0);
    }
    public static Post getPostViaNumber(int number) {
        for (int i = 0; i < postList.size(); i++) {
            if (postList.get(i).getPostNumber() == number) {
                return postList.get(i);
            }
        }
        return postList.get(0);
    }
    public static Account getAccountViaUUID(UUID uuid) {
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getAccountUUID().equals(uuid)) {
                return accountList.get(i);
            }
        }
        return accountList.get(0);
    }

    public static SubReddit getSubRedditViaUUID(UUID uuid) {
        for (int i = 0; i < subRedditList.size(); i++) {
            if (subRedditList.get(i).getSubRedditUUID().equals(uuid)) {
                return subRedditList.get(i);
            }
        }
        return subRedditList.get(0);
    }
    public static Comment getCommentViaUUID(UUID uuid) {
        for (int i = 0; i < commentList.size(); i++) {
            if (commentList.get(i).getCommentUUID().equals(uuid)) {
                return commentList.get(i);
            }
        }
        return commentList.get(0);
    }

    public static void newAccount() {
        Scanner in = new Scanner(System.in);
        Account account = new Account("", "", "");
        String userEmail = "";
        while (true) {
            System.out.print("enter your email: ");
            userEmail = in.next();
            if (userEmail.equals("cancel")) {
                break;
            }
            else if (validEmail(userEmail) == 0) {
                System.out.println("this is not valid email!");
            }
            else if (validEmail(userEmail) == 1) {
                System.out.println("this email already exist");
            }
            else {
                break;
            }
        }
        if (!userEmail.equals("cancel")) {
            System.out.println("enter PassWord: ");
            String passWord = in.next();
            while (true) {
                System.out.println("enter userName: ");
                String UserName = in.next();
                if (UserName.equals("cancel")) {
                    break;
                }
                else if (validUserName(UserName) == 0) {
                    System.out.println("this is not valid UserName!");
                }
                else if (validEmail(userEmail) == 1) {
                    System.out.println("this UserName already exist");
                }
                else {
                    account.setPassWord(passWord);
                    account.setEmail(userEmail);
                    account.setUserName(UserName);
                    break;
                }
            }
        }
        if (!account.getEmail().equals("")) {
            accountList.add(account);
        }
    }
    public static void newComment(UUID accountUUID) {
        Scanner in = new Scanner(System.in);
        Scanner in2 = new Scanner(System.in);
        Scanner in3 = new Scanner(System.in);
        Scanner in4 = new Scanner(System.in);
        Scanner in5 = new Scanner(System.in);
        Account account = getAccountViaUUID(accountUUID);
        System.out.println("enter post number : ");
        int postNumber = in.nextInt();
        if (postNumber != 0) {
            while (postNumber > Post.getPostCounter()) {
                System.out.println("enter valid number.");
                System.out.println("enter post number : ");
                postNumber = in2.nextInt();
                if (postNumber == 0) {
                    break;
                }
            }
            if (postNumber != 0) {
                System.out.println("enter text of comment: ");
                String Text = "";
                Text = in3.nextLine();
                Post post = getPostViaNumber(postNumber);
                Comment comment = new Comment(accountUUID, Text, post.getPostUUID());
                for (int i = 0; i < postList.size(); i++) {
                    if (postList.get(i).getPostNumber() == postNumber) {
                        postList.get(i).addComment(comment.getCommentUUID());
                    }
                }
                commentList.add(comment);
                account.addComment(comment.getCommentUUID());
                for (int i = 0; i < accountList.size(); i++) {
                    if (accountList.get(i).getAccountUUID().equals(account.getAccountUUID())) {
                        accountList.set(i, account);
                    }
                }
            }
        }
    }
    public static void newPost(UUID accountUUID) {
        Scanner in = new Scanner(System.in);
        Scanner in2 = new Scanner(System.in);
        Scanner in3 = new Scanner(System.in);
        Scanner in4 = new Scanner(System.in);
        Scanner in5 = new Scanner(System.in);
        Account account = getAccountViaUUID(accountUUID);
        System.out.println("Your subReddits: ");
        System.out.print("\t[");
        for (int i = 0; i < account.getSubRedditList().size(); i++) {
            if (i < account.getSubRedditList().size() - 1) {
                System.out.print(getSubRedditViaUUID(account.getSubRedditList().get(i)).getSubRedditName() + ", ");
            }
            else {
                System.out.print(getSubRedditViaUUID(account.getSubRedditList().get(i)).getSubRedditName());
            }
        }
        System.out.println("]");
        while (true) {
            System.out.print("Select SubReddit : ");
            String subRedditName = in.next();
            if (subRedditName.equals("cancel")) {
                break;
            }
            boolean find = false;
            for (int i = 0; i < account.getSubRedditList().size(); i++) {
                find |= getSubRedditViaUUID(account.getSubRedditList().get(i)).getSubRedditName().equals(subRedditName);
            }
            if (find) {
                System.out.print("Enter PostTitle: ");
                String postTitle = "";
                postTitle = in2.nextLine();
                System.out.print("Enter PostBody: ");
                String postBody = "";
                postBody = in3.nextLine();
                UUID subRedditUUID = UUID.randomUUID();
                for (int i = 0; i < subRedditList.size(); i++) {
                    if (subRedditList.get(i).getSubRedditName().equals(subRedditName))
                        subRedditUUID = subRedditList.get(i).getSubRedditUUID();
                }
                Post post = new Post(subRedditUUID, accountUUID, postTitle, postBody);
                System.out.print("enter Number of tags: ");
                int tagCnt = in4.nextInt();
                while (tagCnt >= 1) {
                    String tag = "";
                    while (true) {
                        System.out.println("Enter tags + enterKEY : ");
                        tag = in5.next();
                        if (validTag(tag) == 0) {
                            System.out.println("this is not valid tag");
                        }
                        else {
                            break;
                        }
                    }
                    post.addTag(tag);
                    tagCnt--;
                }
                postList.add(post);
                for (int i = 0; i < accountList.size(); i++) {
                    if (accountList.get(i).getAccountUUID().equals(account.getAccountUUID())) {
                        accountList.get(i).addPost(post.getPostUUID());
                    }
                }
                for (int i = 0; i < subRedditList.size(); i++) {
                    if (subRedditList.get(i).getSubRedditName().equals(subRedditName)) {
                        subRedditList.get(i).addPost(post.getPostUUID());
                    }
                }
                break;
            }
            else {
                System.out.println("pleas enter correct subReddit.");
            }
        }
    }
    public static void newSubReddit(UUID accountUUID) {
        Scanner in = new Scanner(System.in);
        Account account = getAccountViaUUID(accountUUID);
        while (true) {
            System.out.print("Enter SubReddit name: ");
            String SubRedditName = in.next();
            if (SubRedditName.equals("cancel")) {
                break;
            }
            else if (validSubReddit(SubRedditName) == 0) {
                System.out.println("this is not valid SubReddit name.");
            }
            else if (validSubReddit(SubRedditName) == 1) {
                System.out.println("this SuReddit already exist.");
            }
            else {
                SubReddit subReddit = new SubReddit(SubRedditName, accountUUID);
                subRedditList.add(subReddit);
                for (int i = 0; i < accountList.size(); i++) {
                    if (accountList.get(i).getAccountUUID().equals(accountUUID)) {
                        accountList.get(i).addSubReddit(subReddit.getSubRedditUUID());
                    }
                }
                break;
            }
        }
    }
    public static void newPost(Post post) {
        postList.add(post);
    }
    public static void viewComment(UUID commentUUID) {
        Comment comment = getCommentViaUUID(commentUUID);
        System.out.println("\t\t" + getCommentViaUUID(commentUUID).getCommentNumber() + ") " + getAccountViaUUID(comment.getUser()).getUserName() + ":");
        System.out.println("\t\t\t" + comment.getText());
        System.out.println("\t\t\t\t karma: " + comment.getVote());
    }
    public static void viewPost(UUID postUUID) {
        Post post = getPostViaUUID(postUUID);
        System.out.print(post.getPostNumber() + " ) ");
        System.out.print(getSubRedditViaUUID(post.getSubReddit()).getSubRedditName() + ", ");
        System.out.print(getAccountViaUUID(post.getUser()).getUserName() + " -");
        System.out.println(post.getTitle() + "- :");
        System.out.println("\t" + post.getBody());
        System.out.print("\t\t");
        for (int i = 0; i < post.getTags().size(); i++) {
            if (i < post.getTags().size() - 1) {
                System.out.print(post.getTags().get(i) + " - ");
            }
            else {
                System.out.print(post.getTags().get(i));

            }
        }
        System.out.println("\t\t\t\t karma: " + post.getVote());
        System.out.print("comments:\n\t");
        for (int i = 0; i < post.getCommentList().size(); i++) {
            viewComment(post.getCommentList().get(i));
        }
    }
    public static void viewPost2(UUID postUUID) {
        Post post = getPostViaUUID(postUUID);
        System.out.print(post.getPostNumber() + " ) ");
        System.out.print(getSubRedditViaUUID(post.getSubReddit()).getSubRedditName() + ", ");
        System.out.print(getAccountViaUUID(post.getUser()).getUserName() + " -");
        System.out.println(post.getTitle() + "- :");
        System.out.println("\t" + post.getBody());
        System.out.print("\t\t");
        for (int i = 0; i < post.getTags().size(); i++) {
            if (i < post.getTags().size() - 1) {
                System.out.print(post.getTags().get(i) + " - ");
            }
            else {
                System.out.print(post.getTags().get(i));

            }
        }
        System.out.println("\t\t\t\t karma: " + post.getVote());
    }
    public static void timeLine(UUID userUUID) {
        for (int i = 0; i < 40; i++) {
            System.out.print("-");
        }
        System.out.print("\n");
        Account user = getAccountViaUUID(userUUID);
        for (int i = postList.size() - 1; i >= 0; i--) {
            for (int j = 0; j < user.getPostList().size(); j++) {
                if (postList.get(i).getSubReddit().equals(getPostViaUUID(user.getPostList().get(j)).getSubReddit())) {
                    viewPost2(postList.get(i).getPostUUID());
                    break;
                }
            }
        }
    }
    public static Account logIn() {
        Scanner in = new Scanner(System.in);
        Account account = new Account("", "", "");
        while (true) {
            System.out.print("enter your email or userName: ");
            String userEmail = in.next();
            if (userEmail.equals("cancel")) {
                account.setEmail("LOGINfaild");
                break;
            }
            else if (validEmail(userEmail) != 1 && validUserName(userEmail) != 1) {
                account.setEmail("LOGINfaild");
                System.out.println("this email or userName not exist!");
            }
            else {
                Account userAccount = new Account("", "", "");
                for (Account i : accountList) {
                    if (i.getEmail().equals(userEmail) || i.getUserName().equals("u/" + userEmail))
                        userAccount = i;
                }
                while (true) {
                    System.out.print("enter PassWord: ");
                    String userPassWord = in.next();
                    if (userPassWord.equals("cancel")) {
                        account.setEmail("LOGINfaild");
                        break;
                    }
                    else if (!userAccount.ValidPassWord(userPassWord)) {
                        System.out.println("enter correct PassWord.");
                    }
                    else {
                        account = userAccount;
                        break;
                    }
                }
                break;
            }
        }
        return account;
    }
    public static void VoteComment(UUID accountUUID) {
        Scanner in = new Scanner(System.in);
        Account account = getAccountViaUUID(accountUUID);
        int CommentNumber = 0;
        while (true) {
            System.out.print("enter CommentNumber: ");
            CommentNumber = in.nextInt();
            if (CommentNumber >= 1 && CommentNumber <= Comment.getCommentCounter()) {
                break;
            }
        }
        Comment comment = commentList.get(0);
        for (int i = 0; i < commentList.size(); i++) {
            if (commentList.get(i).getCommentNumber() == CommentNumber) {
                comment = commentList.get(i);
            }
        }
        int input = 0;
        while (input < 1 || input > 3) {
            System.out.print("select one: 1)DownVote 2)UpVote 3)ReTractVote");
            input = in.nextInt();
        }
        if (input == 1) {
            if (account.getVotes(comment.getCommentUUID()) == 0) {
                comment.DownVote();
            }
            else if (account.getVotes(comment.getCommentUUID()) == 1) {
                comment.DownVote();
                comment.DownVote();
            }
            account.vote(comment.getCommentUUID(), -1);
        }
        else if (input == 2) {
            if (account.getVotes(comment.getCommentUUID()) == 0) {
                comment.UpVote();
            }
            else if (account.getVotes(comment.getCommentUUID()) == -1) {
                comment.UpVote();
                comment.UpVote();
            }
            account.vote(comment.getCommentUUID(), 1);
        }
        else {
            if (account.getVotes(comment.getCommentUUID()) == -1) {
                comment.DownVote();
            }
            else if (account.getVotes(comment.getCommentUUID()) == 1) {
                comment.UpVote();
            }
            account.vote(comment.getCommentUUID(), 0);
        }
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getAccountUUID().equals(accountUUID)) {
                accountList.set(i, account);
            }
        }
        for (int i = 0; i < commentList.size(); i++) {
            if (commentList.get(i).getCommentNumber() == CommentNumber) {
                commentList.set(i, comment);
            }
        }
    }

    public static void VotePost(UUID accountUUID) {
        Scanner in = new Scanner(System.in);
        Account account = getAccountViaUUID(accountUUID);
        int PostNumber = -1;
        while (true) {
            System.out.print("enter PostNumber: ");
            PostNumber = in.nextInt();
            if (PostNumber >= 0 && PostNumber <= Post.getPostCounter()) {
                break;
            }
        }
        if (PostNumber != 0) {
            Post post = getPostViaNumber(PostNumber);
            int input = 0;
            while (input < 1 || input > 3) {
                System.out.print("select one: 1)DownVote 2)UpVote 3)ReTractVote");
                input = in.nextInt();
            }
            if (input == 1) {
                if (account.getVotes(post.getPostUUID()) == 0) {
                    post.DownVote();
                } else if (account.getVotes(post.getPostUUID()) == 1) {
                    post.DownVote();
                    post.DownVote();
                }
                account.vote(post.getPostUUID(), -1);
            } else if (input == 2) {
                if (account.getVotes(post.getPostUUID()) == 0) {
                    post.UpVote();
                } else if (account.getVotes(post.getPostUUID()) == -1) {
                    post.UpVote();
                    post.UpVote();
                }
                account.vote(post.getPostUUID(), 1);
            } else {
                if (account.getVotes(post.getPostUUID()) == 1) {
                    post.DownVote();
                } else if (account.getVotes(post.getPostUUID()) == -1) {
                    post.UpVote();
                }
                account.vote(post.getPostUUID(), 0);
            }
            for (int i = 0; i < accountList.size(); i++) {
                if (accountList.get(i).getAccountUUID().equals(accountUUID)) {
                    accountList.set(i, account);
                }
            }
            for (int i = 0; i < postList.size(); i++) {
                if (postList.get(i).getPostNumber() == PostNumber) {
                    postList.set(i, post);
                }
            }
        }
    }
    public void UpVoted(UUID accountUUID) {
        Account account = getAccountViaUUID(accountUUID);
        List<UUID> postUpvoted = account.PostUpvoted();
        List<UUID> commentUpvoted = account.CommentUpvoted();
        for (int i = 0; i < postUpvoted.size(); i++) {
            viewPost2(postUpvoted.get(i));
        }
        for (int i = 0; i < commentUpvoted.size(); i++) {
            viewComment(commentUpvoted.get(i));
        }
    }
    public static void viewProfile(UUID accountUUID) {
        Account account = getAccountViaUUID(accountUUID);
        System.out.println(account.getUserName());
        System.out.println("SubReddit list: ");
        System.out.print("\t");
        for (int i = 0; i < account.getSubRedditList().size(); i++) {
            System.out.print(getSubRedditViaUUID(account.getSubRedditList().get(i)).getSubRedditName() + " - ");
        }
        System.out.print("\n");
        System.out.println("total karma: " + account.getKarma());
        for (int i = 0; i < account.getPostList().size(); i++) {
            viewPost2(account.getPostList().get(i));
        }
    }
    public static void viewSubReddit(UUID subRedditUUID, UUID accountUUID) {
        Account account = getAccountViaUUID(accountUUID);
        SubReddit subReddit = getSubRedditViaUUID(subRedditUUID);
        System.out.println(subReddit.getSubRedditName());
        System.out.println("SubReddit followers: " + subReddit.numberOfFollowers());
        System.out.print("\t");
        for (int i = 0; i < accountList.size(); i++) {
            boolean find = false;
            for (int j = 0; j < accountList.get(i).getSubRedditList().size(); j++) {
                find |=  accountList.get(i).getSubRedditList().get(j).equals(subRedditUUID);
            }
            if (find) {
                System.out.print(accountList.get(i).getUserName() + " - ");
            }
        }
        System.out.print("\n");
        Scanner in = new Scanner(System.in);
        int input;
        while (true) {
            input = 0;
            while (input < 1 || input > 3) {
                System.out.println("1)joinSubReddit, 2)Back 3)viewPost");
                input = in.nextInt();
            }
            if (input == 1) {
                joinSubReddit(subRedditUUID, accountUUID);
            }
            else if (input == 2) {
                break;
            }
            else {
                for (int i = 0; i < subReddit.getPostList().size(); i++) {
                    viewPost2(subReddit.getPostList().get(i));
                }
            }
        }
    }
    public static void joinSubReddit(UUID subRedditUUID, UUID accountUUID) {
        Account account = getAccountViaUUID(accountUUID);
        SubReddit subReddit = getSubRedditViaUUID(subRedditUUID);
        boolean find = false;
        for (int i = 0; i < account.getSubRedditList().size(); i++) {
            find |= account.getSubRedditList().get(i).equals(subRedditUUID);
        }
        if (find) {
            System.out.println("you have already join in this subReddit.");
        }
        else {
            for (int i = 0; i < accountList.size(); i++) {
                if (accountList.get(i).getAccountUUID().equals(accountUUID)) {
                    accountList.get(i).addSubReddit(subRedditUUID);
                }
            }
            System.out.println("Done.");
        }
    }
    public static void Search(UUID accountUUID) {
        Account account = getAccountViaUUID(accountUUID);
        Scanner in = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print("Enter name for search: ");
            input = in.next();
            if (input.equals("cancel"))
                    break;
            else if (input.length() <= 1 || input.charAt(1) != '/') {
                System.out.println("Enter valid name.");
            }
            else if (input.charAt(0) == 'u') {
                boolean find = false;
                Account account1 = accountList.get(0);
                for (int i = 0; i < accountList.size(); i++) {
                    if (accountList.get(i).getUserName().equals(input)) {
                        find = true;
                        account1 = accountList.get(i);
                    }
                }
                if (find) {
                    viewProfile(account1.getAccountUUID());
                }
                else {
                    System.out.println("userName not found!");
                }
            }
            else {
                boolean find = false;
                SubReddit subReddit = new SubReddit("", UUID.randomUUID());
                for (int i = 0; i < subRedditList.size(); i++) {
                    if (subRedditList.get(i).getSubRedditName().equals(input)) {
                        find = true;
                        subReddit = subRedditList.get(i);
                    }
                }
                if (find) {
                    viewSubReddit(subReddit.getSubRedditUUID(), accountUUID);
                }
                else {
                    System.out.println("userName not found!");
                }
            }
        }
    }
    public static void editProfile(UUID accountUUID) {
        Scanner in = new Scanner(System.in);
        Account account = getAccountViaUUID(accountUUID);
        int input = 0;
        while (input < 1 || input > 3) {
            System.out.print("select one: 1)changeUserName 2)changePassWord 3)changeEmail");
            input = in.nextInt();
        }
        if (input == 1) {
            System.out.print("enter new userName");
            String name = in.next();
            while (validUserName(name) != 2) {
                System.out.print("enter valid userName: ");
                name = in.next();
            }
            for (int i = 0; i < accountList.size(); i++) {
                if (accountList.get(i).getAccountUUID().equals(account.getAccountUUID())) {
                    accountList.get(i).changeUserName(name);
                }
            }
        }
        else if (input == 3) {
            System.out.print("enter new email");
            String email = in.next();
            while (validEmail(email) != 2) {
                System.out.print("enter valid email: ");
                email = in.next();
            }
            for (int i = 0; i < accountList.size(); i++) {
                if (accountList.get(i).getAccountUUID().equals(account.getAccountUUID())) {
                    accountList.get(i).changeEmail(email);
                }
            }
        }
        else {
            System.out.print("enter new PassWord");
            String passWord = in.next();
            for (int i = 0; i < accountList.size(); i++) {
                if (accountList.get(i).getAccountUUID().equals(account.getAccountUUID())) {
                    accountList.get(i).changePassWord(passWord);
                }
            }
        }
    }
    public static void deletePost(UUID accountUUID) {
        Scanner in = new Scanner(System.in);
        Account account = getAccountViaUUID(accountUUID);
        Post post = postList.get(0);
        System.out.print("enter post number: ");
        int input = in.nextInt();
        boolean find = false;
        for (int i = 0; i < postList.size(); i++) {
            if (postList.get(i).getPostNumber() == input) {
                UUID postUUUD = getPostViaNumber(input).getPostUUID();
                for (int j = 0; j < getSubRedditViaUUID(postList.get(i).getSubReddit()).getAdminsList().size(); j++) {
                    if (getSubRedditViaUUID(postList.get(i).getSubReddit()).getAdminsList().get(j).equals(accountUUID)) {
                        find = true;
                        post = postList.get(i);
                    }
                }
                for (int j = 0; j < account.getPostList().size(); j++) {
                    if (account.getPostList().get(j).equals(postUUUD)) {
                        find = true;
                        post = postList.get(i);
                    }
                }
            }
        }
        if (find) {
            for (int i = 0; i < accountList.size(); i++) {
                accountList.get(i).getPostList().remove(post.getPostUUID());
            }
            for (int i = 0; i < subRedditList.size(); i++) {
                subRedditList.get(i).getPostList().remove(post.getPostUUID());
            }
            postList.remove(post);
        }
        else {
            System.out.println("access denied.");
        }
    }
    public static void newAdmin(UUID accountUUID) {
        Scanner in = new Scanner(System.in);
        Account account = getAccountViaUUID(accountUUID);
        System.out.println("SubReddits: ");
        for (int i = 0; i < subRedditList.size(); i++) {
            if (subRedditList.get(i).getAdmin().equals(accountUUID)) {
                System.out.println(subRedditList.get(i).getSubRedditName());
            }
        }
        System.out.println("enter SubRedditName: ");
        String name = in.next();
        SubReddit subReddit = subRedditList.get(0);
        boolean find = false;
        for (int i = 0; i < subRedditList.size(); i++) {
            if (subRedditList.get(i).getAdmin().equals(accountUUID) && subRedditList.get(i).getSubRedditName().equals(name)) {
                find = true;
                subReddit = subRedditList.get(i);
            }
        }
        if (find) {
            System.out.println("enter UserName: ");
            String userName = in.next();
            for (int i = 0; i < accountList.size(); i++) {
                if (accountList.get(i).getUserName().equals(userName)) {
                    accountList.get(i).addSubReddit(subReddit.getSubRedditUUID());
                    for (int j = 0; j < subRedditList.size(); j++) {
                        if (subRedditList.get(j).getSubRedditName().equals(name)) {
                            subRedditList.get(j).newAdmin(accountList.get(i).getAccountUUID());
                        }
                    }
                }
            }
        }
        else {
            System.out.println("access denied.");
        }
    }
    public static void viewPostMenu(UUID accountUUID) {
        Scanner in = new Scanner(System.in);
        Account account = getAccountViaUUID(accountUUID);
        int input = -1;
        while (input < 0 || input > Post.getPostCounter()) {
            System.out.println("enter postNumber: ");
            input = in.nextInt();
        }
        if (input != 0) {
            viewPost(getPostViaNumber(input).getPostUUID());
        }
    }
    public static void viewLikes(UUID accountUUID) {
        Account account = getAccountViaUUID(accountUUID);
        for (int i = 0; i < account.PostUpvoted().size(); i++) {
            viewPost2(account.PostUpvoted().get(i));
        }
        System.out.print("\n");
        for (int i = 0; i < account.CommentUpvoted().size(); i++) {
            viewComment(account.CommentUpvoted().get(i));
        }
    }
    public static void viewSaves(UUID accountUUID) {
        Account account = getAccountViaUUID(accountUUID);
        List<UUID> saves = account.getSaves();
        for (int i = 0; i < saves.size(); i++) {
            viewPost2(saves.get(i));
        }
    }
    public static void saveAndUnSave(UUID accountUUID) {
        Scanner in = new Scanner(System.in);
        Account account = getAccountViaUUID(accountUUID);
        List<UUID> saves = account.getSaves();
        int input = -1;
        while (input < 0 || input > 2) {
            System.out.print("select one: 1)save 2)unsave: ");
            input = in.nextInt();
        }
        if (input == 1) {
            System.out.println("enter postNumber: ");
            input = in.nextInt();
            if (input > Post.getPostCounter() || input < 1) {
                System.out.println("access denied.");
            }
            else if (input != 0) {
                Post post = getPostViaNumber(input);
                boolean find = false;
                for (int i = 0; i < saves.size(); i++) {
                    if (saves.get(i).equals(post.getPostUUID())) {
                        find = true;
                    }
                }
                if (find) {
                    System.out.println("this post already exist in save list.");
                }
                else {
                    account.savePost(post.getPostUUID());
                }
            }
        }
        else {
            System.out.println("enter postNumber: ");
            input = in.nextInt();
            if (input > Post.getPostCounter() || input < 1) {
                System.out.println("access denied.");
            }
            else if (input != 0) {
                Post post = getPostViaNumber(input);
                boolean find = false;
                for (int i = 0; i < saves.size(); i++) {
                    if (saves.get(i).equals(post.getPostUUID())) {
                        find = true;
                    }
                }
                if (!find) {
                    System.out.println("this post already not exist in save list.");
                }
                else {
                    account.UnSavePost(post.getPostUUID());
                }
            }
        }
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getAccountUUID().equals(accountUUID)) {
                accountList.set(i, account);
            }
        }
    }
    public static void removeSubReddit(UUID accountUUID, UUID subRedditUUID) {
        Account account = getAccountViaUUID(accountUUID);
        account.removeSub(subRedditUUID);
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getAccountUUID().equals(accountUUID)) {
                accountList.set(i, account);
                for (int j = 0; j < subRedditList.size(); j++) {
                    if (subRedditList.get(j).getSubRedditUUID().equals(subRedditUUID)) {
                        subRedditList.get(j).removeAdmin(accountUUID);
                    }
                }
            }
        }
    }
    public static void leftSubReddit(UUID accountUUID) {
        Scanner in = new Scanner(System.in);
        Account account = getAccountViaUUID(accountUUID);
        System.out.println("enter subRedditName: ");
        String input = in.next();
        for (int i = 0; i < subRedditList.size(); i++) {
            if (subRedditList.get(i).getSubRedditName().equals(input)) {
                removeSubReddit(accountUUID, subRedditList.get(i).getSubRedditUUID());
            }
        }
    }
    public static void removeSubReddit2(UUID accountUUID) {
        Scanner in = new Scanner(System.in);
        System.out.print("enter subReddit: ");
        String sub = in.next();
        System.out.print("enter user: ");
        String user = in.next();
        SubReddit subReddit = subRedditList.get(0);
        boolean find = false;
        for (int i = 0; i < subRedditList.size(); i++) {
            if (subRedditList.get(i).getSubRedditName().equals(sub) && subRedditList.get(i).getAdmin().equals(accountUUID)) {
                find = true;
                subReddit = subRedditList.get(i);
            }
        }
        if (find) {
            removeSubReddit(accountUUID, subReddit.getSubRedditUUID());
        }
        else {
            System.out.println("access denied.");
        }
    }
    public static void deleteSubReddit(UUID accountUUID) {
        Scanner in = new Scanner(System.in);
        Account account = getAccountViaUUID(accountUUID);
        System.out.println("enter subRedditName: ");
        String input = in.next();
        boolean find = false;
        for (int i = 0; i < subRedditList.size(); i++) {
            if (subRedditList.get(i).getSubRedditName().equals(input)) {
                if (subRedditList.get(i).getAdmin().equals(accountUUID)) {
                    find = true;
                    SubReddit subReddit = subRedditList.get(i);
                    subRedditList.remove(subReddit);
                    for (int j = 0; j < accountList.size(); j++) {
                        accountList.get(j).removeSub(subReddit.getSubRedditUUID());
                    }
                    for (int j = 0; j < postList.size(); j++) {
                        if (postList.get(j).getSubReddit().equals(subReddit.getSubRedditUUID())) {
                            Post post = postList.get(j);
                            deletePost(post.getPostUUID());
                        }
                    }
                }
            }
        }
        if (!find) {
            System.out.println("access denied.");
        }
    }
}
