package ru.academits.dashiev.test2_input_output_stream;

import java.io.*;

public class Test2 {
    public static void main(String[] args) throws IOException {
        // InputStream - �������� ������� ����������� ������� ��� ���� ������� �����. �� �������� ��������
        try (InputStream stream = new FileInputStream(
                "input.txt"); OutputStream outStream = new FileOutputStream("output.txt")) {
            int read = 0;
            int off = 0; // off - ���� 0 ��������, 1 ����� - 1 ����, char - 1 ����2

            byte[] res = new byte[5];

            while ((read = stream.read(res, off, res.length - off)) != -1) {
                off += read;
            }

            int write = 0;
            int off2 = 0;

            outStream.write(res);

//            while((write = outStream.write(resOut, off2, resOut.length-off2))!=-1){
//                off2+= write;
//            }

            // off = stream.read(res); // ������ ������� ���-�� ���=���� ���


            System.out.println(off);
        }
    }
}
