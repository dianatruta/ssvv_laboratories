package controller;

import exceptions.InvalidMemberException;
import exceptions.MemberAlreadyExistsException;
import model.Member;
import repository.MemberRepository;
import validators.MemberValidator;

public class MemberController {

    private MemberValidator memberValidator = new MemberValidator();

    private MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository, MemberValidator memberValidator) {
        this.memberRepository = memberRepository;
        this.memberValidator = memberValidator;
    }

    public Member addMember(String id, String name) throws MemberAlreadyExistsException, InvalidMemberException {
        Member newMember = memberValidator.validate(id, name);
        return memberRepository.addMember(newMember);
    }

}