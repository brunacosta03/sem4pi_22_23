package org.domain.model.exam;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "T_RESOLUTION_SECTION")
public class ResolutionSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resolution_section_id")
    private Long id;
}
