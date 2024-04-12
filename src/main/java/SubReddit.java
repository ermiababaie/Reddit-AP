import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SubReddit {
    private String subRedditName;
    private UUID subRedditUUID;
    private UUID admin;
    private List<UUID> AdminsList;
    private List<UUID> postList;
    public SubReddit(String subRedditName, UUID admin) {
        this.subRedditName = subRedditName;
        this.admin = admin;
        AdminsList = new ArrayList<>();
        AdminsList.add(admin);
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

        return "r/" + subRedditName;
    }

    public List<UUID> getPostList() {

        return postList;
    }

    public List<UUID> getAdminsList() {

        return AdminsList;
    }

    public UUID getAdmin() {
        return admin;
    }
    public int numberOfFollowers() {
        int cnt = 0;
        for (int i = 0; i < Reddit.accountList.size(); i++) {
            boolean find = false;
            for (int j = 0; j < Reddit.accountList.get(i).getSubRedditList().size(); j++) {
                find |= Reddit.accountList.get(i).getSubRedditList().get(j).equals(subRedditUUID);
            }
            if (find) {
                cnt++;
            }
        }
        return cnt;
    }
    public void newAdmin(UUID uuid) {
        AdminsList.add(uuid);
    }
    public void removeAdmin(UUID adminUUID) {
        AdminsList.remove(adminUUID);
    }
}
