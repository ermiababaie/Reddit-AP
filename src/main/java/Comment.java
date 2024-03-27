import java.util.UUID;

public class Comment {
    private static int CommentCounter;
    private int CommentNumber = 0;
    private UUID user;
    private UUID commentUUID;
    private String text;
    private UUID postUUID;
    private int Vote;
    public Comment(UUID user, String text, UUID postUUID) {
        CommentCounter++;
        CommentNumber = CommentCounter;
        this.user = user;
        this.text = text;
        this.postUUID = postUUID;
        commentUUID = UUID.randomUUID();
        Vote = 0;
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

    public UUID getPostUUID() {
        return postUUID;
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

    public int getCommentNumber() {
        return CommentNumber;
    }

    public static int getCommentCounter() {
        return CommentCounter;
    }
}