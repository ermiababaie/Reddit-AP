import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SubReddit {
    String subRedditName;
    private UUID subRedditUUID;
    private UUID admin;
    private List<UUID> AdminsList;
    private List<UUID> postList;
    public SubReddit(String subRedditName) {
        this.subRedditName = subRedditName;
        AdminsList = new ArrayList<>();
        postList = new ArrayList<>();
        this.subRedditUUID = UUID.randomUUID();
    }
    public void addPost(UUID postUUID) {
        postList.add(postUUID);
    }
    public UUID getSubRedditUUID() {
        return subRedditUUID;
    }

    public String getSubRedditName() {
        return subRedditName;
    }
}