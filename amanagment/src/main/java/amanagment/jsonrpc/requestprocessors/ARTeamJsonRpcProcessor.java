package amanagment.jsonrpc.requestprocessors;

import amanagment.services.IAssessmentService;
import com.github.arteam.simplejsonrpc.server.JsonRpcServer;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ARTeamJsonRpcProcessor implements IJsonRpcRequestProcessor {
    private final JsonRpcServer server = new JsonRpcServer();
    private final IAssessmentService assessmentService;

    @Inject
    public ARTeamJsonRpcProcessor(IAssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @Override
    public String processRequest(String request) {
        return server.handle(request, assessmentService);
    }
}
