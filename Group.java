import java.util.ArrayList;
import java.util.List;

public class Group extends Component {
    private String bossName;
    private List<Component> members = new ArrayList<>();

    public Group(String name, String bossName) {
        super(name); // Call the superclass constructor to set the group name
        this.bossName = bossName;
    }

    // Add a member (can be a worker or another group)
    public void add(Component component) {
        members.add(component);
    }

    // Remove a member from the group
    public void remove(Component component) {
        members.remove(component);
    }

    // Print the group's structure, including the boss and the members
    @Override
    public void print() {
        System.out.println("Group: " + name + ", Boss: " + bossName);
        for (Component member : members) {
            member.print();  // Print each member (either a worker or a group)
        }
    }
}
