package com.u2d.projeto.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipArchive {
	
	private final static String SAIDA = "/opt/saida/";
	private final static String ZIP = ".zip";
	private final static String TXT = ".txt";
	private final static String NOME_ARQUIVO = "arquivos"+ZIP;

	public static void zipaArquivo(File... file) {	     
		try {
			File arq = new File("/opt/teste");
			FileOutputStream fileOut = new FileOutputStream(SAIDA+NOME_ARQUIVO);
		    ZipOutputStream out = new ZipOutputStream(fileOut);
		    
		    List<File> files = Arrays.asList(arq.listFiles())
		    		.stream()
		    		.filter(e -> e.getName().contains("1234") && e.getName().endsWith(TXT))
		    		.collect(Collectors.toList());
		    files.forEach(f -> {
					try {
						FileInputStream fis = new FileInputStream(f.getPath());
						out.putNextEntry( new ZipEntry(f.getName()));
						int content;
					    while ((content = fis.read()) != -1) {
					        out.write(content );
					    }
					    out.closeEntry();
					    fis.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			});
		    out.close();     
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
