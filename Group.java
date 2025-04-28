import java.util.ArrayList;
import java.util.List;

public class Group extends Component {
    private String bossName;
    private List<Component> members = new ArrayList<>();

    public Group(String name, String bossName) {
        super(name);
        this.bossName = bossName;
    }

    public void add(Component component) {
        members.add(component);
    }

    public void remove(Component component) {
        members.remove(component);
    }

    public List<Component> getMembers() {
        return members;
    }

    @Override
    public void print() {
        System.out.println("Group: " + name + ", Boss: " + bossName);
        for (Component member : members) {
            member.print();
        }
        System.out.println();
    }
}