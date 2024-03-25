import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
        return String.valueOf(ans) + '#' + String.valueOf(ans2);
    }

    public static int validEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}";
        //need to change
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
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
        String regex = "[a-zA-Z_]+[0-9]";
        //need to change
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userName);
        if (!matcher.find()) {
            return 0;
        }
        boolean find = false;
        for (int i = 0; i < accountList.size(); i++) {
            find |= accountList.get(i).getUserName().equals(userName);
        }
        if (find) {
            return 1;
        }
        else {
            return 2;
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

    public static void newAccount(Account account) {
        accountList.add(account);
    }
    public static void newComment(Comment comment) {
        commentList.add(comment);
    }
    public static void newSubReddit(SubReddit subReddit) {
        subRedditList.add(subReddit);
    }
    public static void newPost(Post post) {
        postList.add(post);
    }
    public void viewComment(UUID commentUUID) {
        Comment comment = getCommentViaUUID(commentUUID);
        System.out.println("\t\t" + getAccountViaUUID(comment.getUser()).getUserName() + ":");
        System.out.println("\t\t\t" + comment.getText());
    }
    public void viewPost(UUID postUUID) {
        Post post = getPostViaUUID(postUUID);
        System.out.print(getSubRedditViaUUID(post.getSubReddit()).getSubRedditName() + ", ");
        System.out.print(getAccountViaUUID(post.getUser()).getUserName() + " -");
        System.out.println(post.getTitle() + "- :");
        System.out.println("\t" + post.getBody());
        for (int i = 0; i < post.getCommentList().size(); i++) {
            viewComment(post.getCommentList().get(i));
        }
    }
    public void timeLine(UUID userUUID) {
        Account user = getAccountViaUUID(userUUID);
        for (int i = postList.size() - 1; i >= 0; i--) {
            for (int j = 0; j < user.getPostList().size(); j++) {
                if (postList.get(i).getPostUUID().equals(user.getPostList().get(j))) {
                    viewPost(postList.get(i).getPostUUID());
                    break;
                }
            }
        }
    }
}