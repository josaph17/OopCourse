package ru.academits.dashiev.test2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Test2 {
    public static void main(String[] args) throws IOException {
        // InputSTream - €вл€етс€ базовым абстрактным классом дл€ всех потоков ввода. ќн €вл€етс€ байтовым
        try(InputStream stream = new FileInputStream("input.txt")){
            int read = 0;
            int off =0 ;

            byte[] res = new byte[10000];

            while((read= stream.read(res,off, res.length - off))!=-1){
                off+=read;
            }
        }
    }
}
