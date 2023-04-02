package uz.pdp.appproblemsolver.payload;

import lombok.Builder;

@Builder
public record TokenDTO(

        String accessToken,
        String refreshToken,
        String tokenType

) {
}
