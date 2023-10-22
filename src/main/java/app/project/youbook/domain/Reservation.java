package app.project.youbook.domain;

import app.project.youbook.Enum.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private Date startDate;
    private Date endDate;
    private Double totalPrice;
    private ReservationStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @JsonIgnore
    public User getUser() {
        return user;
    }
    @JsonSetter
    public void setUser(User user) {
        this.user = user;
    }
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;



}
