package org.shared.board.server;

import com.google.gson.Gson;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.apache.commons.httpclient.auth.InvalidCredentialsException;
import org.authz.application.AuthorizationService;
import org.shared.board.server.request_bodys.BoardBody;
import org.shared.board.server.request_bodys.LoginBody;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;

public class HttpRequestThread extends Thread {
    String baseFolder;
    Socket sock;
    DataInputStream inS;
    DataOutputStream outS;
    HttpServerAjax httpServerAjax;
    AuthorizationService authz;
    Gson json;


    public HttpRequestThread(Socket httpCliSock, String folder, AuthorizationService authzp) {
        this.baseFolder = folder;
        this.sock = httpCliSock;
        this.authz = authzp;
        this.json = new Gson();
        this.httpServerAjax = new HttpServerAjax(authzp);
    }

    @Override
    public void run() {
        try {
            outS = new DataOutputStream(sock.getOutputStream());
            inS = new DataInputStream(sock.getInputStream());
        } catch (IOException ex){
            System.out.println("Thread error on data streams creation");
        }

        try {
            HTTPmessage request = new HTTPmessage(inS);
            HTTPmessage response = new HTTPmessage();
            System.out.println(request.getURI());

            if(request.getMethod().equals("GET")) {
                if(request.getURI().equals("/myboards")) {
                    String fullname = baseFolder + "/myboards.html";

                    if(response.setContentFromFile(fullname)) {
                        response.setResponseStatus("200 Ok");
                    } else {
                        fileNotFound(response);
                    }

                    response.setResponseStatus("200 Ok");
                    response.send(outS);
                }

                if(request.getURI().equals("/user")) {
                    try{
                        response.setContentFromString(
                                httpServerAjax.getAuthenticatedUser(), "text");
                        response.setResponseStatus("200 Ok");
                    } catch (NoSuchElementException e){
                        response.setResponseStatus("401 unauthorized");
                    }

                } else {
                    String fullname = baseFolder + "/";

                    if(request.getURI().equals("/")){
                        fullname = fullname + "login.html";
                    } else {
                        fullname = fullname + request.getURI();
                    }

                    if(response.setContentFromFile(fullname)) {
                        response.setResponseStatus("200 Ok");
                    } else {
                        fileNotFound(response);
                    }
                }
                response.send(outS);
            }

            if(request.getMethod().equals("POST")){
                if(request.getURI().equals("/create_board")){
                    createBoard(request, response);
                }

                if(request.getURI().equals("/login")){
                    String requestBody = request.getContentAsString();

                    LoginBody body = json.fromJson(requestBody, LoginBody.class);

                    try{
                        response.setContentFromString(
                                httpServerAjax.login(body),
                                "text");
                        response.setResponseStatus("200 Ok");
                    } catch (InvalidCredentialsException e){
                        response.setContentFromString(
                                e.getMessage(),
                                "text");
                        response.setResponseStatus("401 Unauthorized");
                    }

                    response.send(outS);
                }
            }


        } catch(IOException ex) {
            System.out.println("Thread error when reading request");
        }

        try {
            sock.close();
        } catch(IOException ex) {
            System.out.println("CLOSE IOException");
        }
    }

    public void createBoard(HTTPmessage request, HTTPmessage response){
        try {
            String requestBody = request.getContentAsString();

            BoardBody body = json.fromJson(requestBody, BoardBody.class);

            try{
                response.setContentFromString(
                        httpServerAjax.createBoard(body),
                        "text");
                response.setResponseStatus("200 Ok");
            } catch (IntegrityViolationException e){
                response.setContentFromString(
                        "A board with that title already exists",
                        "text");
                response.setResponseStatus("409 Conflict");
            } catch (NumberFormatException e){
                response.setContentFromString(
                        "Problem with request",
                        "text");
                response.setResponseStatus("400 Bad Request");
            } catch (IllegalArgumentException e){
                response.setContentFromString(
                        e.getMessage(),
                        "text");
                response.setResponseStatus("400 Bad Request");
            }

            response.send(outS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void fileNotFound(HTTPmessage response){
        response.setContentFromString(
                "<html><body><h1>404 File not found</h1></body></html>",
                "text/html");
        response.setResponseStatus("404 Not Found");
    }
}


