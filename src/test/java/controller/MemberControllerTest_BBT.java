package controller;

import exceptions.InvalidMemberException;
import exceptions.MemberAlreadyExistsException;
import model.Member;
import org.junit.Before;
import org.junit.Test;
import repository.MemberRepository;
import validators.MemberValidator;

import static org.junit.Assert.assertEquals;

public class MemberControllerTest_BBT {

    private MemberController memberController;

    @Before
    public void setUp() throws Exception {
        MemberValidator memberValidator = new MemberValidator();
        MemberRepository memberRepository = new MemberRepository();
        memberController = new MemberController(memberRepository, memberValidator);
    }

    @Test(expected = InvalidMemberException.class)
    public void addMember_invalidIdMember_throwsException() throws InvalidMemberException,
            MemberAlreadyExistsException {
        Member expectedResult = new Member("name", 0);
        memberController.addMember(String.valueOf(expectedResult.getId()), expectedResult.getName());
    }

    @Test
    public void addMember_validMember_ok() throws InvalidMemberException, MemberAlreadyExistsException {
        Member expectedResult = new Member("name", 1);
        Member result = memberController.addMember(String.valueOf(expectedResult.getId()), expectedResult.getName());
        assertEquals(expectedResult, result);
    }

    @Test(expected = InvalidMemberException.class)
    public void addMember_invalidNameMember_throwsException() throws InvalidMemberException,
            MemberAlreadyExistsException {
        Member expectedResult = new Member("", 1);
        memberController.addMember(String.valueOf(expectedResult.getId()), expectedResult.getName());
    }

    @Test(expected = InvalidMemberException.class)
    public void addMember_nullIdMember_throwsException() throws InvalidMemberException,
            MemberAlreadyExistsException {
        memberController.addMember(null, "name");
    }

    @Test(expected = InvalidMemberException.class)
    public void addMember_nullNameMember_throwsException() throws InvalidMemberException,
            MemberAlreadyExistsException {
        memberController.addMember("1", null);
    }

    @Test(expected = InvalidMemberException.class)
    public void addMember_doubleIdMember_throwsException() throws InvalidMemberException, MemberAlreadyExistsException {
        memberController.addMember("2.3", "name");
    }

    @Test(expected = InvalidMemberException.class)
    public void addMember_validIdNullNameMember_throwsException() throws InvalidMemberException, MemberAlreadyExistsException {
        memberController.addMember("2", null);
    }

    @Test(expected = MemberAlreadyExistsException.class)
    public void addMember_alreadyExistMember_throwsException() throws InvalidMemberException,
            MemberAlreadyExistsException {
        Member expectedResult = new Member("name", 1);
        memberController.addMember(String.valueOf(expectedResult.getId()), expectedResult.getName());
        memberController.addMember(String.valueOf(expectedResult.getId()), expectedResult.getName());
    }

}
