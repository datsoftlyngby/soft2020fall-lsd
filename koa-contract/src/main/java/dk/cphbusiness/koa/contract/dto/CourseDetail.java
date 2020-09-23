package dk.cphbusiness.koa.contract.dto;

public class CourseDetail extends CourseIdentifier {
    private String description;
    private CategorySummary category;
    private boolean active = false;


    public CourseDetail(int id, String description, CategorySummary category) {
        super(id);
        this.description = description;
        this.category = category;
        }

    public String getDescription() {
        return description;
        }

    public void setDescription(String description) {
        this.description = description;
        }

    public boolean isActive() {
        return active;
        }

    public void setActive(boolean active) {
        this.active = active;
        }

    public CategorySummary getCategory() {
        return category;
        }

    }
