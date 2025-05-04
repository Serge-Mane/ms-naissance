package tech.sam.ms_naissances.profiles;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProfileDTO {
    Civility civility;
    String firstName;
    String lastName;
    String email;
    String phone;
    String password;
    String role;
    LocalDateTime birthDate;
}
