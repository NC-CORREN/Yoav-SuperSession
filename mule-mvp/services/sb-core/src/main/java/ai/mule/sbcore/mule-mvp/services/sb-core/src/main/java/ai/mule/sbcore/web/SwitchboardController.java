package ai.mule.sbcore.web;

import ai.mule.sbcore.api.UreqModels.IngressDecision;
import ai.mule.sbcore.api.UreqModels.UreqEnvelope;
import ai.mule.sbcore.core.SwitchboardService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * HTTP front-door for the Switchboard core.
 */
@RestController
@RequestMapping("/sb")
public class SwitchboardController {

    private final SwitchboardService service;

    public SwitchboardController(SwitchboardService service) {
        this.service = service;
    }

    @PostMapping("/ingress")
    public ResponseEntity<IngressDecision> ingress(
            @Valid @RequestBody UreqEnvelope env) {

        IngressDecision decision = service.ingress(env);
        return ResponseEntity.ok(decision);
    }
}