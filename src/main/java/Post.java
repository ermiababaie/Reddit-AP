import java.util.List;
import java.util.UUID;

public class Post {
    private UUID subReddit;
    private List<UUID> commentList;
    private UUID postUUID;
    public Post() {
        postUUID = UUID.randomUUID();
    }

    public UUID getPostUUID() {
        return postUUID;
    }
//    public void viewComments() {
//
//    }
}