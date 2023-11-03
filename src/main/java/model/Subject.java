package model;

public class Subject {
    private String subjectID;
    private String SubjectName;
    private String credit;

    public Subject(String subjectID, String subjectName, String credit) {
        this.subjectID = subjectID;
        SubjectName = subjectName;
        this.credit = credit;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public String getCredit() {
        return credit;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectID='" + subjectID + '\'' +
                ", SubjectName='" + SubjectName + '\'' +
                ", credit='" + credit + '\'' +
                '}';
    }
}
