package entities;

public class Category {
    private String name;
    private String description;

    public Category(String name, String description) throws NullPointerException
    {
        if((name == null) || (description == null)){
            throw new NullPointerException("name can not be null");
        }
        this.name = name.toLowerCase();
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
