package controller;

import exceptions.InvalidMemberException;
import exceptions.MemberAlreadyExistsException;
import model.Member;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import repository.MemberRepository;
import validators.MemberValidator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MemberControllerTest_WBT {

    @Mock
    private MemberValidator memberValidator;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberController memberController;

    @Before
    public void setUp() throws Exception {

    }

    @Test(expected = InvalidMemberException.class)
    public void addMember_negativeIdMember_throwsException()
            throws InvalidMemberException, MemberAlreadyExistsException {
        Member member = new Member("aa", -1);
        when(memberValidator.validate(String.valueOf(member.getId()), member.getName()))
                .thenThrow(InvalidMemberException.class);
        memberController.addMember(String.valueOf(member.getId()), member.getName());
    }

    @Test(expected = InvalidMemberException.class)
    public void addMember_stringIdMember_throwsException()
            throws InvalidMemberException, MemberAlreadyExistsException {
        String name = "name";
        String id = "id";
        when(memberValidator.validate(id, name))
                .thenThrow(InvalidMemberException.class);
        memberController.addMember(id, name);
    }

    @Test
    public void addMember_validMember_nothing() throws InvalidMemberException, MemberAlreadyExistsException {
        Member expectedResult = new Member("aa", 1);
        when(memberValidator.validate(String.valueOf(expectedResult.getId()), expectedResult.getName()))
                .thenReturn(expectedResult);
        when(memberRepository.addMember(expectedResult))
                .thenReturn(expectedResult);
        Member result = memberController.addMember(String.valueOf(expectedResult.getId()), expectedResult.getName());
        assertEquals(expectedResult, result);
    }

}
