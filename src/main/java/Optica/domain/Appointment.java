package Optica.domain;

import java.util.Date;

public class Appointment {
    private int id;
    private Date date;
    private String username;
    private String reason;
    private Date creationDate;

    public Appointment() {}

    public Appointment(String username, Date date, String reason, Date creationDate) {
        this.date = date;
        this.username = username;
        this.reason = reason;
        this.creationDate = creationDate;
    }

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getReason() {return reason;}

    public void setReason(String reason) {this.reason = reason;}

    public Date getCreationDate() {return creationDate;}

    public void setCreationDate(Date creationDate) {this.creationDate = creationDate;}
}
