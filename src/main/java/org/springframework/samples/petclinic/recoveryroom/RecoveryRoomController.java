package org.springframework.samples.petclinic.recoveryroom;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecoveryRoomController {

    private static final String VIEWS_RECOVERYROOM_CREATE_OR_UPDATE_FORM = "recoveryroom/createOrUpdateRecoveryRoomForm";
	
	private RecoveryRoomService recoveryRoomService;

    @Autowired
    public RecoveryRoomController(RecoveryRoomService rService) {
        this.recoveryRoomService = rService;
    }

    @GetMapping(value = "/recoveryroom/create")
	public String initCreationForm(ModelMap model) {
		RecoveryRoom rr = new RecoveryRoom();
        model.put("recoveryRoom", rr);
		return VIEWS_RECOVERYROOM_CREATE_OR_UPDATE_FORM;
	}

    @PostMapping(value = "/recoveryroom/create")
	public String processCreationForm(@Valid RecoveryRoom rr, BindingResult result, ModelMap model) throws DuplicatedRoomNameException {		
		if (result.hasErrors()) {
			model.put("recoveryRoom", rr);
			return VIEWS_RECOVERYROOM_CREATE_OR_UPDATE_FORM;
		}
		else {
            this.recoveryRoomService.save(rr);
            return "welcome";
		}
	}

    
}
