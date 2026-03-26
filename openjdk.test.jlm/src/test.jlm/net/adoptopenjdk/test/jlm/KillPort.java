package net.adoptopenjdk.test.jlm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KillPort {

    public static void killPort(int port) {
        try {
            System.out.println("DEBUG: Attempting to kill port " + port);

            Process process = Runtime.getRuntime().exec("fuser -k " + port + "/tcp");
            process.waitFor();

            BufferedReader stdError = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));

            String err;
            while ((err = stdError.readLine()) != null) {
                System.out.println("fuser: " + err);
            }

            System.out.println("DEBUG: Kill command executed for port " + port);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
