package Server;

import Controller.UserController;
import Model.Request;
import Model.User;
import enums.ParameterKeys;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

import static enums.RequestActions.*;

public class ServerMain {
    private static final int SERVER_PORT = 7777;
    private static final HashMap<Long, User> threadIDUser = new HashMap<>();
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
//                            System.out.println("users: " + threadIDUser);
                            if (action.equals(LOGIN.code)) {
                                sendEnumRequest(UserController.login(params.get(ParameterKeys.USERNAME.code), params.get(ParameterKeys.PASSWORD.code)), objectOutputStream);
                            } else if (action.equals(REGISTER.code)) {
                                sendEnumRequest(UserController.register(params.get(ParameterKeys.USERNAME.code),
                                        params.get(ParameterKeys.PASSWORD.code),
                                        params.get(ParameterKeys.NICKNAME.code), loggedInUser), objectOutputStream);
                            } else if (action.equals(REMOVE_USER.code)) {
                                sendEnumRequest(UserController.removeUser(), objectOutputStream);
                            } else if (action.equals(CHANGE_PROFILE_PICTURE.code)) {
                                sendEnumRequest(UserController.changePicture((File) request.getObj()), objectOutputStream);
                            } else if (action.equals(GET_THIS_USER.code)) {
                                sendRequest(new Request("sent this User", null, UserController.getCurrentUser()), objectOutputStream);
                            } else if (action.equals(CHANGE_PASSWORD.code)) {
                                sendEnumRequest(UserController.changePassword(params.get(ParameterKeys.OLD_PASSWORD.code), params.get(ParameterKeys.NEW_PASSWORD.code)), objectOutputStream);
                            } else if (action.equals(CHANGE_NICKNAME.code)) {
                                sendEnumRequest(UserController.changeNickname(params.get(ParameterKeys.NICKNAME.code)), objectOutputStream);
                            } else {
                                System.err.println("INVALID COMMAND!!!");
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
        sendRequest(new Request(thisEnum.toString(), null, thisEnum), objectOutputStream);
    }

    private static synchronized void sendRequest(Request request, ObjectOutputStream objectOutputStream) {
        try {
            System.out.println("Response:  action:" + request.action + " params: " + request.params);
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void addThreadUser(User user) {
        threadIDUser.put(Thread.currentThread().getId(), user);
        System.out.println("user add to: " + threadIDUser);
    }

    public static synchronized User getThisThreadUser() {
        return threadIDUser.get(Thread.currentThread().getId());
    }

    public static synchronized void removeThisThreadUser() {
        threadIDUser.remove(Thread.currentThread().getId());
    }

}
