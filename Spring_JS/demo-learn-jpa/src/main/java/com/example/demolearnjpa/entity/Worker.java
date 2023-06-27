package com.example.demolearnjpa.entity;

import com.example.demolearnjpa.dto.WorkerDto;
import jakarta.persistence.*;
import lombok.*;

@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "resultInfo",
                classes = @ConstructorResult(
                        targetClass = WorkerDto.class,
                        columns = {
                                @ColumnResult(name = "id"),
                                @ColumnResult(name = "name"),
                                @ColumnResult(name = "email")
                        }
                )
        )
})
@NamedNativeQuery(
        name = "getWorkerDtoUseNativeQuery",
        resultSetMapping = "resultInfo",
        query = """
            select id, name, email from  worker where email = ?1
                """
)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "worker")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Name", nullable = false)
    private String name;

    private String email;

    private String password;

}
