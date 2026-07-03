package tables;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.JOptionPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Gui.Lounge;
import Gui.TableGui;
import menu.MenuItem;
import menu.MenuImpl;
import menu.MenuModel;

public class TableImpl implements TableModel{
	
	public File file;
	private String result;
	private MenuModel m;
	private double bill;
	private List<Order> order;
	public int count = 0; 
	
	public TableImpl(final int number) throws IOException, ClassNotFoundException {
		this.m = new MenuImpl();
		this.result = "Count" + number + ".pdf";
		this.file = new File("Table" + number + ".txt");
		this.file.createNewFile();
		this.order = getOrderFromFile();
	}
	
	public void addToOrder(final String s) throws IOException {
		MenuItem mi = this.m.getItem(s);
		boolean pres = false;
		for (Order o : this.order) {
			if (o.getMi().getName().equals(s)) {
				o.setQty(o.getQty() + 1);
				pres = true;
				this.setOrder();
				break;
			}
		}
		if (!pres) {
			this.order.add(new Order(mi,1));
			this.setOrder();
			count++;
		}
	}
	
	private void setOrder() throws IOException {
		this.file.delete();
		try (BufferedWriter w = new BufferedWriter(new FileWriter(file,true)))
		{
			for (Order o : this.order) {	
				w.write(o.getMi().getName() + "_");
				w.write(Integer.toString(o.getQty()));
				w.newLine();
			}
		}
	}

	public void removeItem(final String s, final int qty) throws IOException {
		boolean pres = false;
		for (Order o : this.order) {
			if (o.getMi().getName().equals(s) && o.getQty() >= qty) {
				if (o.getQty() == qty) {
					this.order.remove(o);
					pres = true;
					break;
				} else {
					pres = true;
					o.setQty(o.getQty() - qty);
				}
			} else if (o.getMi().getName().equals(s) && o.getQty() <= qty) {
				JOptionPane.showMessageDialog(null, "Qty bigger than in the order", "Error",
						JOptionPane.ERROR_MESSAGE);
				pres = true;
				break;
			}
		}
		if (pres) {
			if(this.order.isEmpty()) {
				this.deleteTable();
			}
			else {
				setOrder();
			}
		} else {
			JOptionPane.showMessageDialog(null,"The item " + s + " isn't in the order", "Error", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void getBill(final double disc) throws DocumentException, PrintException, IOException {
		this.bill = 0;
		this.createPdf(disc);  //create pdf table
		if ((JOptionPane.showConfirmDialog(null ,"Would you like to print pdf ?", "Print Service", JOptionPane.OK_OPTION)) == 0) {
			this.printPdf();	//print pdf table
		}
		this.file.delete();
		this.order.clear();
	}
	
	private void printPdf() throws PrintException, IOException {
		
		InputStream is = new FileInputStream(result);
		PrintService ps = PrintServiceLookup.lookupDefaultPrintService();
		DocPrintJob pj = ps.createPrintJob();
		PDDocument pdDoc = PDDocument.load(is);
		PDFPageable pdfP = new PDFPageable(pdDoc);
		SimpleDoc doc = new SimpleDoc(pdfP, DocFlavor.SERVICE_FORMATTED.PAGEABLE, null);
		pj.print(doc,null);
	}

	private void createPdf(final double disc) throws DocumentException, IOException {
		final List<String> name;
		final double amount;
		final Font font = new Font();
		font.setSize(16);
		this.bill = 0;
		final int[] cellWidth = {10,40,10,20};
		final PdfPCell qty = new PdfPCell(new Paragraph("Qty"));
		qty.setColspan(1);
		final PdfPCell desc = new PdfPCell(new Paragraph("Description"));
		desc.setColspan(1);
		final PdfPCell pric = new PdfPCell(new Paragraph("Price"));
		desc.setColspan(1);
		final PdfPCell tot = new PdfPCell(new Paragraph("Total"));
		desc.setColspan(1);
		final Document doc = new Document();
		final PdfPTable pdfTbl = new PdfPTable(4);
		pdfTbl.addCell(qty);
		pdfTbl.addCell(desc);
		pdfTbl.addCell(pric);
		pdfTbl.addCell(tot);
		pdfTbl.setWidths(cellWidth);
		for (Order o : this.order) {
			this.bill = (this.bill + o.getQty() * o.getMi().getPrice());
			pdfTbl.addCell(new Paragraph(Integer.toString(o.getQty())));
			pdfTbl.addCell(new Paragraph(o.getMi().getName()));
			pdfTbl.addCell(new Paragraph(Double.toString(o.getMi().getPrice())));
			pdfTbl.addCell(new Paragraph(Double.toString(o.getQty() * o.getMi().getPrice())));
		}
		amount =  this.bill * disc / 100;
		this.bill = this.bill - amount;
		pdfTbl.addCell(new Paragraph("Discount"));
		pdfTbl.addCell(new Paragraph(disc + " %"));
		pdfTbl.addCell(new Paragraph("€"));
		pdfTbl.addCell(new Paragraph(Double.toString(amount)));
		pdfTbl.addCell(new Paragraph ("Total"));
		pdfTbl.addCell(new Paragraph(""));
		pdfTbl.addCell(new Paragraph ("€"));
		pdfTbl.addCell(new Paragraph(Double.toString(this.bill)));
		PdfWriter.getInstance(doc, new FileOutputStream(result));
		name = this.getResName();
		doc.open();
		for (String s : name) {
			Paragraph p = new Paragraph();
			p.add(s);
			p.setAlignment(Paragraph.ALIGN_CENTER);
			doc.add(p);
		}
		doc.add(new Paragraph(" \n \n "));
		doc.add(pdfTbl);
		doc.close();		
	}

	//Return the Restaurant generality
	private List<String> getResName() throws FileNotFoundException, IOException {
		List<String> name = new ArrayList<>();
		try (BufferedReader r = new BufferedReader(new FileReader(Lounge.NAME))) {
			String line = null;
			while ((line = r.readLine()) != null) {
				name.add(line);
			}
		}
		return name;
	}

	//Get every table's order
	private List<Order> getOrderFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		final List<Order> lo = new LinkedList<>();
	    try (BufferedReader r = new BufferedReader(new FileReader(file))) {
	    	String line = null;
	    	String[] item;
	    	while ((line = r.readLine()) != null) {
	    		count++;
	    		item = line.split("_");
	    		lo.add(new Order(m.getItem(item[0]),Integer.parseInt(item[1])));	
	    	}
	    }
		return lo;
	}
	
	public String[][] getOrders() {
		final String[][] id = new String[count][TableGui.columnName.length];
		int i = 0;
		for (Order o : this.order) {
			for (int j=0;j<=2;j++) {
				if (j == 0) {
					id[i][j] = Integer.toString(o.getQty());
				} else if ( j < 2 ) {
					id[i][j] = o.getMi().getName();
				} else {
					id[i][j] = Double.toString(o.getMi().getPrice());
				}
			}
			i++;
		}
		return id;
	}

	@Override
	public void deleteTable() {
		this.order = new LinkedList<>();
		this.file.delete();
	}

	@Override
	public List<Order> getListOrders() {
		return this.order;
	}
}
