package SzymonServer;

import SzymonServer.exceptions.WrongCommandException;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.stream.Collectors;

public class SzymonServerThread extends Thread {
    protected Socket socket;
    protected Server server;
    protected String id = null;
    protected String output = "";

    public SzymonServerThread(Socket clientSocket, Server server) {
        this.socket = clientSocket;
        this.server = server;
    }

    public void run() {
        InputStream in = null;
        BufferedReader brinp = null;
        DataOutputStream out = null;

        try {
            in = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(in));
            out = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            return;
        }

        String command = null;

        while (true) {
            try {
                command = brinp.readLine();

                String command2 = "";
                if (command.equals("LOGIN")) {
                    command2 = brinp.readLine();
                }
                try {
                    String[] commandOut = handleCommand(command, command2).split(";");
                    String result = commandOut[0];
                    int commandId = Integer.parseInt(commandOut[1]);
                    if (result == "success") {
                        switch (commandId) {
                            case 1:
                                this.id = generateId();
                                output = this.id;
                                break;
                            case 2:
                                output = "true";
                                out.writeBytes(output);
                                socket.close();
                                return;
                            case 3:
                                output = "";
                                for (String s : server.files) {
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
                                out.writeBytes(output);
                                socket.close();
                                return;
                            case 1:
                                output = "Złe hasło\nOdległość: " + calculatePasswordDistance(command2.split(";")[1], server);
                                out.writeBytes(output);
                                socket.close();
                                return;
                            case 2:
                                output = "false";
                                out.writeBytes(output);
                                socket.close();
                                return;
                            case 3:
                                output = "false";
                                out.writeBytes(output);
                                socket.close();
                                return;
                            case 4:
                                output = "false";
                                out.writeBytes(output);
                                socket.close();
                                return;
                        }

                    }

                    out.writeBytes(output);
                    out.flush();
                } catch (WrongCommandException e) {
                    socket.close();
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    private int calculatePasswordDistance(String userPassword, Server server) throws WrongCommandException {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("polish-dic.txt").getFile());
            BufferedReader br = new BufferedReader(new FileReader(file));
            int counter = 1;
            String line;
            while ((line = br.readLine()) != null) {
                if (line == userPassword) {
                    return server.passwordLine - counter;
                } else counter += 1;
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new WrongCommandException("Hasło nie znajduje się w słowniku");
    }

    private String getFileContent(String s) throws WrongCommandException {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("secret.txt").getFile());
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


    public String handleCommand(String command, String command2) throws WrongCommandException {
        String output = "";
        String comm = "";
        if(command.split(" ") != null) {
             comm = command.split(" ")[0];
        }
        else {
            comm = command;
        }
        if (!command2.isEmpty()) {
            String[] usersData = command2.split(";");
            if (usersData.length != 2) {
                throw new WrongCommandException("Błędna komenda");
            }
            String username = usersData[0];
            String userPassword = usersData[1];

            if (username == server.login) {
                if (userPassword == server.password) {
                    return "success;1";
                } else {
                    return "failure;1";
                }

            } else {
                return "failure;0";
            }


        } else {
            switch (comm) {
                case "LOGOUT":
                    if (command.split(" ")[1] == this.id) {
                        return "success;2";
                    } else {
                        return "failure;2";
                    }
                case "LS":
                    if (command.split(" ")[1] == this.id) {
                        return "success;3";
                    } else {
                        return "failure;3";
                    }
                case "GET":
                    if (command.split(" ")[1] == this.id) {
                        String filename = command.split(" ")[2];
                        for (String s : server.files) {
                            if (s == filename) {
                                return "success;4";
                            }
                        }
                        return "failure;4";
                    } else {
                        return "failure;4";
                    }
                default:
                    throw new WrongCommandException("Błędna komenda");

            }
        }
    }
}
