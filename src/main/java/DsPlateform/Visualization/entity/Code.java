package DsPlateform.Visualization.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codeId;

    private String codeTitle;
    private String codeDescription;
    private String writtenCode;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // foreign key column in 'Code' table
    private UserInfo user;
}
