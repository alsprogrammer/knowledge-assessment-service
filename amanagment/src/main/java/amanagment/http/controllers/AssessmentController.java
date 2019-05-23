package amanagment.http.controllers;

import amanagment.jsonrpc.requestprocessors.IJsonRpcRequestProcessor;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import javax.inject.Inject;

@Controller("/api/v1")
public class AssessmentController {
    private IJsonRpcRequestProcessor jsonRpcRequestProcessor;

    @Inject
    public AssessmentController(IJsonRpcRequestProcessor jsonRpcRequestProcessor) {
        this.jsonRpcRequestProcessor = jsonRpcRequestProcessor;
    }

    @Get("/")
    @Produces(MediaType.TEXT_JSON)
    @Consumes(MediaType.TEXT_JSON)
    public String getIndex(String request) {
        return jsonRpcRequestProcessor.processRequest(request);
    }

    @Post("/")
    @Produces(MediaType.TEXT_JSON)
    @Consumes(MediaType.TEXT_JSON)
    public String index(@Body String request) {
        return jsonRpcRequestProcessor.processRequest(request);
    }
}
