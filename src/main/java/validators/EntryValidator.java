package validators;

import exceptions.InvalidEntryException;
import model.Entry;

public class EntryValidator {

    public Entry validate(String typeEntry, String value, String idMember)
            throws InvalidEntryException {
        int idMemberInt;
        try {
            idMemberInt = Integer.parseInt(idMember);
        } catch (NumberFormatException exception) {
            throw new InvalidEntryException("Entry id for member not a number");
        }

        if (idMemberInt <= 0)
            throw new InvalidEntryException("Entry id for member not a positive number");

        int valueInt;
        try {
            valueInt = Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new InvalidEntryException("Entry value not a number");
        }

        if (valueInt <= 0)
            throw new InvalidEntryException("Entry value not a positive number");

        if (typeEntry.isEmpty())
            throw new InvalidEntryException("Entry type is null");

        if (!typeEntry.toLowerCase().equals("cost") && !typeEntry.toLowerCase().equals("income"))
            throw new InvalidEntryException("Entry type is neither cost or income");

        return new Entry(typeEntry, valueInt, idMemberInt);
    }

}
