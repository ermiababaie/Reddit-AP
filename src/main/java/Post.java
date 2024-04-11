import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Post {
    static int postCounter;
    private int postNumber;
    private UUID user;
    private UUID subReddit;
    private List<UUID> commentList;
    private UUID postUUID;
    private String title;
    private String body;
    private List<String> Tags;
    private int Vote;
    public Post(UUID subReddit, UUID user, String title, String body) {
        postCounter++;
        postNumber = postCounter;
        this.subReddit = subReddit;
        this.user = user;
        this.title = title;
        this.body = body;
        postUUID = UUID.randomUUID();
        Tags = new ArrayList<>();
        commentList = new ArrayList<>();
        Vote = 0;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public UUID getUser() {
        return user;
    }

    public UUID getSubReddit() {
        return subReddit;
    }
    public UUID getPostUUID() {
        return postUUID;
    }
    public void addComment(UUID commentUUID) {
        commentList.add(commentUUID);
    }

    public List<UUID> getCommentList() {
        return commentList;
    }

    public int getPostNumber() {

        return postNumber;
    }

    public static int getPostCounter() {

        return postCounter;
    }
    public void addTag(String tag) {
        Tags.add(tag);
    }

    public List<String> getTags() {
        return Tags;
    }
    public void DownVote() {
        Vote--;
    }
    public void UpVote() {
        Vote++;
    }
    public int getVote() {
        return Vote;
    }
}