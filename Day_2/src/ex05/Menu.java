package ex05;

import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private TransactionsService logic;
    private Scanner in;

    public Menu() {
        logic = new TransactionsService();
        in = new Scanner(System.in);
    }

    public void start(String[] args) {
        if (args.length == 0) {
            stMode();
        } else if (args.length == 1 && args[0].equals("--profile=dev")) {
            devMode();
        } else {
            System.out.println("Wrong start arguments!");
        }
    }

    private void stMode() {
        int choice = 0;
        while (true) {
            printMenu();
            choice = inputCheck(choice);
            switch (choice) {
                case 1:
                    option1(logic);
                    break;
                case 2:
                    option2(logic);
                    break;
                case 3:
                    option3(logic);
                    break;
                case 4:
                    option4(logic);
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Error, only 1-5 options is available");
            }
        }
    }

    private void option1(TransactionsService logic) {
        System.out.println("Enter a user name and a balance");
        try {
            String input = in.nextLine();
            String[] split = input.split(" ");
            if (split.length != 2) {
                System.out.println("Wrong Input!");
                return;
            }
            User add = new User(split[0], Integer.parseInt(split[1]));
            logic.addUser(add);
            System.out.printf("User with id = %d is added%n", add.getIdentifier());
        } catch (NumberFormatException e) {
            System.out.println("Wrong input!");
        }
    }

    private void option2(TransactionsService logic) {
        System.out.println("Enter a user ID");
        try {
            int id = Integer.parseInt(in.nextLine());
            System.out.printf("%s - %d%n", logic.getUser(id).getName(), logic.userBalance(id));
        } catch (NumberFormatException e) {
            System.out.println("Wrong input!");
        } catch (UserNotFoundException e) {
            System.out.println("Wrong input!");
        }
    }

    private void option3(TransactionsService logic) {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        try {
            String input = in.nextLine();
            String[] split = input.split(" ");
            if (split.length != 3) {
                System.out.println("Wrong Input!");
                return;
            }
            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);
            int amount = Integer.parseInt(split[2]);
            logic.performTransaction(from, to, amount);
            System.out.println("The transfer is completed");
        } catch (NumberFormatException e) {
            System.out.println("Wrong input!");
        } catch (IllegalTransactionException e) {
            System.out.println("Wrong input!");
        } catch (UserNotFoundException e) {
            System.out.println("Wrong input!");
        }
    }

    private void option4(TransactionsService logic) {
        System.out.println("Enter a user ID");
        try {
            int id = Integer.parseInt(in.nextLine());
            for (Transaction data : logic.userTransactions(id)) {
                if (data.getAmount() < 0) {
                    System.out.printf("To %s(id = %d) %d with id = %s%n",
                            data.getRecipient().getName(),
                            data.getRecipient().getIdentifier(),
                            data.getAmount(),
                            data.getId());
                } else {
                    System.out.printf("From %s (id = %d) %d with id = %s%n",
                            data.getSender().getName(),
                            data.getSender().getIdentifier(),
                            data.getAmount(),
                            data.getId());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input!");
        } catch (UserNotFoundException e) {
            System.out.println("Wrong input!");
        }
    }

    private void devMode() {
        int choice = 0;
        while (true) {
            printDevMenu();
            choice = inputCheck(choice);
            switch (choice) {
                case 1:
                    option1(logic);
                    break;
                case 2:
                    option2(logic);
                    break;
                case 3:
                    option3(logic);
                    break;
                case 4:
                    option4(logic);
                    break;
                case 5:
                    option5(logic);
                    break;
                case 6:
                    option6(logic);
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Error, only 1-7 options is available");
            }
        }
    }

    private void option5(TransactionsService logic) {
        System.out.println("Enter a user ID and a transfer ID");
        try {
            String input = in.nextLine();
            String[] split = input.split(" ");
            if (split.length != 2) {
                System.out.println("Wrong Input!");
                return;
            }
            int id = Integer.parseInt(split[0]);
            UUID idTr = UUID.fromString(split[1]);

            for (Transaction data : logic.userTransactions(id)) {
                if (data.getId().equals(idTr)) {
                    if (data.getAmount() < 0) {
                        System.out.printf("Transfer To %s (id = %d) %s removed%n",
                                data.getRecipient().getName(),
                                data.getRecipient().getIdentifier(),
                                data.getAmount());
                    } else {
                        System.out.printf("Transfer From %s (id = %d) %s removed%n",
                                data.getSender().getName(),
                                data.getSender().getIdentifier(),
                                data.getAmount());
                    }
                    logic.removeTransactions(id, idTr);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input!");
        } catch (UserNotFoundException e) {
            System.out.println("Wrong input!");
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong input!");
        }
    }

    private void option6(TransactionsService logic) {
        System.out.println("Check results:");
        for (Transaction data : logic.checkValidity()) {
            if (data.getAmount() < 0) {
                System.out.printf("%s(id = %d) has an unacknowledged transfer id = %s to %s(id = %d) for %d%n",
                        data.getSender().getName(),
                        data.getSender().getIdentifier(),
                        data.getId(),
                        data.getRecipient().getName(),
                        data.getRecipient().getIdentifier(),
                        -data.getAmount());
            } else {
                System.out.printf("%s(id = %d) has an unacknowledged transfer id = %s from %s(id = %d) for %d%n",
                        data.getRecipient().getName(),
                        data.getRecipient().getIdentifier(),
                        data.getId(),
                        data.getSender().getName(),
                        data.getSender().getIdentifier(),
                        data.getAmount());
            }
        }
    }

    private void printDevMenu() {
        System.out.println("---------------------------------------------------------");
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        System.out.println("5. DEV – remove a transfer by ID");
        System.out.println("6. DEV – check transfer validity");
        System.out.println("7. Finish execution");
        System.out.println("---------------------------------------------------------");
    }

    private void printMenu() {
        System.out.println("---------------------------------------------------------");
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        System.out.println("5. Finish execution");
        System.out.println("---------------------------------------------------------");
    }

    private int inputCheck(int input) {
        try {
            input = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
        return input;
    }
}
