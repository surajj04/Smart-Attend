package org.samrtattend.Controller;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.samrtattend.Model.Attendance;
import org.samrtattend.Model.Student;
import org.samrtattend.Repository.AttendanceRepo;
import org.samrtattend.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class ExcelController {

    @Autowired
    StudentRepo studentRepo;
    @Autowired
    AttendanceRepo attendanceRepo;

    @GetMapping("/downloadExcel/{_class}/{org}")
    public ResponseEntity<byte[]> exportAttendanceExcel(@PathVariable String _class, @PathVariable String org) throws IOException {
        List<Student> students = studentRepo.findByClassOrg(_class, org);
        List<Attendance> attendances = attendanceRepo.findByClassAndOrg(_class, org);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Student Attendance");

        // Setting column widths (in units of 1/256th of a character width)
        sheet.setColumnWidth(0, 4000); // Roll No
        sheet.setColumnWidth(1, 8000); // Name
        sheet.setColumnWidth(2, 4000); // Class
        sheet.setColumnWidth(3, 6000); // Attendance


        Row headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("Roll No");
        headRow.createCell(1).setCellValue("Name");
        headRow.createCell(2).setCellValue("Class");
        headRow.createCell(3).setCellValue("Attendance");

        int rowNum = 1;
        for (Student s : students) {
            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(rowNum);  // Adjusted to start from 1
            row.createCell(1).setCellValue(s.getName());
            row.createCell(2).setCellValue(s.get_class());
            row.createCell(3).setCellValue(Math.floor(calculateAttendance(_class, org, s.getId())) + " %");
            rowNum++;
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.close();

        byte[] excelByte = bos.toByteArray();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=StudentAttendance.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelByte);
    }

    private float calculateAttendance(String _class, String org, int id) {
        int attendanceCount = 0;
        List<Attendance> attendances = attendanceRepo.findByClassAndOrg(_class, org);

        for (Attendance attendance : attendances) {
            Integer[] attendArr = parseAttendanceString(attendance.getAttend());
            for (Integer studentId : attendArr) {
                if (studentId == id) {
                    attendanceCount++;
                }
            }
        }

        return ((float) attendanceCount / attendances.size() * 100);
    }

    private Integer[] parseAttendanceString(String str) {
        return str.chars()
                .mapToObj(c -> (char) c)
                .map(this::charToInteger)
                .toArray(Integer[]::new);
    }

    private int charToInteger(char c) {
        switch (c) {
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            default:
                return 0;
        }
    }
}
