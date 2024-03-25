import java.util.List;
import java.util.UUID;

public class Post {
    private UUID user;
    private UUID subReddit;
    private List<UUID> commentList;
    private UUID postUUID;
    private String title;
    private String body;
    public Post(UUID subReddit, UUID user, String title, String body) {
        this.subReddit = subReddit;
        this.user = user;
        this.title = title;
        this.body = body;
        postUUID = UUID.randomUUID();
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
}