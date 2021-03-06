package com.evaluation.function;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * <code>AboutExcel</code> 객체는 엑셀 파일을 읽고 쓰는데 사용한다..
 */
public class AboutExcel {

    /**
     * 엑셀 파일을 읽는다.
     * 
     * @param uploadFile 업로드 파일
     * @return 엑셀 파일을 행<열리스트>리스트로 변환
     */
    public static List<List<String>> readExcel(MultipartFile uploadFile) {
        List<List<String>> ret = new ArrayList<List<String>>();

        try {

            InputStream is = uploadFile.getInputStream();
            XSSFWorkbook workbook = new XSSFWorkbook(is); // 2007 이후 버전(xlsx파일)

            int rowindex = 0;
            int columnindex = 0;
            // 시트 수 (첫번째에만 존재하므로 0을 준다)
            // 만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
            XSSFSheet sheet = workbook.getSheetAt(0);
            // 행의 수
            int rows = sheet.getPhysicalNumberOfRows();
            // 열의 수 행 머릿글에서 열 숫자를 전체 행의 열의 수로 인지한다.
            int cells = sheet.getRow(0).getLastCellNum();
            for (rowindex = 0; rowindex < rows; rowindex++) {
                // 행을읽는다
                XSSFRow row = sheet.getRow(rowindex);
                if (row != null) {
                    List<String> tmpList = new ArrayList<String>();
                    // 셀의 수 getPhysicalNumberOfCells()은 개수카운팅에 문제가 있음... getLastCellNum으로!!
                    // int cells = row.getPhysicalNumberOfCells();
                    // int cells = row.getLastCellNum();
                    for (columnindex = 0; columnindex < cells; columnindex++) {
                        // 셀값을 읽는다
                        XSSFCell cell = row.getCell(columnindex);
                        String value = "";
                        // 셀이 빈값일경우를 위한 널체크
                        if (cell == null) {
                            value = null;
                            // 최초버전 continue로 뒀으나, 이것때문에 행렬 어그러짐 문제 발생.
                            // continue;
                        } else {
                            // 타입별로 내용 읽기
                            switch (cell.getCellType()) {
                            case XSSFCell.CELL_TYPE_FORMULA:
                                value = cell.getCellFormula();
                                break;
                            case XSSFCell.CELL_TYPE_NUMERIC:
                                // value = cell.getNumericCellValue() + "";
                                value = cell.getRawValue().trim().trim();
                                break;
                            case XSSFCell.CELL_TYPE_STRING:
                                value = cell.getStringCellValue().trim() + "";
                                break;
                            case XSSFCell.CELL_TYPE_BLANK:
                                // value = cell.getBooleanCellValue() + "";
                                value = null;
                                break;
                            case XSSFCell.CELL_TYPE_ERROR:
                                value = cell.getErrorCellValue() + "";
                                break;
                            }
                        }
                        tmpList.add(value);
                    }
                    ret.add(tmpList);
                }
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    // xlsx 쓰기 함수
    public static XSSFWorkbook writeExcel(List<List<String>> list) {
        // 워크북 생성
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 워크시트 생성
        XSSFSheet sheet = workbook.createSheet("data");
        // 행 생성
        XSSFRow row = sheet.createRow(0);
        // 쎌 생성
        XSSFCell cell;

        // 리스트의 size 만큼 row를 생성
        for (int rowIdx = 0; rowIdx < list.size(); rowIdx++) {

            List<String> body = list.get(rowIdx);
            // 행 생성
            row = sheet.createRow(rowIdx);

            for (int i = 0; i < body.size(); i++) {
                cell = row.createCell(i);
                cell.setCellValue(body.get(i));
            }
        }

        return workbook;
        // 입력된 내용 파일로 쓰기
        // excel 파일 저장
        // try {
        // File xlFile = new File("C:/test/" + name + ".xlsx");
        // FileOutputStream fileOut = new FileOutputStream(xlFile);
        // workbook.write(fileOut);
        // fileOut.close();
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }

}