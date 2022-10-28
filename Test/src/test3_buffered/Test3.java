package test3_buffered;

import java.io.*;

public class Test3 {
    public static void main(String[] args) throws IOException {
        // Буфферизованные потоки
        byte[] inBytes = new byte[2]; // буфер

        int readBytes = 0;

        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(
                "input.txt")); OutputStream outputStream = new BufferedOutputStream(
                new FileOutputStream("test3.txt"))) {
            while((readBytes = inputStream.read(inBytes))!= -1){
                // inputStream.read(inBytes); -- не надо! т.к. и так в while считываю

                outputStream.write(inBytes, 0, readBytes);
            }
        }
    }
}
