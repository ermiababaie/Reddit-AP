import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Account {

    private String email;
    private String passWord;
    private String userName;
    private UUID accountUUID;
    private List<UUID> postList;
    private List<UUID> subRedditList;
    private List<UUID> commentList;
    private HashMap<UUID,Integer> votes;
    private List<UUID> UpVOtes;
    private List<UUID> saves;

    public  Account(String email, String passWord, String userName) {
        this.email = email;
        this.passWord = Reddit.hash(passWord);
        this.userName = userName;
        this.accountUUID = UUID.randomUUID();
        this.commentList = new ArrayList<>();
        this.subRedditList = new ArrayList<>();
        this.postList = new ArrayList<>();
        votes = new HashMap<UUID,Integer>();
        UpVOtes = new ArrayList<>();
        saves = new ArrayList<>();
    }
    public boolean ValidPassWord(String passWord) {
        return Reddit.hash(passWord).equals(this.passWord);
    }
    public String getEmail() {
        return email;
    }
    public UUID getAccountUUID() {
        return accountUUID;
    }

    public String getUserName() {
        return "u/" + userName;
    }
    public void changeUserName(String userName) {
        if (userName.equals(this.userName)) {
            System.out.println("already this is your userName!");
        }
        else if (Reddit.validUserName(userName) == 0) {
            System.out.println("this is not valid userName.");
        }
        else if (Reddit.validUserName(userName) == 1) {
            System.out.println("this userName already exist.");
        }
        else {
            this.userName = userName;
            System.out.println("userName changed.");
        }
    }

    public List<UUID> getPostList() {
        return postList;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public void setPassWord(String passWord) {
        this.passWord = Reddit.hash(passWord);
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }
    public void addSubReddit(UUID subRedditUUID) {
        subRedditList.add(subRedditUUID);
    }
    public void addComment(UUID commentUUID) {
        commentList.add(commentUUID);
    }

    public List<UUID> getSubRedditList() {
        return subRedditList;
    }
    public void addPost(UUID postUUID) {
        postList.add(postUUID);
    }
    public int getKarma() {
        int karma = 0;
        for (int i = 0; i < commentList.size(); i++) {
            karma += Reddit.getCommentViaUUID(commentList.get(i)).getVote();
        }
        for (int i = 0; i < postList.size(); i++) {
            karma += Reddit.getPostViaUUID(postList.get(i)).getVote();
        }
        return karma;
    }
    public int getVotes(UUID uuid) {
        if (votes.containsKey(uuid)) {
            return votes.get(uuid);
        }
        else {
            return 0;
        }
    }
    public void vote(UUID uuid, int karma) {
        if (karma == 1) {
            if (getVotes(uuid) != +1) {
                UpVOtes.add(uuid);
            }
        }
        else {
            if (getVotes(uuid) == +1) {
                UpVOtes.remove(uuid);
            }
        }
        votes.put(uuid, karma);
    }
    public List<UUID> PostUpvoted() {
        List<UUID> list = new ArrayList<>();
        for (int i = 0; i < UpVOtes.size(); i++) {
            for (int j = 0; j < Reddit.postList.size(); j++) {
                if (UpVOtes.get(i).equals(Reddit.postList.get(j).getPostUUID())) {
                    list.add(UpVOtes.get(i));
                }
            }
        }
        return list;
    }
    public List<UUID> CommentUpvoted() {
        List<UUID> list = new ArrayList<>();
        for (int i = 0; i < UpVOtes.size(); i++) {
            for (int j = 0; j < Reddit.commentList.size(); j++) {
                if (UpVOtes.get(i).equals(Reddit.commentList.get(j).getCommentUUID())) {
                    list.add(UpVOtes.get(i));
                }
            }
        }
        return list;
    }
    public void changeEmail(String email) {
        this.email = email;
    }
    public void changePassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWord() {
        return passWord;
    }

    public List<UUID> getUpVOtes() {
        return UpVOtes;
    }

    public List<UUID> getSaves() {
        return saves;
    }
    public void savePost(UUID postUUID) {
        saves.add(postUUID);
    }
    public void UnSavePost(UUID postUUID) {
        saves.remove(postUUID);
    }
    public void removeSub(UUID subUUID) {
        subRedditList.remove(subUUID);
    }
}
