package pl.dormitorymaintenancesystem.utils.dataOutput;

import lombok.Data;

@Data
public class AuthenticationResponseDTO {

    private String token;

    public AuthenticationResponseDTO(String token) {
        this.token = token;
    }
}
