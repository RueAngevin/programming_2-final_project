public class Worker extends Component {

    public Worker(String name) {
        super(name);
    }

    @Override
    public void print() {
        System.out.println("Worker: " + name);
    }
}