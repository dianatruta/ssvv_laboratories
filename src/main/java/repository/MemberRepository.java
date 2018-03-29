package repository;

import exceptions.MemberAlreadyExistsException;
import model.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberRepository {
    private List<Member> members;

    private String filename;

    public MemberRepository() {
        members = new ArrayList<>();
    }

    public MemberRepository(String fileName) {
        this.members = new ArrayList<>();
        this.filename = fileName;
        readFromFile();
    }

    public Member addMember(Member m) throws MemberAlreadyExistsException {
        if (checkIfExists(m.getId())) {
            throw new MemberAlreadyExistsException();
        } else {
            members.add(m);
            this.saveToFile();
            return m;
        }
    }

    public boolean checkIfExists(int idOfMember) {
        Optional<Member> tempMember = members.stream().filter(m -> m.getId() == idOfMember).findFirst();
        return tempMember.isPresent();
    }

    @SuppressWarnings("resource")
    private void readFromFile() {
        try {
            FileReader fileReader;
            BufferedReader bufferedReader;
            String currentLine;

            fileReader = new FileReader(this.filename);
            bufferedReader = new BufferedReader(fileReader);

            while ((currentLine = bufferedReader.readLine()) != null) {
                String line[] = currentLine.split(";");
                Member m = new Member(line[0], Integer.parseInt(line[1]));
                this.members.add(m);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @SuppressWarnings("resource")
    private void saveToFile() {
        try {
//            File f = new File(this.filename);
//            if (!f.exists())
//                f.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filename));

            for (Member member : members) {
                String line = member.toString();
                writer.write(line);
                writer.write("\n");
            }
            writer.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

}
