package vidya.com.net;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SimpleTCPServer {
	public static void main(String[] args) throws IOException {
		ServerSocket servSock = new ServerSocket(1025);

		while (true) { // Run forever, accepting and servicing connections
			Socket clntSock = servSock.accept(); // Get client connection
			SocketAddress clientAddress = clntSock.getRemoteSocketAddress();
			System.out.println("Handling client at " + clientAddress);

			OutputStream out = clntSock.getOutputStream();
			String sendAsciiString = createString("HELLO");
			// System.out.println(sendAsciiString);
			byte[] message1 = sendAsciiString.getBytes();
			out.write(message1);

			sendAsciiString = createString("WORLD");
			// System.out.println(sendAsciiString);
			byte[] message2 = sendAsciiString.getBytes();
			out.write(message2);

			clntSock.close();
		}

	}

	public static String createString(String asciiString) {
		int width = 100;
		int height = 30;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setFont(new Font("SansSerif", Font.BOLD, 24));

		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphics.drawString(asciiString, 10, 20);
		String s = "";
		for (int y = 0; y < height; y++) {
			StringBuilder sb = new StringBuilder();
			for (int x = 0; x < width; x++) {

				sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");

			}

			if (sb.toString().trim().isEmpty()) {
				continue;
			}
			s = s + "\n" + sb.toString();

		}
		return s;
	}

}
