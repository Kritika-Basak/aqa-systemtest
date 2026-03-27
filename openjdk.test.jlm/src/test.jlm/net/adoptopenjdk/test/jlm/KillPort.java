package net.adoptopenjdk.test.jlm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KillPort {

    public static void killPort(int port) {
        try {
            Process process = Runtime.getRuntime().exec("fuser -k " + port + "/tcp");
            process.waitFor();

            BufferedReader stdError = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));

            String err;
            while ((err = stdError.readLine()) != null) {
                System.out.println("fuser: " + err);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
