package Server;

import Controller.UserController;
import Model.Request;
import enums.ParameterKeys;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

import static enums.RequestActions.*;

public class ServerMain {
    private static final int SERVER_PORT = 7777;

    public static void main(String[] args) {
        ServerSocket ss;
        try {
            ss = new ServerSocket(SERVER_PORT);
            System.out.println("server started: " + ss);
            while (true) {
                Socket socket = ss.accept();
                System.out.println("Connected: " + socket);
                new Thread(() -> {
                    ThreadLocal loggedInUser = new ThreadLocal();
                    ThreadLocal enteredClass = new ThreadLocal();
                    try {
                        InputStream inputStream = socket.getInputStream();
                        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                        OutputStream outputStream = socket.getOutputStream();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                        while (true) {
                            Request request = (Request) objectInputStream.readObject();
                            String action = request.action;
                            HashMap<String, String> params = request.params;
                            System.out.println(request);
                            if (action.equals(LOGIN.code)) {
                                sendEnumRequest(UserController.login(params.get(ParameterKeys.USERNAME.code), params.get(ParameterKeys.PASSWORD.code)), objectOutputStream);
                            } else if (action.equals(REGISTER.code)) {
                                sendEnumRequest(UserController.register(params.get(ParameterKeys.USERNAME.code), params.get(ParameterKeys.PASSWORD.code), params.get(ParameterKeys.NICKNAME.code)), objectOutputStream);
                            } else if (action.equals(REMOVE_USER.code)) {
                                sendEnumRequest(UserController.removeUser(), objectOutputStream);
                            } else if (action.equals(CHANGE_PROFILE_PICTURE.code)) {
                                sendEnumRequest(UserController.changePicture(request.getFile()), objectOutputStream);
                            }
                        }

                    } catch (SocketException e) {
                        System.out.println("Disconnected: " + socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendEnumRequest(Enum thisEnum, ObjectOutputStream objectOutputStream) {
        HashMap<String, String> params = new HashMap<>();
        params.put(ParameterKeys.ENUM.code, String.valueOf(thisEnum.ordinal()));
        sendRequest(new Request(thisEnum.toString(), params), objectOutputStream);
    }

    private static synchronized void sendRequest(Request request, ObjectOutputStream objectOutputStream) {
        try {
            System.out.println("Response: " + request.action + request.params);
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
