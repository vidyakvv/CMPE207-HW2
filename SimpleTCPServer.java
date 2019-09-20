package vidya.com.net;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SimpleTCPServer {
	public static void main(String[] args) throws IOException {
		ServerSocket servSock = new ServerSocket(100);
		int width = 72;
        int height = 32;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 8));
        
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString("HELLO WORLD", 6, 12);
  
		
				
		while (true) { // Run forever, accepting and servicing connections
		   Socket clntSock = servSock.accept(); // Get client connection
		   SocketAddress clientAddress = clntSock.getRemoteSocketAddress();
		   System.out.println("Handling client at " + clientAddress);
		   
		   OutputStream out = clntSock.getOutputStream();
	        for (int y = 0; y < height; y++) {
	            StringBuilder sb = new StringBuilder();
	            for (int x = 0; x < width; x++) {

	                sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");

	            }

	            if (sb.toString().trim().isEmpty()) {
	                continue;
	            }
	            System.out.println(sb);

	            byte[] message = sb.toString().getBytes();
	            out.write(message);
	        }
		   clntSock.close();
		}
		
	}
	

}
