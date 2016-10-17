
import java.io.*;
import java.security.SecureRandom;


public class aesu {



    public static void main(String args[]) {



        String s = null;



        try {

        	

        	

        	System.out.println("Number of arguments:" + args.length + " arguments.");

        	System.out.println ("Argument List:");
        	int argNum = 0;

        	for(int i=0; i<args.length; ++i) {

        	    System.out.println(args[i]);
        	    argNum += 1;
        	}

        	

    		/*

    		#      0     1  2                          3  4    5   6          7    8	

    		# AesU -e[d] -k key <-i initial_vector>    -m ecb  -in input_file -out output_file

    		*/



    		int keylen = args [2].length();
    		String mode = args[4];
    		
    		// (aes-keylength-mode)
    	    String cipher = "aes-" + Integer.toString(keylen * 4) + "-" + mode;


        	// generate iv
    	    int iv = generateIv().hashCode();
 
    	     String opensslcmmend = ""; 
    	     
    	     
    	    if (mode.equals("ecb")){
    	    	opensslcmmend = String.format("openssl %s %s -K %s -v -nosalt -in %s -out %s", cipher, args[0], args[2], args[6], args[8]);
    	    }if (mode.equals("cbc")){
    	    	opensslcmmend = String.format("openssl %s %s -K %s -iv %s -v -nosalt -in %s -out %s", cipher, args[0], args[2], iv, args[6], args[8]);
    	    }if (mode.equals("cfb")){
    	    	opensslcmmend = String.format("openssl %s %s -K %s -iv %s -v -nosalt -in %s -out %s", cipher, args[0], args[2], iv, args[6], args[8]);
    	    }if (mode.equals("ofb")){
    	    	opensslcmmend = String.format("openssl %s %s -K %s -iv %s -v -nosalt -in %s -out %s", cipher, args[0], args[2], iv, args[6], args[8]);
    	    }if (mode.equals("ctr")){
    	    	opensslcmmend = String.format("openssl %s %s -K %s -iv %s -v -nosalt -in %s -out %s", cipher, args[0], args[2], iv, args[6], args[8]);
    	    }
    	  
    	    
    	    
    	    
    	    

        	// run openssl command

    	    String opensslcmd = opensslcmmend;



            // using the Runtime exec method:

            Process p = Runtime.getRuntime().exec(opensslcmd);

            

            BufferedReader stdInput = new BufferedReader(new 

                 InputStreamReader(p.getInputStream()));



            BufferedReader stdError = new BufferedReader(new 

                 InputStreamReader(p.getErrorStream()));



            // read the output from the command

            System.out.println("Here is the standard output of the command:\n");

            while ((s = stdInput.readLine()) != null) {

                System.out.println(s);

            }

            

            // read any errors from the attempted command
            String error = "Here is the standard error of the command (if any): ";
            if (argNum != 9){
            	error += "unexpected nomber of argument";
            }
            if (keylen != 32 && keylen != 48 && keylen != 64 ){
            	error += "Key linght is not supported";
            }
           

            System.out.println( error + "\n");

            while ((s = stdError.readLine()) != null) {

                System.out.println(s);

            }

            

            System.exit(0);

        }

        catch (IOException e) {

            System.out.println("exception happened - here's what I know: ");

            e.printStackTrace();

            System.exit(-1);

        }
        

    }
    private static byte[] generateIv() {
        SecureRandom random = new SecureRandom();
        byte[] ivBytes = new byte[16];
        random.nextBytes(ivBytes);
        return ivBytes;
    }
}
