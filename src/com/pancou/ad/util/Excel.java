package com.pancou.ad.util;

import java.io.*;
import javax.persistence.Entity;
import javax.servlet.http.*;
import jxl.*;
import jxl.write.*;
import jxl.write.Number;
import jxl.format.*;
import jxl.format.CellFormat;
import jxl.write.WritableImage;
import jxl.write.biff.RowsExceededException;

/**
 * <pre>
 * ��excel�ļ��Ĵ���
 *  
 *
 *</pre>
 */

@Entity
public class Excel {
    // �������� workbook
    private Workbook workbook = null;
    // �������� sheet
    private Sheet sheet = null;
    // �������� cell
    private Cell  cell  = null;
    // �������� writableworkbook
    private WritableWorkbook writableWorkbook = null;
    // �������� writeablesheet
    private WritableSheet writableSheet = null;
    // �������� writeableFont
    private WritableFont writableFont   = null;
    // �������� writeablecellformat
    private WritableCellFormat writableCellFormat = null;
    
    private void setWbRead(Workbook workbook){
        this.workbook = workbook;
    }
    
    private Workbook getWbRead(){
        return workbook;
    }
    
    /**
     * ��һ��������ָ�����ļ� ����һ���µ�excel <br>
     * <br>
     * @param pTarFile  ·�����ַ� <br>
     * @return writableWorkbook <br>
     */    
    public WritableWorkbook getWorkBook(String pTarFile) {
        try{
            // ����writableWorkbook����
            writableWorkbook = Workbook.createWorkbook(new File(pTarFile));
        }catch(Exception e){
            System.out.println(e);
        }
        return  writableWorkbook;
    }
    
    /**
     * �����? <br>
     * <br>
     * @param pTarFile file�ļ� <br>
     * @param pSetSheetName Ҫ�����ı? <br>
     * @param pFlag �����Ͳ��� <br>
     * @return WritableSheet <br>
     */
    public WritableSheet getSheet(String pTarFile,
            String pSetSheetName,
            boolean pFlag){
        try{
            // ����writeablesheet����Ĭ���ǵ�һ��ҳ
            writableSheet = this.getWorkBook(pTarFile).createSheet(pSetSheetName,0);
        }catch(Exception e){
            System.out.println(e);
        }
        return writableSheet;
    }
    
    /**
     * �ڶ�������������� ����excel���� <br>
     * <br>
     * @param pOutputStream ���ļ� <br>
     * @return writableWorkbook <br>
     */
    public WritableWorkbook getWorkBook(OutputStream pOutputStream){
        try{
            // ����writableWorkbook����
            writableWorkbook = Workbook.createWorkbook(pOutputStream);
        }catch(Exception e){
            System.out.println(e);
        }
        return  writableWorkbook;
    }
    
