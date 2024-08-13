package kr.co.ordermanagement.presentation.dto;

import kr.co.ordermanagement.domain.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*(1)
@NoArgsConstructor
@AllArgsConstructor
@Getter*/

//(2)
//@NoArgsConstructor
//@AllArgsConstructor
@Getter @Setter
public class ChangeStateRequestDto {

    private State state;

}
