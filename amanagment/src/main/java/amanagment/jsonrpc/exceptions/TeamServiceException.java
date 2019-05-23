package amanagment.jsonrpc.exceptions;


import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcError;

@JsonRpcError(code = -32032, message = "It's not permitted to add new players")
public class TeamServiceException extends Exception {
}