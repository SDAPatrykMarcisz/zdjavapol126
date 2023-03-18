package pl.sda.controller.mvc;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.dto.MoneySenderForm;
import pl.sda.service.MoneyTransferExecutor;

import javax.validation.Valid;

@Controller
@Slf4j
public class MoneyTransferController {

    private final MoneyTransferExecutor moneyTransferExecutor;

    @Autowired
    public MoneyTransferController(MoneyTransferExecutor moneyTransferExecutor) {
        this.moneyTransferExecutor = moneyTransferExecutor;
    }

    @GetMapping(path = "/przelewy")
    public String getTransferForm(Model model){
        model.addAttribute("formObj", new MoneySenderForm());
        return "przelew";
    }

    @PostMapping(path = "/przelewy")
    public String doTransfer(@Valid @ModelAttribute("formObj") MoneySenderForm moneySenderForm, Errors errors){
        if(errors.hasErrors()){
            log.error(errors.toString());
            return "przelew";
        }
        moneyTransferExecutor.send(moneySenderForm.getSenderAccount(), moneySenderForm.getReceiverAccount(), moneySenderForm.getAmountToSend());
        return "redirect:/";
    }
}
