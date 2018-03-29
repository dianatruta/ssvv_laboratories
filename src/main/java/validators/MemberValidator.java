package validators;

import exceptions.InvalidMemberException;
import model.Member;

public class MemberValidator {

    public Member validate(String id, String name) throws InvalidMemberException {
        if (id == null || id.isEmpty())
            throw new InvalidMemberException("Member id is null");

        int idInt;
        try {
            idInt = Integer.parseInt(id);
        } catch (NumberFormatException exception) {
            throw new InvalidMemberException("Member id not a number");
        }

        if (idInt <= 0)
            throw new InvalidMemberException("Member id not a positive number");

        if (name == null || name.isEmpty())
            throw new InvalidMemberException("Member name is null");

        return new Member(name, idInt);
    }

}