    /**
     * ����writeablework���� ��ݵڶ������������excel�������? <br>
     * <br>
     * @param pOutputStream ���ļ� <br>
     * @param pNames sheet���ļ��� <br>
     * @return writableWorkbook <br>
     */
    public WritableWorkbook getWorkBook(OutputStream pOutputStream,
            String []pNames){
        try{
            // ����writableWorkbook����
            writableWorkbook = Workbook.createWorkbook(pOutputStream);
            // ��ÿ���?����
            for(int i = 0;i < pNames.length;i++){
                writableWorkbook.createSheet(pNames[i], i);
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        return  writableWorkbook;
    }
    
    /**
     * �����?<br>
     * <br>
     * @param pSetSheetName Ҫ�����ı?�� <br>
     * @param pResponse response���� <br>
     * @param pFileName file�ļ��� <br>
     * @return WritableSheet <br>
     */
    public WritableSheet getSheet(String pSetSheetName,
            HttpServletResponse pResponse,
            String pFileName){
        try{
            pResponse.reset();
            // ����ͷ�ļ�
            pResponse.setHeader("Content-disposition", 
                    "attachment; filename=" + pFileName + ".xls");
            // �����ı���ʽ
            pResponse.setContentType("application/OCTET-STREAM");
            // �õ������
            OutputStream outputStream = pResponse.getOutputStream();
            // �����?
            writableSheet = this.getWorkBook(outputStream).
            createSheet(pSetSheetName,0);
            
        }catch(Exception e){
            System.out.println(e);
        }
        return writableSheet;
    }
    
    /**
     * �õ��? <br>
     * <br>
     * @param pSheetNo ���� <br>
     * @return writableSheet <br>
     */
    public WritableSheet getSheet(int pSheetNo){
        try{
            // ���writableWorkbook�õ��?
            writableSheet = this.writableWorkbook.getSheet(pSheetNo);
        }catch(Exception e){
            System.out.println(e);
        }
        return writableSheet;
    }
    
    /**
     * ������������Դ�ļ���Ŀ���ļ�����excel���� <br>
     * <br>
     * @param pSourFile Դ�ļ� <br>
     * @param pTarFile Ŀ���ļ� <br>
     * @return writableWorkbook <br>
     */
    public WritableWorkbook getWorkBook(String pSourFile,
            String pTarFile) {
        try{
            // ����excle����
            workbook = Workbook.getWorkbook(new File(pSourFile));
            // �������excel����
            this.setWbRead(workbook);
            // ����writableWorkbook ����
            writableWorkbook =
                Workbook.createWorkbook(new File(pTarFile),workbook);
            
        }catch(Exception e){
            System.out.println(e);
        }
        return  writableWorkbook;
    }    
    
    /**
     * �õ��? <br>
     * <br>
     * @param pSourFile Դ�ļ� <br>
     * @param pTarFile Ŀ���ļ� <br>
     * @return writableSheet <br>
     */
    public WritableSheet getSheet(String pSourFile,
            String pTarFile){
        try{
            // ���writableWorkbook�õ��?
            writableSheet = 
                this.getWorkBook(pSourFile,pTarFile).getSheet(0);
        }catch(Exception e){
            System.out.println(e);
        }
        return writableSheet;
    }  
    
    /**
     * ������������Դ�ļ������������writeablebook���� <br>
     * <br>
     * @param pSourFile Դ�ļ� <br>
     * @param pOutputStream ����� <br>
     * @return writableWorkbook <br>
     */
    public WritableWorkbook getWorkBook(String pSourFile,
            OutputStream pOutputStream){
        try{
            // �õ�excel����
            workbook  = jxl.Workbook.getWorkbook(new File(pSourFile));
            // ���õ�ǰ��excel����
            this.setWbRead(workbook);
            // ����writableWorkbook����
         writableWorkbook = Workbook.createWorkbook(pOutputStream,workbook );
        }catch(Exception e){
            System.out.println(e);
        }
        return  writableWorkbook;
    }
    
    /**
     * �����?<br>
     * <br>
     * @param pSourFile file�ļ��� <br>
     * @param pSetSheetName Ҫ���? <br>
     * @param pResponse response���� <br>
     * @param pFileName ��ͷ�ļ������� <br>
     * @return WritableSheet<br>
     */
    public WritableSheet getSheet(String pSourFile,
            String pSetSheetName,
            HttpServletResponse pResponse,
            String pFileName){
        try{
            // ��������response����
            pResponse.reset();
            // ���ñ�ͷ
            pResponse.setHeader("Content-disposition", 
                    "attachment; filename=" + pFileName + ".xls");
            // �������ݸ�ʽ
            pResponse.setContentType("application/OCTET-STREAM");
            // ���������
            OutputStream outputStream = pResponse.getOutputStream();
            // ������淽������writableSheet����
            writableSheet = 
                this.getWorkBook(pSourFile,outputStream).getSheet(0);
            // ��?����
            writableSheet.setName(pSetSheetName);
        }catch(Exception e){
            System.out.println(e);
        }
        return writableSheet;
    }
    
    /**
     * �����? <br>
     * <br>
     * @param pName sheet�� <br>
     * @param pIndex ���� <br>
     */
    public  void createSheet(String pName,int pIndex){
        
        if(pIndex < 1 || pIndex > 254){
            pIndex = 1;
        }
        
        // ���Ʊ?
        writableWorkbook.copySheet(0,pName,pIndex);
    }
    
    /**
     * ɾ��?<br>
     * <br>
     * @param pIndex Ҫɾ���sheetҳ�� <br>
     * @return void <br>
     */
    public void removeSheet(int pIndex){
        // ɾ��?
        writableWorkbook.removeSheet(pIndex);
    }  
    
    /**
     * �õ��? <br>
     * <br>
     * @param pSheetNo sheet�� <br>
     * @return writableSheet <br>
     */ 
    public WritableSheet getSheetNo(int pSheetNo){
        
        try{
            // ҳ�����254��ʱ��
            if(pSheetNo > 254){
                pSheetNo=254;
            }
            
            // ҳ��С��0��ʱ��
            if(pSheetNo < 0){
                pSheetNo = 0;
            }
            
            // �õ��?����
            writableSheet = writableWorkbook.getSheet(pSheetNo);
        }catch(Exception e){
            System.out.println(e);
        }
        return writableSheet;
    }   
    
    /**
     * �Ե�Ԫ������� <br>
     * <br>
     * @param pCellBackColor ����ɫ <br>
     * @param pFontColor ����ɫ <br>
     * @param pFontSize �����С <br>
     * @param pBold �Ƿ���� <br>
     * @param pBorder �߽�ֵ <br>
     * @return writableCellFormat WritableCellFormat���� <br>
     */
    public WritableCellFormat getCellFormat(int pCellBackColor,
            int pFontColor,
            int pFontSize,
            boolean pBold,
            int pBorder){
        
        try{
            // �������壬��С���Ƿ��Ǵ��������
            writableFont = new WritableFont(WritableFont.createFont("����"),
                    pFontSize,
                    pBold == true ? WritableFont.BOLD : WritableFont.NO_BOLD,
                    false,
                    UnderlineStyle.NO_UNDERLINE,
                    this.setColor(pFontColor));
            writableCellFormat = new WritableCellFormat(writableFont);
            // �Ա߿������ ������ɫ��
            writableCellFormat.setBorder(jxl.format.Border.ALL,
                    jxl.format.BorderLineStyle.THIN,
                    pBorder == 
                    1 ? jxl.format.Colour.BLACK : jxl.format.Colour.GRAY_25);
            // ���ñ���
            writableCellFormat.setBackground(this.setColor(pCellBackColor)); 
            // ���þ���
            writableCellFormat.setAlignment(jxl.format.Alignment.CENTRE);
            // ����ˮƽ�������
            writableCellFormat.
            setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            writableCellFormat.setWrap(true);
        }catch(Exception e){
            System.out.println(e);
        }
        return writableCellFormat;
    }
    
    /**
     * �õ��ɶ��? <br>
     * <br>
     * @param pReadSheetNo <br>
     * @return sheet <br>
     */
    public Sheet getReadSheetNo(int pReadSheetNo){
        try{
            // �õ��ɶ��?
            sheet = this.getWbRead().getSheet(pReadSheetNo);
        }catch(Exception e){
            System.out.println(e);
        }
        return sheet;
    }
    
    /**
     * �õ���Ԫ�� <br>
     * <br>
     * @param pReadSheetNo ������ <br>
     * @param pReadCurrRow ��ǰ�� <br>
     * @param pReadCurrCell ��ǰ��Ԫ�� <br>
     * @return cell ��Ԫ�� <br>
     */
    public Cell getCell(int pReadSheetNo,
            int pReadCurrRow,
            int pReadCurrCell){
        try{
            // �õ���Ԫ��
            cell = this.getReadSheetNo
            (pReadSheetNo).getCell(pReadCurrCell,pReadCurrRow);
        }catch(Exception e){
            System.out.println(e);
        }
        return cell;
    }
    
    /**
     * �õ�ָ����Ԫ���ֵ <br>
     * <br>
     * @param pReadSheetNo ������ <br>
     * @param pReadCurrRow ָ���� <br>
     * @param pReadCurrCell ָ���ĵ�Ԫ�� <br>
     * @return String
     */
    public String getCellValue(int pReadSheetNo,
            int pReadCurrRow,
            int pReadCurrCell){
        // �õ�ָ����Ԫ���ֵ
        return this.getCell
        (pReadSheetNo,pReadCurrRow,pReadCurrCell).getContents();
    }   
    
    /**
     * �ϲ���Ԫ��Ĵ���<br>
     * <br>
     * @param pSheet ����ı? <br>
     * @param pRow �� <br>
     * @param pCell �� <br>
     * @param pToRow Ŀ���� <br>
     * @param pToCell Ŀ���� <br>
     * @return void <br>
     */ 
    public void mergeCells(WritableSheet pSheet,
            int pRow,
            int pCell,
            int pToRow,
            int pToCell){
        try{
            // �ϲ���Ԫ��
            pSheet.mergeCells(pCell,pRow,pToCell,pToRow);
        }catch(Exception e){
            System.out.println(e);
        }
    }   
    
    /**
     * ������� <br>
     * <br>
     * @param pSheet ������ <br>
     * @param pRow �� <br>
     * @param pCell �� <br>
     * @param pContent ���� <br>
     * @param pIntStrType �ַ��͡������� <br>
     * @param pCellFormat ��Ԫ������ <br>
     */
    public void outExecute(
            WritableSheet pSheet,
            int pRow,
            int pCell,
            String pContent,
            int pIntStrType,
            WritableCellFormat pCellFormat) {
        try{
            // ����ָ����cellFormat��ʽ���
            if(pCellFormat != null){
                // ����
                if(pIntStrType == 0){
                    jxl.write.Label  label = 
                        new jxl.write.Label(pCell,pRow,pContent,pCellFormat);
                   pSheet.addCell(label);
                }else{
                    // ����
                    jxl.write.Number label = new jxl.write.Number
                    (pCell,pRow,Float.parseFloat(pContent),pCellFormat);
                    pSheet.addCell(label);
                }
            }else{ 
                // ����
               if(pIntStrType == 0){
                jxl.write.Label label = new jxl.write.Label(pCell,pRow,pContent);
                pSheet.addCell(label);
                }else{
                   // ����
                jxl.write.Number label = 
                  new jxl.write.Number(pCell,pRow,Float.parseFloat(pContent));
                    pSheet.addCell(label);
                }
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
    }

	/**
	 * ���빫ʽ
	 * 
	 * @param sheet
	 * @param col
	 * @param row
	 * @param formula
	 * @param format
	 */

	public void insertFormula(WritableSheet sheet, Integer col, Integer row,
			String formula, WritableCellFormat format) {
		try {
			Formula f = new Formula(col, row, formula, format);
			sheet.addCell(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �õ���ʽ(��˻�)
	 * @param col
	 *            int : ��
	 * @param startPos
	 *            int : ��ʼλ��
	 * @param endPos
	 *            int : ����λ��
	 * @return String
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
    public String getFormula(int col, int startPos, int endPos)
            throws RowsExceededException, WriteException
    {
        char base = 'A';
        char c1 = base;
        char c2 = base;
        StringBuffer formula = new StringBuffer(128);
        // ��װ��ʽ
        if (col < 25)
        {//A*Z
            c1 = (char) (col % 65 + base);
            c2 = (char) ((col+1) % 65 + base);
            formula.append(c1).append(startPos).append("*")
                   .append(c2).append(endPos);
        } 
        else if (col == 25) {//Z*AA
        	 char c3 = (char) (col%26 + base);
             c1 = (char) ((col+1-26) % 65 + base);
             formula.append(c3).append(startPos).append("*")
                    .append(c1).append(c1).append(endPos);
        }
        
        else if (col > 25)
        {//AA*AZ
            char c3 = (char) ((col - 26) / 26 + base);
            char c4 = (char) ((col - 25) / 26 + base);
            c1 = (char) ((col - 26) % 26 + base);
            c2 = (char) ((col - 25) % 26 + base);
            if (((col - 26) % 26==25) && ((col - 25) % 26==0)){
            	//c1==Z;c2==A
            	formula.append(c3).append(c1).append(startPos).append("*")
                .append(c4).append(c2).append(endPos);
            	
            } else {
            	formula.append(c3).append(c1).append(startPos).append("*")
                   .append(c3).append(c2).append(endPos);
            }
        }

        return formula.toString();
    }
    
    /**
	 * �õ���ʽ(�����)
	 * @param col
	 *            int : ��
	 * @param startPos
	 *            int : ��ʼλ��
	 * @param endPos
	 *            int : ����λ��
	 * @return String
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
    public String getFormulaSum(int col, int startPos, int endPos, int flag)
            throws RowsExceededException, WriteException
    {
		char base = 'A';
		char c1 = base;
		char c2 = base;
		StringBuffer formula = new StringBuffer(128);
		formula.append("SUM(");
		// ��װ��ʽ
		if (col <= 25) {
			c1 = (char) (col % 65 + base);
			if (flag != 0) {// �ܽ�����
				for (int k = 0; k < flag; k++) {
					if (k < 8 ) { //A -- Z
						c2 = (char) ((col + 3 * k) % 65 + base);
						formula.append(c2).append(startPos).append(",");// ���ܽ��
						
					}
					if (k >= 8) {//AA--AZ
						if (k < 17) {
							c1 = 'A';
							c2 = (char) (((col + 3 * k) - 26) % 65 + base);
						} else if (k >= 17 && k < 25) {
							c1 = 'B';
							c2 = (char) (((col + 3 * k) - 26 * 2) % 65 + base);
						} else if (k >= 25 && k < 34) {
							c1 = 'C';
							c2 = (char) (((col + 3 * k) - 26 * 3) % 65 + base);
						} else if (k >= 34 && k < 43) {//��Ͳ��ܳ���40��
							if (k == 37) {
								formula.append(")+SUM(");
							}
							c1 = 'D';
							c2 = (char) (((col + 3 * k) - 26 * 4) % 65 + base);
						} else if (k >= 43 && k < 54) {
							c1 = 'E';
							c2 = (char) (((col + 3 * k) - 26 * 5) % 65 + base);
						} else if (k >= 54 && k < 65) {
							c1 = 'F';
							c2 = (char) (((col + 3 * k) - 26 * 6) % 65 + base);
						}
						formula.append(c1).append(c2).append(startPos).append(",");// ���ܽ��
					} 
					
				}
				
			} else {// �����				
				formula.append(c1).append(startPos).append(":");
				formula.append(c1).append(endPos);
			}
			formula.append(")");
			
		} else if (col > 25) {
			char c3 = (char) ((col - 26) / 26 + base);
			c1 = (char) ((col - 26) % 26 + base);
			if (flag != 0) {// �ܽ�����
				for (int k = 0; k < flag; k++) {
					c2 = (char) ((col + 3 * k) % 65 + base);
					formula.append(c3).append(c2).append(startPos).append(",");// ���ܽ��
				}
			} else {// �����
				formula.append(c3).append(c1).append(startPos).append(":");
				formula.append(c3).append(c1).append(endPos);
			}
			formula.append(")");
		}
		return formula.toString();
	}
    /**
	 * �޸��ַ�Ԫ���ֵ
	 * 
	 * @param dataSheet
	 *            WritableSheet : ������
	 * @param col
	 *            int : ��
	 * @param row
	 *            int : ��
	 * @param str
	 *            String : �ַ�
	 * @param format
	 *            CellFormat : ��Ԫ�����ʽ
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
    public void modiStrCell(WritableSheet dataSheet, int col, int row, String str, CellFormat format) throws RowsExceededException, WriteException
    {
        // ��õ�Ԫ�����
        WritableCell cell = dataSheet.getWritableCell(col, row);
        // �жϵ�Ԫ�������, ������Ӧ��ת��
        if (cell.getType() == CellType.EMPTY)
        {
            Label lbl = new Label(col, row, str);
            if(null != format)
            {
                lbl.setCellFormat(format);
            } else
            {
                lbl.setCellFormat(cell.getCellFormat());
            }
            dataSheet.addCell(lbl);
        } else if (cell.getType() == CellType.LABEL)
        {
            Label lbl = (Label)cell;
            lbl.setString(str);
        } else if (cell.getType() == CellType.NUMBER)
        {
            // ���ֵ�Ԫ���޸�
            Number n1 = (Number)cell;
            n1.setValue(42.05);
        }
    }

    
    /**
     * ��Ԫ������ <br>
     * <br>
     * @param pFontSize �����С <br>
     * @param pFontColor ������ɫ <br>
     * @param pCellBkColor ������ɫ <br>
     * @param pBold �Ƿ���� <br>
     * @param pBorder �Ƿ��б߿� <br>
     * @param pAlignment ���뷽ʽ <br>
     * @return writableCellFormat WritableCellFormat <br>
     * @throws Exception ���������ʱ�׳��쳣<br>
     */
    public WritableCellFormat setCellStyle(int pFontSize,
            int pFontColor,
            int pCellBkColor,
            boolean pBold,
            boolean pBorder,
            String pAlignment) throws Exception{
        
        WritableFont font = null;
        WritableCellFormat cellStyle = null;       

        try{
            // ��������
            font =  new WritableFont(WritableFont.createFont("����"),
                    pFontSize,
                    pBold == true ? WritableFont.BOLD : WritableFont.NO_BOLD,
                    false,
                    UnderlineStyle.NO_UNDERLINE,
                    this.setColor(pFontColor));
            // ���õ�Ԫ����ʽ����
            cellStyle = new WritableCellFormat(font);
            if(pBorder){
                cellStyle.setBorder(jxl.format.Border.ALL,
                        jxl.format.BorderLineStyle.THIN,
                        jxl.format.Colour.BLACK);
            }else{
                cellStyle.setBorder(jxl.format.Border.NONE,
                        jxl.format.BorderLineStyle.NONE,
                        jxl.format.Colour.BLACK);
            }
            
            // ������ɫ
            cellStyle.setBackground(this.setColor(pCellBkColor));
            
            // ���ж���
            if(pAlignment.equals("center") || pAlignment.equals("")){
                cellStyle.setAlignment(jxl.format.Alignment.CENTRE);
            }else{
                if(pAlignment.equals("left")){
                    // �����
                    cellStyle.setAlignment(jxl.format.Alignment.LEFT);
                }else{
                    // �Ҷ���
                    cellStyle.setAlignment(jxl.format.Alignment.RIGHT);
                }
            }
            
            cellStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            cellStyle.setWrap(true);
            
        }catch(Exception e){
            System.out.println(e);
        }       
        return cellStyle;
    }
    /**
     * ��Ԫ������(����) <br>
     * <br>
     * @param pFontSize �����С <br>
     * @param pFontColor ������ɫ <br>
     * @param pCellBkColor ������ɫ <br>
     * @param pBold �Ƿ���� <br>
     * @param pBorder �Ƿ��б߿� <br>
     * @param pAlignment ���뷽ʽ <br>
     * @return writableCellFormat WritableCellFormat <br>
     * @throws Exception ���������ʱ�׳��쳣<br>
     */
    public WritableCellFormat setCellStylePrice(int pFontSize,
            int pFontColor,
            int pCellBkColor,
            boolean pBold,
            boolean pBorder,
            String pAlignment) throws Exception{
        
        WritableFont font = null;
        WritableCellFormat cellStyle = null;
        WritableCellFormat numFormat2 = null;

        try{
            // ��������
            font =  new WritableFont(WritableFont.createFont("����"),
                    pFontSize,
                    pBold == true ? WritableFont.BOLD : WritableFont.NO_BOLD,
                    false,
                    UnderlineStyle.NO_UNDERLINE,
                    this.setColor(pFontColor));
            // ���õ�Ԫ����ʽ����
            cellStyle = new WritableCellFormat(font);
            cellStyle =  new WritableCellFormat(new NumberFormat("0.00"));
            if(pBorder){
                cellStyle.setBorder(jxl.format.Border.ALL,
                        jxl.format.BorderLineStyle.THIN,
                        jxl.format.Colour.BLACK);
            }else{
                cellStyle.setBorder(jxl.format.Border.NONE,
                        jxl.format.BorderLineStyle.NONE,
                        jxl.format.Colour.BLACK);
            }
            
            // ������ɫ
            cellStyle.setBackground(this.setColor(pCellBkColor));
            
            // ���ж���
            if(pAlignment.equals("center") || pAlignment.equals("")){
                cellStyle.setAlignment(jxl.format.Alignment.CENTRE);
            }else{
                if(pAlignment.equals("left")){
                    // �����
                    cellStyle.setAlignment(jxl.format.Alignment.LEFT);
                }else{
                    // �Ҷ���
                    cellStyle.setAlignment(jxl.format.Alignment.RIGHT);
                }
            }
            
            cellStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            cellStyle.setWrap(true);
            cellStyle.setAlignment(jxl.format.Alignment.LEFT);
//            cellStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
        }catch(Exception e){
            System.out.println(e);
        }       
        return cellStyle;
    }
    /**
     * 2007-03-21ȡ�������ͬ��Ԫ�����ԣ������˱߿�ķ�ʽ�ϡ��¡����ҡ�<br>
     * ��Ԫ������ <br>
     * <br>
     * @param pFontSize �����С <br>
     * @param pFontColor ������ɫ <br>
     * @param pCellBkColor ������ɫ <br>
     * @param pBold �Ƿ���� <br>
     * @param pBorder �Ƿ��б߿� <br>
     * @param pAlignment ���뷽ʽ <br>
     * @param pBooleanWrap �Ƿ��� <br>
     * @return writableCellFormat WritableCellFormat <br>
     * @throws Exception ���������ʱ�׳��쳣<br>
     */
    public WritableCellFormat setCellStyle(int pFontSize,
            int pFontColor,
            int pCellBkColor,
            boolean pBold,
            int pBorder,
            String pAlignment,
            boolean pBooleanWrap) throws Exception{
        
        WritableFont font = null;
        WritableCellFormat cellStyle = null;
        
        try{
            font =  new WritableFont(WritableFont.createFont("����"),
                    pFontSize,
                    pBold == true ? WritableFont.BOLD : WritableFont.NO_BOLD,
                    false,
                    UnderlineStyle.NO_UNDERLINE,
                    this.setColor(pFontColor));
            
            cellStyle = new WritableCellFormat(font);
            // ���ñ߿�
            switch(pBorder){
                case 0:                    
                    // �б߿�
                    break;
                case 1:  
                    // �ϱ߿�
                    cellStyle.setBorder(jxl.format.Border.ALL,
                            jxl.format.BorderLineStyle.THIN,
                            jxl.format.Colour.BLACK);
                    break;
                case 2:  
                    // �±߿�
                    cellStyle.setBorder(jxl.format.Border.TOP,
                            jxl.format.BorderLineStyle.THIN,
                            jxl.format.Colour.BLACK);
                    break;
                case 3: 
                    // ��߿�
                    cellStyle.setBorder(jxl.format.Border.BOTTOM,
                            jxl.format.BorderLineStyle.THIN,
                            jxl.format.Colour.BLACK);
                    break;
                case 4:  
                    // �ұ߿�
                    cellStyle.setBorder(jxl.format.Border.LEFT,
                            jxl.format.BorderLineStyle.THIN,
                            jxl.format.Colour.BLACK);
                    break;
                case 5:  
                    // �ޱ߿�
                    cellStyle.setBorder(jxl.format.Border.RIGHT,
                            jxl.format.BorderLineStyle.THIN,
                            jxl.format.Colour.BLACK);
                    break;
                case 6:  
                    cellStyle.setBorder(jxl.format.Border.NONE,
                            jxl.format.BorderLineStyle.NONE,
                            jxl.format.Colour.BLACK);
                    break;
                default:
                    break;
            }
            
            cellStyle.setBackground(this.setColor(pCellBkColor));
            // ���ж���
            if(pAlignment.equals("center") || pAlignment.equals("")){
                cellStyle.setAlignment(jxl.format.Alignment.CENTRE);
            }else{
                if(pAlignment.equals("left")){
                    // �����
                    cellStyle.setAlignment(jxl.format.Alignment.LEFT);
                }else{
                    // �Ҷ���
                    cellStyle.setAlignment(jxl.format.Alignment.RIGHT);
                }
            }
            
          cellStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
          cellStyle.setWrap(pBooleanWrap);
        }catch(Exception e){
            System.out.println(e);
        }      
        return cellStyle;
    }
    
    /**
     * �Ե�Ԫ���ִ�� <br>
     * <br>
     * @param pWritableSheet WritableSheet������ <br>
     * @param pRow �� <br>
     * @param pCell ��Ԫ�� <br>
     * @param pVlue ֵ <br>
     * @param pIntCellBackColor ��Ԫ�񱳾���ɫ <br>
     * @param pFontColor ������ɫ <br>
     * @param pFontSize �����С <br>
     * @param pBooleanBold �Ƿ���� <br>
     * @param pBorder �߽�ֵ <br>
     * @param pStrType �ַ��͡������� <br>
     */
    public void setCell(
            WritableSheet pWritableSheet,
            int pRow,
            int pCell,
            String pVlue,
            int pIntCellBackColor,
            int pFontColor,
            int pFontSize,
            boolean pBooleanBold,
            int pBorder,
            int pStrType) {
        try{
            if(pStrType == 1){
                // cell������ʱ������
                jxl.write.Number label = new jxl.write.Number(pCell,
                        pRow,
                        Integer.parseInt(pVlue),
                        this.getCellFormat(pIntCellBackColor,
                                pFontColor,
                                pFontSize,
                                pBooleanBold,
                                pBorder));
                
                pWritableSheet.addCell(label);
            }else{
                // cell����������������
                jxl.write.Label  label = new jxl.write.Label(pCell,
                        pRow,
                        pVlue,
                        this.getCellFormat(pIntCellBackColor,
                                pFontColor,
                                pFontSize,
                                pBooleanBold,
                                pBorder));
                pWritableSheet.addCell(label);
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    /**
     * ͼƬ���� <br>
     * <br>
     * @param pSheet �? <br>
     * @param pRow �� <br>
     * @param pCell ��Ԫ�� <br>
     * @param pRowAdd ���ӵ��� <br>
     * @param pCellAdd ���ӵĵ�Ԫ�� <br>
     * @param pImage <br>
     * @return void <br>
     */
    public void insertImages(WritableSheet pSheet,
            int pRow,
            int pCell,
            int pRowAdd,
            int pCellAdd,
            File pImage){
        WritableImage wbImage = new WritableImage(5,2,1,6,pImage);
        pSheet.addImage(wbImage);
    }
    
    /**
     * ����workbook���� <br>
     * <br>
     * @return void <br>
     */
    public void saveWorkBook(){
        try{
            // �������
            writableWorkbook.write();  
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    /**
     * �ر�workbook <br>
     * <br>
     * @return void <br>
     */
    public void closeWorkBook(){
        try{
            // �ر� writableWorkbook
            if(writableWorkbook!=null){
                writableWorkbook.close();
            }
            
            // �ر� workbook
            if(workbook!=null){
                workbook.close();
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    /**
     * ��Ԫ������<br>
     * <br>
     * @param pFontSize �����С <br>
     * @param pFontBold �Ƿ���� <br>
     * @param pBorder �Ƿ��б߿� <br>
     * @param pFontColor ������ɫ <br>
     * @param pCellBkColor ������ɫ <br>
     * @param pAlignment ���뷽ʽ <br>
     * @return writableCellFormat WritableCellFormat <br>
     * @throws Exception ���������ʱ�׳��쳣<br>
     */  
    public WritableCellFormat setCellStyle(int pFontSize,
            boolean pFontBold,
            boolean pBorder,
            int pFontColor,
            int pCellBkColor,
            String pAlignment) throws Exception{
        // ��ʼ font ����
        WritableFont font = null;
        // ��ʼ WritableCellFormat ����
        WritableCellFormat cellStyle = null;
        
        try{
            // ��ʼ��font
            font =  new WritableFont(WritableFont.createFont("����"),
                    pFontSize,
                    pFontBold == true ? WritableFont.BOLD : WritableFont.NO_BOLD,
                    false,
                    UnderlineStyle.NO_UNDERLINE,
                    this.setColor(pFontColor));
            cellStyle = new WritableCellFormat(font);
            // �Ƿ�Ҫ�߿�
            if(pBorder){
                cellStyle.setBorder(jxl.format.Border.ALL,
                        jxl.format.BorderLineStyle.THIN,
                        jxl.format.Colour.BLACK);
            }else{
                cellStyle.setBorder(jxl.format.Border.NONE,
                        jxl.format.BorderLineStyle.NONE,
                        jxl.format.Colour.BLACK);
            }
            
            // ���ñ���
            cellStyle.setBackground(this.setColor(pCellBkColor));
            // ���ж���
            if(pAlignment.equals("center") || pAlignment.equals("")){
                cellStyle.setAlignment(jxl.format.Alignment.CENTRE);
            }else{
                // �����
                if(pAlignment.equals("left")){
                    cellStyle.setAlignment(jxl.format.Alignment.LEFT);
                }else{
                    // �Ҷ���
                    cellStyle.setAlignment(jxl.format.Alignment.RIGHT); 
                }
            }
            
         cellStyle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
         cellStyle.setWrap(true);
        }catch(Exception e){
            System.out.println(e);
        }        
        return cellStyle;
    }    
    
    /**
     * ��ɫ���ձ� <br> 
     * <br>
     * @param pCellBackColor ��ɫ��Ӧ������ <br>
     * @return Colour <br>
     */
    public jxl.format.Colour setColor(int pCellBackColor){
        jxl.format.Colour color=null;
        // �жϴ������ɫ
        switch(pCellBackColor){
            case  1: color = jxl.format.Colour.AQUA ;         break;
            case  2: color = jxl.format.Colour.AUTOMATIC ;    break;
            case  3: color = jxl.format.Colour.BLACK ;        break;
            case  4: color = jxl.format.Colour.BLUE ;         break;
            case  5: color = jxl.format.Colour.BLUE_GREY ;    break;
            case  6: color = jxl.format.Colour.BLUE2 ;        break;
            case  7: color = jxl.format.Colour.BRIGHT_GREEN;  break;
            case  8: color = jxl.format.Colour.BROWN ;        break;
            case  9: color = jxl.format.Colour.CORAL ;        break;
            case 10: color = jxl.format.Colour.DARK_BLUE;     break;
            case 11: color = jxl.format.Colour.DARK_BLUE2 ;   break;
            case 12: color = jxl.format.Colour.DARK_GREEN ;   break;
            case 13: color = jxl.format.Colour.DARK_PURPLE ;  break;
            case 14: color = jxl.format.Colour.DARK_RED ;     break;
            case 15: color = jxl.format.Colour.DARK_RED2 ;    break;
            case 16: color = jxl.format.Colour.DARK_TEAL ;    break;
            case 17: color = jxl.format.Colour.DARK_YELLOW ;  break;
            case 18: color = jxl.format.Colour.DEFAULT_BACKGROUND ;  break;
            case 19: color = jxl.format.Colour.DEFAULT_BACKGROUND1 ; break;
            case 20: color = jxl.format.Colour.GOLD ;    break;
            case 21: color = jxl.format.Colour.GRAY_25 ; break;
            case 22: color = jxl.format.Colour.GRAY_50 ; break;
            case 23: color = jxl.format.Colour.GRAY_80 ; break;
            case 24: color = jxl.format.Colour.GREEN ;   break;
            case 25: color = jxl.format.Colour.GREY_25_PERCENT ; break;
            case 26: color = jxl.format.Colour.GREY_40_PERCENT ; break;
            case 27: color = jxl.format.Colour.GREY_50_PERCENT ; break;
            case 28: color = jxl.format.Colour.GREY_80_PERCENT ; break;
            case 29: color = jxl.format.Colour.ICE_BLUE ; break;
            case 30: color = jxl.format.Colour.INDIGO ;   break;
            case 31: color = jxl.format.Colour.IVORY ;    break;
            case 32: color = jxl.format.Colour.LAVENDER ;          break;
            case 33: color = jxl.format.Colour.LIGHT_BLUE ;        break;
            case 34: color = jxl.format.Colour.LIGHT_GREEN ;       break;
            case 35: color = jxl.format.Colour.LIGHT_ORANGE ;      break;
            case 36: color = jxl.format.Colour.LIGHT_TURQUOISE ;   break;
            case 37: color = jxl.format.Colour.LIGHT_TURQUOISE2 ;  break;
            case 38: color = jxl.format.Colour.LIME ;           break;
            case 39: color = jxl.format.Colour.OCEAN_BLUE ;     break;
            case 40: color = jxl.format.Colour.OLIVE_GREEN ;    break;
            case 41: color = jxl.format.Colour.ORANGE ;         break;
            case 42: color = jxl.format.Colour.PALE_BLUE ;      break;
            case 43: color = jxl.format.Colour.PALETTE_BLACK ;  break;
            case 44: color = jxl.format.Colour.PERIWINKLE ;     break;
            case 45: color = jxl.format.Colour.PINK ;   break;
            case 46: color = jxl.format.Colour.PINK2 ;  break;
            case 47: color = jxl.format.Colour.PLUM ;   break;
            case 48: color = jxl.format.Colour.PLUM2 ;  break;
            case 49: color = jxl.format.Colour.RED ;    break;
            case 50: color = jxl.format.Colour.ROSE ;   break;
            case 51: color = jxl.format.Colour.SEA_GREEN ; break;
            case 52: color = jxl.format.Colour.SKY_BLUE ;  break;
            case 53: color = jxl.format.Colour.TAN ;       break;
            case 54: color = jxl.format.Colour.TEAL ;      break;
            case 55: color = jxl.format.Colour.TEAL2 ;     break;
            case 56: color = jxl.format.Colour.TURQOISE2 ; break;
            case 57: color = jxl.format.Colour.TURQUOISE ; break;
            case 58: color = jxl.format.Colour.UNKNOWN ;   break;
            case 59: color = jxl.format.Colour.VERY_LIGHT_YELLOW ;  break;
            case 60: color = jxl.format.Colour.VIOLET ;   break;
            case 61: color = jxl.format.Colour.VIOLET2 ;  break;
            case 62: color = jxl.format.Colour.WHITE ;    break;
            case 63: color = jxl.format.Colour.YELLOW ;   break;
            case 64: color = jxl.format.Colour.YELLOW2 ;  break;
            default :color = jxl.format.Colour.WHITE ;    break;
        }
        return color;
    }



}