package tech.sam.ms_naissances.profiles;

public record ProfilesDTO(
         Civility civility,
         String firstName,
         String lastName,
         String email,
         String phone,
         String password
) {

}
