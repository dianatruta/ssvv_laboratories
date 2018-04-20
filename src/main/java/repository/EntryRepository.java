package repository;

import model.Entry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntryRepository {

    private List<Entry> entries;
    private String fileName;

    public EntryRepository() {
        entries = new ArrayList<>();
    }

    public EntryRepository(String fileName) {
        this.fileName = fileName;
        readFromFile();
    }

    public void addEntry(Entry e) {
        entries.add(e);
        this.saveToFile();
    }

    public List<Entry> getAllEntries() {
        return entries;
    }

    public List<Entry> findByIdMember(int idMember) {
        return entries.stream().filter(m -> m.getIdMember() == idMember).collect(Collectors.toList());
    }

    @SuppressWarnings("resource")
    private void readFromFile() {
        try {
            FileReader fileReaderEntry = new FileReader(fileName);
            BufferedReader bufferedReaderEntry = new BufferedReader(fileReaderEntry);
            String currentLineEntry;

            while ((currentLineEntry = bufferedReaderEntry.readLine()) != null) {
                String line[] = currentLineEntry.split(";");
                String typeOfEntry = line[0];
                int valueEntry = Integer.parseInt(line[1]);
                int idEntryMember = Integer.parseInt(line[2]);
                Entry e = new Entry(typeOfEntry, valueEntry, idEntryMember);
                this.entries.add(e);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @SuppressWarnings("resource")
    private void saveToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            for (Entry entry : entries) {
                String line = entry.toString();
                writer.write(line);
                writer.write("\n");
            }
            writer.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

}
