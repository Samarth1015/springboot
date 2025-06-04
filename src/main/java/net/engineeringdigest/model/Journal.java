package net.engineeringdigest.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "journal_entries")
@Data
@NoArgsConstructor
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private  String title;

    @Column(nullable = false)
    private String content;

    private Date date;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;






}
