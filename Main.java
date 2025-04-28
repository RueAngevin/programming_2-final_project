import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create the hardcoded organization (step 1)
        Group topManagement = new Group("Top Management", "Raymond Holt");
        Worker terryJeffords = new Worker("Terry Jeffords");
        topManagement.add(terryJeffords);

        Group marketing = new Group("Marketing", "Amy Santiago");
        Worker rosaDiazMarketing = new Worker("Rosa Diaz");
        marketing.add(rosaDiazMarketing);
        topManagement.add(marketing);

        Group softwareDevelopment = new Group("Software Development", "Jake Peralta");
        Worker charlesBoyle = new Worker("Charles Boyle");
        Worker ginaLinetti = new Worker("Gina Linetti");
        Worker normScully = new Worker("Norm Scully");
        softwareDevelopment.add(charlesBoyle);
        softwareDevelopment.add(ginaLinetti);
        softwareDevelopment.add(normScully);
        topManagement.add(softwareDevelopment);

        Group customerSupport = new Group("Customer Support", "Rosa Diaz");
        Worker hitchcockScully = new Worker("Hitchcock Scully");
        Worker adrianPimento = new Worker("Adrian Pimento");
        Worker kevinCozner = new Worker("Kevin Cozner");
        customerSupport.add(hitchcockScully);
        customerSupport.add(adrianPimento);
        customerSupport.add(kevinCozner);
        topManagement.add(customerSupport);

        // Presenting the menu and performing actions
        while (true) {
            printMenu();
            System.out.print("Your choice: ");
            String choice = scanner.nextLine().trim();

            if (choice.equalsIgnoreCase("q")) {
                break;
            } else if (choice.equals("1")) {
                topManagement.print();
                System.out.println(); // extra space
            } else if (choice.equals("2")) {
                // Print the organization and add a person
                topManagement.print();
                System.out.print("Give unit name: ");
                String groupName = scanner.nextLine().trim();

                if (groupExists(groupName)) {
                    System.out.print("Give person name: ");
                    String personName = scanner.nextLine().trim();
                    String[] nameParts = personName.split(" ");
                    if (nameParts.length == 2) {
                        String firstName = nameParts[0];
                        String lastName = nameParts[1];
                        Worker newWorker = new Worker(firstName + " " + lastName);

                        // Add new worker to the group
                        getGroupByName(groupName).add(newWorker);

                        // Print the updated organization
                        System.out.println("\nUpdated Organization:");
                        topManagement.print();
                    } else {
                        System.out.println("Invalid name format. Please enter first and last name.");
                    }
                } else {
                    System.out.println("Group not found. Please enter a valid group name.");
                }

                System.out.println(); // extra space
            } else if (choice.equals("3")) {
                System.out.println("Choice 3 not implemented.\n");
            } else {
                System.out.println("Invalid choice.\n");
            }
        }

        System.out.println("\nProcess finished with exit code 0");
        scanner.close();
    }

    public static void printMenu() {
        System.out.println("Organization management system");
        System.out.println("------------------------------");
        System.out.println();
        System.out.println("1. Create and print hard coded organization");
        System.out.println("2. Print organization, add person to it and finally print it");
        System.out.println("3. Print organization, remove person from it and finally print it");
        System.out.println("Q. Quit the application");
        System.out.println();
    }

    // Check if the group exists by name
    public static boolean groupExists(String groupName) {
        return getGroupByName(groupName) != null;
    }

    // Retrieve the group by name
    public static Group getGroupByName(String groupName) {
        switch (groupName) {
            case "Top Management":
                return new Group("Top Management", "Raymond Holt");
            case "Marketing":
                return new Group("Marketing", "Amy Santiago");
            case "Software Development":
                return new Group("Software Development", "Jake Peralta");
            case "Customer Support":
                return new Group("Customer Support", "Rosa Diaz");
            default:
                return null;
        }
    }
}
