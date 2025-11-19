package ai.mule.sbcore.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Map;

/**
 * Minimal UREQ envelope + ingress response for the Mule MVP.
 */
public final class UreqModels {

    private UreqModels() {
    }

    /** Basic UREQ-style envelope. */
    public record UreqEnvelope(
            @NotBlank String requestingParty,
            @NotBlank String requestedParty,
            @NotBlank String service,
            @NotNull Integer priority,
            @NotNull Map<String, Object> meta,
            @NotNull Map<String, Object> payload
    ) {}

    /** Switchboard ingress response. */
    public record IngressDecision(
            String traceId,
            String decision,      // PASS | REVIEW | BLOCK
            String clinCode,      // dev: always "123456"
            String cloutCode,     // dev: always "123456"
            Instant ts
    ) {}
}