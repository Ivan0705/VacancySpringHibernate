package academits.demo.dto;

public class Name {
    private double profarea_id;
    private String profarea_name;
    private double id;
    private String name;

    public Name() {
    }

    public Name(double profarea_id, String profarea_name, double id, String name) {
        this.profarea_id = profarea_id;
        this.profarea_name = profarea_name;
        this.id = id;
        this.name = name;
    }

    public String getProfarea_id() {
        return Double.toString(profarea_id);
    }

    public void setProfarea_id(int profarea_id) {
        this.profarea_id = profarea_id;
    }

    public String getProfarea_name() {
        return profarea_name;
    }

    public void setProfarea_name(String profarea_name) {
        this.profarea_name = profarea_name;
    }

    public String geiId() {
        return Double.toString(id);
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\"profarea_id\":\"" + profarea_id + "\", \"profarea_name\":\"" + this.profarea_name + "\", \"id\":\"" + this.id + "\", \"name\":\"" + this.name + "\"";
    }
}
