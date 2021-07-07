package br.com.habernate01.GerarPDF;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws DocumentException, IOException {

		String path = "C:/temp/gerado/";
		String nome_Arquivo_Novo_1 = "Regulamentos_Sistema_Sebrae ";

		String pathTxt = "C:/temp/NomeColaborador.txt";

		/*String nome_Colaborador = "Leonardo Campelo Leite Guedes";
		ArrayList<String> colaborador = new ArrayList<String>();
		colaborador.add("Leonardo Campelo Leite Guedes");
		colaborador.add("Oséia Abreu da Silva");
		for (int i = 0; i < colaborador.size(); i++) {

			GerarPDFColaborador(path, nome_Arquivo_Novo_1, colaborador.get(i));
		}
		*/

		List<Colaborador> col = getNomeColaboradoresTxt(pathTxt);// new ArrayList<Colaborador>();

		for (Colaborador c : col) {
			//System.out.println(c.getNome());
			GerarPDFColaborador(path, nome_Arquivo_Novo_1, c.getNome());
		}

		

	}

	public static void GerarPDFColaborador(String path, String nome_Arquivo_Novo_1, String nome_Colaborador)
			throws DocumentException, IOException {
		String INPUTFILE = "C:/temp/Regulamentos_Sistema_Sebrae.pdf";

		String OUTPUTFILE = path + nome_Arquivo_Novo_1 + nome_Colaborador + ".pdf";

		Document document = new Document();

		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUTFILE));
		document.open();
		PdfReader reader = new PdfReader(INPUTFILE);
		int n = reader.getNumberOfPages();
		PdfImportedPage page;
		for (int i = 1; i <= n; i++) {
			// only page number 2 will be included
			// if (i == 2) {
			page = writer.getImportedPage(reader, i);
			Image instance = Image.getInstance(page);
			document.add(instance);
			// }
		}
		document.close();
	}

	
	public static List<Colaborador> getNomeColaboradoresTxt(String pathTxt) throws FileNotFoundException {

		FileInputStream entradaArquivo = new FileInputStream(pathTxt);// "C:\\Users\\Leonardo\\git\\repository\\arquivos\\src\\arquivos\\arquivo.txt");

		Scanner lerArquivo = new Scanner(entradaArquivo, "UTF-8");

		List<Colaborador> colabora = new ArrayList<Colaborador>();

		while (lerArquivo.hasNext()) {
			String linha = lerArquivo.nextLine();

			if (linha != null && !linha.isEmpty()) {

				String[] dados = linha.split("\\;");

				Colaborador nome_colaborador = new Colaborador();
				nome_colaborador.setNome(dados[0]);

				colabora.add(nome_colaborador);
			}

		}
		return colabora;

	}
}
