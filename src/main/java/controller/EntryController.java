package controller;

import exceptions.InvalidEntryException;
import exceptions.MemberDoesNotExistException;
import model.Entry;
import repository.EntryRepository;
import repository.MemberRepository;
import validators.EntryValidator;

import java.util.List;

public class EntryController {

    private EntryRepository entryRepository;
    private MemberRepository memberRepository;
    private EntryValidator entryValidator;

    public EntryController(EntryRepository entryRepository, MemberRepository memberRepository,
                           EntryValidator entryValidator) {
        this.entryRepository = entryRepository;
        this.memberRepository = memberRepository;
        this.entryValidator = entryValidator;
    }

    public void addEntry(String typeEntry, String value, String idMember)
            throws MemberDoesNotExistException, InvalidEntryException {
        Entry oneEntry = this.entryValidator.validate(typeEntry, value, idMember);
        if (memberRepository.checkIfExists(oneEntry.getIdMember())) {
            this.entryRepository.addEntry(oneEntry);
        } else {
            throw new MemberDoesNotExistException();
        }
    }

    public List<Entry> allEntries() {
        return this.entryRepository.getAllEntries();
    }

    public List<Entry> getEntriesFor(int idMember) throws MemberDoesNotExistException {
        if (memberRepository.checkIfExists(idMember)) {
            return entryRepository.findByIdMember(idMember);
        } else {
            throw new MemberDoesNotExistException();
        }
    }

}
