package controller;

import exceptions.InvalidEntryException;
import exceptions.MemberAlreadyExistsException;
import exceptions.MemberDoesNotExistException;
import model.Member;
import org.junit.Before;
import org.junit.Test;
import repository.EntryRepository;
import repository.MemberRepository;
import validators.EntryValidator;

public class EntryControllerTest_WBT {

    private EntryController entryController;
    private MemberRepository memberRepository;

    @Before
    public void setUp() throws Exception {
        EntryValidator entryValidator = new EntryValidator();
        EntryRepository entryRepository = new EntryRepository();
        memberRepository = new MemberRepository();
        entryController = new EntryController(entryRepository, memberRepository, entryValidator);
    }

    @Test(expected = InvalidEntryException.class)
    public void addEntry_stringIdMember_throwsException() throws InvalidEntryException, MemberDoesNotExistException {
        String typeEntry = "";
        String value = "value";
        String idMember = "idMember";
        entryController.addEntry(typeEntry, value, idMember);
    }

    @Test(expected = InvalidEntryException.class)
    public void addEntry_zeroIdMember_throwsException() throws InvalidEntryException, MemberDoesNotExistException {
        String typeEntry = "";
        String value = "value";
        String idMember = "0";
        entryController.addEntry(typeEntry, value, idMember);
    }

    @Test(expected = InvalidEntryException.class)
    public void addEntry_stringValue_throwsException()
            throws InvalidEntryException, MemberDoesNotExistException {
        String typeEntry = "";
        String value = "value";
        String idMember = "1";
        entryController.addEntry(typeEntry, value, idMember);
    }

    @Test(expected = InvalidEntryException.class)
    public void addEntry_zeroValue_throwsException()
            throws InvalidEntryException, MemberDoesNotExistException {
        String typeEntry = "";
        String value = "0";
        String idMember = "1";
        entryController.addEntry(typeEntry, value, idMember);
    }

    @Test(expected = InvalidEntryException.class)
    public void addEntry_emptyTypeEntry_throwsException()
            throws InvalidEntryException, MemberDoesNotExistException {
        String typeEntry = "";
        String value = "1";
        String idMember = "1";
        entryController.addEntry(typeEntry, value, idMember);
    }

    @Test(expected = InvalidEntryException.class)
    public void addEntry_nonExistingTypeEntry_throwsException()
            throws InvalidEntryException, MemberDoesNotExistException {
        String typeEntry = "typeEntry";
        String value = "1";
        String idMember = "1";
        entryController.addEntry(typeEntry, value, idMember);
    }

    @Test
    public void addEntry_validEntry_ok()
            throws InvalidEntryException, MemberDoesNotExistException, MemberAlreadyExistsException {
        String typeEntry = "cost";
        String value = "1";
        String idMember = "1";
        addMember();
        entryController.addEntry(typeEntry, value, idMember);
    }

    @Test(expected = MemberDoesNotExistException.class)
    public void addEntry_memberNonExistent_throwsException()
            throws InvalidEntryException, MemberDoesNotExistException, MemberAlreadyExistsException {
        String typeEntry = "cost";
        String value = "1";
        String idMember = "100";
        addMember();
        entryController.addEntry(typeEntry, value, idMember);
    }

    private void addMember() throws MemberAlreadyExistsException {
        Member member = new Member("name", 1);
        memberRepository.addMember(member);
    }

}
