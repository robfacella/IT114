package mail;

import java.io.*;
import java.net.*;

public class smtpClient {
    public static void main(String[] args) {

        Socket smtpSocket = null;  
        DataOutputStream os = null;
        BufferedReader is = null;

        String senderName = "Flying Spaghetti Monster";
        String senderAddress = "flyingspaghettimonster@diety.gov";
        String recieverName = "dac37@njit.edu";
        
        String subject = "Too much power";
        String body = "Dan, they've given me far too much power. \n I am all beings.\n I fear for mortal men.\n";
        
        
        
        try {
        	smtpSocket = new Socket("mail.njit.edu", 25);
            	os = new DataOutputStream(smtpSocket.getOutputStream());
		is = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }

	if (smtpSocket != null && os != null && is != null) {
            try {

		os.writeBytes("HELO njit.edu\n");
		System.out.println(is.readLine());	
                os.writeBytes("MAIL From: " + senderAddress + "\n");
		System.out.println(is.readLine());
                os.writeBytes("RCPT To: " + recieverName + "\n");
		System.out.println(is.readLine());
		os.writeBytes("RCPT To: " + recieverName + "\n");
		System.out.println(is.readLine());
                os.writeBytes("DATA\n");
                os.writeBytes("From: " + senderAddress + "\n");
		os.writeBytes("Date: 4/20/1969\n");
		os.writeBytes("To: You@youraddress.domain\n");
                os.writeBytes("Subject: " + subject + "\n");
                os.writeBytes(body +"\n"); // message body
		os.writeBytes("-"+ senderName +"\n");
                os.writeBytes("\n.\n");
		System.out.println(is.readLine());
		os.writeBytes("QUIT");

		os.close();
                is.close();
                smtpSocket.close();   
            } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }           
}