package amanagment.jsonrpc.exceptions;

import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcError;

@JsonRpcError(code = -32033, message = "Element not found")
public class NotFoundException extends Exception {
}
