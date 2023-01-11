package app.project.youbook.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer roomNumber;
    private String description;
    private String type;
    private Integer numberOfBeds;
    private Integer capacity;
    private Integer price;


    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room", orphanRemoval = true)
    private Set<Reservation> reservations = new LinkedHashSet<>();
    @JsonIgnore
    public Hotel getHotel() {
        return hotel;
    }
    @JsonSetter
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
