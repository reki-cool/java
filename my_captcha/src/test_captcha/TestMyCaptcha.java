package test_captcha;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import pw.produ.util.Captcha;

public class TestMyCaptcha {
	public static void main(String[] args) throws FileNotFoundException {
		Captcha x = new Captcha();
		
		x.setType(0,0);
		//x.setCharNumber(6);
		//x.setFontSize(25);
		//x.setRandomColor(true);
		
		//x.setBackgroundColor(Color.GRAY);
		//x.setBorderColor(Color.WHITE);
		//x.setCharRotate(true);
		x.createData();
		
		
		File f = new File("C:/Users/都颜汗/Desktop/new.jpg");
		FileOutputStream out = new FileOutputStream(f);
		
		String answer = x.exportImage(out);
		System.out.println("answer = "+answer);
		
		
	}
}
