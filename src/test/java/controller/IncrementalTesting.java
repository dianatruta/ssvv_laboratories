package controller;

import exceptions.InvalidEntryException;
import exceptions.InvalidMemberException;
import exceptions.MemberAlreadyExistsException;
import exceptions.MemberDoesNotExistException;
import model.Member;
import org.junit.Before;
import org.junit.Test;
import repository.EntryRepository;
import repository.MemberRepository;
import validators.EntryValidator;
import validators.MemberValidator;

import static org.mockito.Mockito.when;

public class IncrementalTesting {

    private EntryController entryController;
    private MemberRepository memberRepository;
    private MemberController memberController;
    private MemberValidator memberValidator;


    @Before
    public void setUp() throws Exception {
        EntryValidator entryValidator = new EntryValidator();
        EntryRepository entryRepository = new EntryRepository();
        memberRepository = new MemberRepository();
        entryController = new EntryController(entryRepository, memberRepository, entryValidator);
        memberValidator = new MemberValidator();
        memberController = new MemberController(memberRepository, memberValidator);
    }

    @Test(expected = InvalidMemberException.class)
    public void addMember_negativeIdMember_throwsException()
            throws InvalidMemberException, MemberAlreadyExistsException {
        Member member = new Member("aa", -1);
        when(memberValidator.validate(String.valueOf(member.getId()), member.getName()))
                .thenThrow(InvalidMemberException.class);
        memberController.addMember(String.valueOf(member.getId()), member.getName());
    }

    @Test(expected = InvalidEntryException.class)
    public void addEntry_stringIdMember_throwsException() throws InvalidEntryException, MemberDoesNotExistException,
            InvalidMemberException, MemberAlreadyExistsException {
        String typeEntry = "";
        String value = "value";
        String idMember = "idMember";
        entryController.addEntry(typeEntry, value, idMember);
        addMember_negativeIdMember_throwsException();
    }

    @Test(expected = InvalidEntryException.class)
    public void getEntriesForMember_validIdMember_throwsException() throws MemberAlreadyExistsException, InvalidEntryException,
            MemberDoesNotExistException, InvalidMemberException {
        String typeEntry = "cost";
        String value = "1";
        String idMember = "1";
        addMember("name", 1);
        entryController.addEntry(typeEntry, value, idMember);
        entryController.getEntriesFor(Integer.parseInt(idMember));
        addEntry_stringIdMember_throwsException();
    }

    private void addMember(String name, int id) throws MemberAlreadyExistsException {
        Member member = new Member(name, id);
        memberRepository.addMember(member);
    }

}
