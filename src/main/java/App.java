
import repository.EntryRepository;
import repository.MemberRepository;
import controller.EntryController;
import controller.MemberController;
import ui.UI;;

public class App {
	public static void main(String[] args) {

		EntryRepository entryRepository = new EntryRepository("budgetF.txt");
		MemberRepository memberRepository = new MemberRepository("membersF.txt");

		EntryController entryController = new EntryController(entryRepository, memberRepository);
		MemberController memberController = new MemberController(memberRepository);

		UI console = new UI(entryController, memberController);
		console.run();

	}
}
