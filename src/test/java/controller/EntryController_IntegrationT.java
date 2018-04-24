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

import static org.junit.Assert.assertEquals;

public class EntryController_IntegrationT {

    private EntryController entryController;
    private MemberRepository memberRepository;
    private MemberController memberController;

    @Before
    public void setUp() throws Exception {
        EntryValidator entryValidator = new EntryValidator();
        EntryRepository entryRepository = new EntryRepository();
        memberRepository = new MemberRepository();
        entryController = new EntryController(entryRepository, memberRepository, entryValidator);
        MemberValidator memberValidator = new MemberValidator();
        memberController = new MemberController(memberRepository, memberValidator);
    }

    @Test
    public void getEntriesForMember_validIdMember_ok() throws MemberAlreadyExistsException, InvalidEntryException,
            MemberDoesNotExistException {
        String typeEntry = "cost";
        String value = "1";
        String idMember = "1";
        addMember("name", 1);
        entryController.addEntry(typeEntry, value, idMember);
        entryController.getEntriesFor(Integer.parseInt(idMember));
    }

    @Test
    public void addEntry_validEntry_ok()
            throws InvalidEntryException, MemberDoesNotExistException, MemberAlreadyExistsException {
        String typeEntry = "cost";
        String value = "1";
        String idMember = "2";
        addMember("name", 2);
        entryController.addEntry(typeEntry, value, idMember);
    }

    @Test
    public void addMember_validMember_ok() throws InvalidMemberException, MemberAlreadyExistsException {
        Member expectedResult = new Member("name", 3);
        Member result = memberController.addMember(String.valueOf(expectedResult.getId()), expectedResult.getName());
        assertEquals(expectedResult, result);
    }

    @Test
    public void integrate() throws MemberAlreadyExistsException, InvalidEntryException, MemberDoesNotExistException,
            InvalidMemberException {
        getEntriesForMember_validIdMember_ok();
        addEntry_validEntry_ok();
        addMember_validMember_ok();
    }

    private void addMember(String name, int id) throws MemberAlreadyExistsException {
        Member member = new Member(name, id);
        memberRepository.addMember(member);
    }
}
