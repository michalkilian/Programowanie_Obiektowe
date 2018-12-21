package SzymonServer;

import SzymonServer.exceptions.WrongCommandException;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SzymonServerThread extends Thread {
    private Socket socket;
    private String output = "";
    private Scanner in;
    private PrintStream out;

    SzymonServerThread(Socket clientSocket) throws IOException {
        this(clientSocket.getOutputStream(), clientSocket.getInputStream());
        this.socket = clientSocket;

    }

    private SzymonServerThread(OutputStream output, InputStream input) {
        in = new Scanner(input);
        out = new PrintStream(output);
    }

    public void run() {


        String command = null;

        while ((!Thread.currentThread().isInterrupted()) && in.hasNextLine()) {
            try {
                command = in.nextLine();
                try {
                    String[] commandOut = handleCommand(command).split(";");
                    String result = commandOut[0];
                    int commandId = Integer.parseInt(commandOut[1]);
                    if (result.equals("success")) {
                        switch (commandId) {
                            case 1:
                                String id = generateId();
                                Server.loggedUsers.add(id);
                                output = id;
                                break;
                            case 2:
                                output = "true";
                                out.println(output);
                                out.close();
                                socket.close();
                                return;
                            case 3:
                                output = "";
                                for (String s : Server.files) {
                                    output += s + ";";
                                }
                                break;
                            case 4:
                                output = getFileContent(command.split(" ")[2]);
                                break;
                        }
                    } else {
                        switch (commandId) {
                            case 0:
                                output = "Zła nazwa użytkownika";
                                out.println(output);
                                out.close();
                                socket.close();
                                return;
                            case 1:
                                output = "Złe hasło. Odległość: " + calculatePasswordDistance(command.split(" ")[1].split(";")[1]);
                                out.print(output);
                                out.close();
                                socket.close();
                                return;
                            case 2:
                                output = "false";
                                out.println(output);
                                out.close();
                                socket.close();
                                return;
                            case 3:
                                output = "false";
                                out.println(output);
                                out.close();
                                socket.close();
                                return;
                            case 4:
                                output = "false";
                                out.println(output);
                                out.close();
                                socket.close();
                                return;
                        }

                    }

                    out.println(output);
                    out.flush();
                } catch (WrongCommandException e) {
                    out.close();
                    socket.close();
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    private int calculatePasswordDistance(String userPassword) throws WrongCommandException {
        String a = userPassword.toLowerCase();
        String b = Server.password;
        int[] costs = new int[b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }

    private String getFileContent(String s) throws WrongCommandException {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(s).getFile());
            BufferedReader br = new BufferedReader(new FileReader(file));
            return br.readLine();
        } catch (FileNotFoundException e) {
            throw new WrongCommandException("Błędny plik");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateId() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvwxyz"
                + "0123456789";
        return new Random().ints(10, 0, chars.length())
                .mapToObj(i -> "" + chars.charAt(i))
                .collect(Collectors.joining());
    }


    private String handleCommand(String command) throws WrongCommandException {
        String output = "";
        String comm = "";
        String attributes = "";
        if (command.split(" ") != null) {
            comm = command.split(" ")[0];
        } else {
            comm = command;
        }

        String userId;
        if (command.split(" ").length >= 2) {
            userId = command.split(" ")[1];
            userId = userId.replace("\\s", "");
        } else {
            throw new WrongCommandException("Błędna komenda");
        }
        switch (comm) {
            case "LOGIN":
                try {
                    String username = userId.split(";")[0];
                    String userPassword = userId.split(";")[1];
                    if (username.equals(Server.login)) {
                        if (userPassword.equals(Server.password)) {
                            return "success;1";
                        } else {
                            return "failure;1";
                        }

                    } else {
                        return "failure;0";
                    }
                } catch (Exception e) {
                    throw new WrongCommandException("Błędna komenda");
                }

            case "LOGOUT":
                System.out.println(Server.loggedUsers);
                for (String id : Server.loggedUsers) {
                    if (userId.equals(id)) {
                        Server.loggedUsers.remove(id);
                        return "success;2";
                    }
                }
                return "failure;2";

            case "LS":
                for (String id : Server.loggedUsers) {
                    System.out.println(id + ' ' +userId);
                    if (userId.equals(id)) {
                        System.out.println("SUCC");
                        return "success;3";
                    }
                }
                return "failure;3";
            case "GET":
                String filename = command.split(" ")[2];
                for (String id : Server.loggedUsers) {
                    if (userId.equals(id)) {
                        for (String s : Server.files) {
                            if (s.equals(filename)) {
                                return "success;4";
                            }
                        }
                    }
                }
                return "failure;4";
            default:
                throw new WrongCommandException("Błędna komenda");


        }
    }
}
