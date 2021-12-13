package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {


    private String userId;

    private String ip;

    private String msg;



}
