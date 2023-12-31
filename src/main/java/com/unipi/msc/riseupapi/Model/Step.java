package com.unipi.msc.riseupapi.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Step {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Long position;
    @OneToMany(mappedBy = "step")
    private List<Task> tasks = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
