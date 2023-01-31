package app.foot.controller.rest;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class UpdatePlayer {
    private Integer id;
    private String name;
    private Boolean isGuardian;
}
