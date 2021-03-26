package exections;

public class CustomerException extends RuntimeException{

    private String CseId;
    private String description;

    public CustomerException(String description) {
        super(description);
        this.description = description;
    }

    public CustomerException() {
    }

    public CustomerException(String cseId, String description) {
        CseId = cseId;
        this.description = description;
    }


    public String getCseId() {
        return CseId;
    }

    public String getDescription() {
        return description;
    }


   
}
