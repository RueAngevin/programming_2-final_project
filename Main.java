import java.util.Scanner;

public class Main {
    private static Group topManagement;
    private static boolean organizationCreated = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                printMenu();
                System.out.print("Your choice: ");
                String choice = scanner.nextLine().trim();

                if (choice.equalsIgnoreCase("q")) {
                    break;
                } else if (choice.equals("1")) {
                    createOrganization();
                    organizationCreated = true;
                    topManagement.print();
                    System.out.println();
                } else if (choice.equals("2") || choice.equals("3")) {
                    if (!organizationCreated) {
                        throw new OrganizationException("Organization is not created yet. Create it first in step 1.");
                    }

                    topManagement.print();

                    if (choice.equals("2")) {
                        handleAddPerson(scanner);
                    } else {
                        handleRemovePerson(scanner);
                    }
                } else {
                    throw new InvalidInputException("Invalid input. Please enter 1, 2, 3, q or Q");
                }
            } catch (OrganizationException | InvalidInputException | NameFormatException e) {
                System.out.println("ERROR: " + e.getMessage());
                System.out.println();
            }
        }

        System.out.println("\nProcess finished with exit code 0");
        scanner.close();
    }

    private static void createOrganization() {
        topManagement = new Group("Top Management", "Raymond Holt");
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
    }

    private static void handleAddPerson(Scanner scanner) throws OrganizationException, NameFormatException {
        System.out.print("Give unit name: ");
        String groupName = scanner.nextLine().trim();

        Group targetGroup = getGroupByName(groupName);
        if (targetGroup == null) {
            throw new OrganizationException("Organization unit not found. Give it again.");
        }

        System.out.print("Give person name: ");
        String personName = scanner.nextLine().trim();
        validateNameFormat(personName);

        Worker newWorker = new Worker(personName);
        targetGroup.add(newWorker);

        System.out.println("\nUpdated Organization:");
        topManagement.print();
        System.out.println();
    }

    private static void handleRemovePerson(Scanner scanner) throws OrganizationException, NameFormatException {
        System.out.print("Give person name to remove: ");
        String personName = scanner.nextLine().trim();
        validateNameFormat(personName);

        if (!removeWorkerByName(topManagement, personName)) {
            throw new OrganizationException("Person not found. Give it again.");
        }

        System.out.println("\nUpdated Organization:");
        topManagement.print();
        System.out.println();
    }

    private static void validateNameFormat(String name) throws NameFormatException {
        if (!name.matches("[A-Z][a-z]+ [A-Z][a-z]+")) {
            throw new NameFormatException("Invalid name. Please enter a valid name like John Smith.");
        }
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

    public static Group getGroupByName(String groupName) {
        return findGroupInHierarchy(topManagement, groupName);
    }

    private static Group findGroupInHierarchy(Group group, String groupName) {
        if (group.getName().equalsIgnoreCase(groupName)) {
            return group;
        }
        
        for (Component member : group.getMembers()) {
            if (member instanceof Group) {
                Group found = findGroupInHierarchy((Group) member, groupName);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    private static boolean removeWorkerByName(Group group, String workerName) {
        for (Component member : group.getMembers()) {
            if (member instanceof Worker && member.getName().equals(workerName)) {
                group.remove(member);
                return true;
            } else if (member instanceof Group) {
                boolean removed = removeWorkerByName((Group) member, workerName);
                if (removed) {
                    return true;
                }
            }
        }
        return false;
    }
}

class OrganizationException extends Exception {
    public OrganizationException(String message) {
        super(message);
    }
}

class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

class NameFormatException extends Exception {
    public NameFormatException(String message) {
        super(message);
    }
}