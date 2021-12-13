package entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginEvent {


    private String userId;

    private String message;

    private Integer timestamp;


}
