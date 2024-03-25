import java.util.UUID;

public class Comment {
    private UUID user;
    private UUID commentUUID;
    private String text;
    public Comment(UUID user, String text) {
        this.user = user;
        this.text = text;
        commentUUID = UUID.randomUUID();
    }

    public UUID getCommentUUID() {
        return commentUUID;
    }

    public UUID getUser() {
        return user;
    }

    public String getText() {
        return text;
    }
}