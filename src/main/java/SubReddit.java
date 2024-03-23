import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SubReddit {
    private UUID subRedditUUID;
    private UUID admin;
    private List<UUID> AdminsList;
    private List<UUID> postList;
    public SubReddit() {
        AdminsList = new ArrayList<>();
        postList = new ArrayList<>();
        this.subRedditUUID = UUID.randomUUID();
    }

    public UUID getSubRedditUUID() {
        return subRedditUUID;
    }
}