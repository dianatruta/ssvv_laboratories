import controller.EntryController;
import controller.MemberController;
import repository.EntryRepository;
import repository.MemberRepository;
import ui.UI;
import validators.EntryValidator;
import validators.MemberValidator;

;

public class App {
    public static void main(String[] args) {

        EntryValidator entryValidator = new EntryValidator();
        MemberValidator memberValidator = new MemberValidator();

        EntryRepository entryRepository = new EntryRepository("budgetF.txt");
        MemberRepository memberRepository = new MemberRepository("membersF.txt");

        EntryController entryController = new EntryController(entryRepository, memberRepository, entryValidator);
        MemberController memberController = new MemberController(memberRepository, memberValidator);

        UI console = new UI(entryController, memberController);
        console.run();

    }
}
