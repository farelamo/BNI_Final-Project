package com.mvp.logservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document("log")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Log {
    @Id
    private String id;

    private String name;

    private Object data;

    protected Date created_at;

    public void setDate(){
        this.created_at = new java.util.Date();
    }
}
