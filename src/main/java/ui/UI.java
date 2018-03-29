package ui;

import controller.EntryController;
import controller.MemberController;
import exceptions.InvalidEntryException;
import exceptions.InvalidMemberException;
import exceptions.MemberAlreadyExistsException;
import exceptions.MemberDoesNotExistException;
import model.Entry;

import java.util.List;
import java.util.Scanner;

;

public class UI {
    public MemberController memberController;
    public EntryController entryController;

    public UI(EntryController entryController, MemberController memberController) {
        this.memberController = memberController;
        this.entryController = entryController;
    }

    public void printMenu() {
        String menu;
        menu = "Budget Admin Menu: \n";
        menu += "\t 1 - to add a new member; \n";
        menu += "\t 2 - to add a new income/cost for a member; \n";
        menu += "\t 3 - to list the income/cost for a member; \n";
        menu += "\t 0 - exit \n";
        menu += "Give a command:";

        System.out.println(menu);
    }

    @SuppressWarnings("resource")
    public void run() {
        Scanner in = new Scanner(System.in);

        while (true) {
            printMenu();

            String cmd = in.next();
            if (cmd.equals("1")) {

                System.out.println("Enter id:");
                String id = in.next();
                System.out.println("Enter name:");
                String name = in.next();
                try {
                    memberController.addMember(id, name);
                } catch (MemberAlreadyExistsException | InvalidMemberException e) {
                    System.out.println(e.getLocalizedMessage());
                }

            } else if (cmd.equals("2")) {

                try {
                    System.out.println("Enter type:");
                    String type = in.next();

                    System.out.println("Enter the value:");
                    String value = in.next();

                    System.out.println("Enter the id of the member:");
                    String memberId = in.next();

                    entryController.addEntry(type, value, memberId);
                } catch (MemberDoesNotExistException | InvalidEntryException e) {
                    System.out.println(e.getLocalizedMessage());
                }

            } else if (cmd.equals("3")) {

                try {
                    for (Entry entry : entryController.allEntries()) {
                        System.out.println(entry);
                    }
                    System.out.println("Enter id for member:");
                    int id = Integer.parseInt(in.next());
                    List<Entry> entries = entryController.getEntriesFor(id);
                    for (Entry entry : entries) {
                        System.out.println(entry);
                    }
                } catch (NumberFormatException exception) {
                    System.out.println("Wrong id for member!");
                } catch (MemberDoesNotExistException e) {
                    System.out.println(e.getLocalizedMessage());
                }

            } else if (cmd.equals("0")) {
                System.exit(0);
            } else {
                System.out.println("Wrong command! Please choose from the listed menu!");
            }

        }
    }
}
