package dk.cphbusiness.koa.contract.dto;

public class CourseSummary extends CourseIdentifier {
    private final String description;

    public CourseSummary(int id, String description) {
        super(id);
        this.description = description;
        }

    public String getDescription() {
        return description;
        }

    }
