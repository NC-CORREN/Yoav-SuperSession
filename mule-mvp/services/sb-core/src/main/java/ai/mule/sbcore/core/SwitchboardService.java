package ai.mule.sbcore.core;

import ai.mule.sbcore.api.UreqModels.IngressDecision;
import ai.mule.sbcore.api.UreqModels.UreqEnvelope;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * Minimal Switchboard core for Mule MVP.
 *
 * - Validates basic fields.
 * - Assigns a traceId.
 * - Applies a *local* CLIN/CLOUT stub that always returns "123456".
 * - Decision is always PASS for MVP (real policies can be added later).
 */
@Service
public class SwitchboardService {

    private static final String DEV_CLIN_CODE = "123456";
    private static final String DEV_CLOUT_CODE = "123456";

    public IngressDecision ingress(UreqEnvelope env) {
        // In a fuller version we would:
        // - run structure checks
        // - talk to CLIN/CLOUT over the network
        // Here we simply generate a trace id and PASS.

        String traceId = (String) env.meta().getOrDefault(
                "traceId",
                "sb-" + UUID.randomUUID()
        );

        return new IngressDecision(
                traceId,
                "PASS",
                DEV_CLIN_CODE,
                DEV_CLOUT_CODE,
                Instant.now()
        );
    }
}