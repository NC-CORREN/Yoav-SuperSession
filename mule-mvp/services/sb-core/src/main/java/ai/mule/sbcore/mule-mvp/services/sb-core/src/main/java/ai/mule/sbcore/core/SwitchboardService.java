package ai.mule.sbcore.core;

import ai.mule.sbcore.api.UreqModels.IngressDecision;
import ai.mule.sbcore.api.UreqModels.UreqEnvelope;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

/**
 * Minimal Switchboard core for Mule MVP.
 */
@Service
public class SwitchboardService {

    private static final String DEV_CLIN_CODE = "123456";
    private static final String DEV_CLOUT_CODE = "123456";

    public IngressDecision ingress(UreqEnvelope env) {

        String traceId =
                (String) env.meta().getOrDefault("traceId",
                        "sb-" + UUID.randomUUID());

        return new IngressDecision(
                traceId,
                "PASS",
                DEV_CLIN_CODE,
                DEV_CLOUT_CODE,
                Instant.now()
        );
    }
}