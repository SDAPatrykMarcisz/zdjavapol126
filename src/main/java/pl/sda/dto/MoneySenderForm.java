package pl.sda.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class MoneySenderForm {

    @NotBlank @Length(max = 26, min = 26)
    String senderAccount;

    @NotBlank @Length(max = 26, min = 26)
    String receiverAccount;

    @NotNull
    BigDecimal amountToSend;
}
