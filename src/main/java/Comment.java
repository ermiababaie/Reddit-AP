import java.util.UUID;

public class Comment {
    private UUID commentUUID;
    public Comment() {
        commentUUID = UUID.randomUUID();
    }

    public UUID getCommentUUID() {
        return commentUUID;
    }
}