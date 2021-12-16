package com.b2i.sms_sender.application.bootstrap

import com.b2i.sms_sender.domain.contact.entity.Contact
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import java.io.File
import java.io.InputStream
import java.nio.charset.StandardCharsets


object ExcelReader {

    @Autowired
    var resourceLoader: ResourceLoader? = null


    fun reader() : MutableList<Contact> {

        var FILE_NAME : String  = "contacts.xlsx"

        val resource: Resource = ClassPathResource( FILE_NAME )
        val inputStream: InputStream = resource.inputStream

        val contactList: MutableList<Contact> = ArrayList<Contact>()
        val workbook : Workbook = XSSFWorkbook( inputStream )
        val worksheet: Sheet = workbook.getSheetAt(0)

        for (i in 1 until worksheet.physicalNumberOfRows) {
            val contact = Contact()
            val row: XSSFRow = worksheet.getRow(i) as XSSFRow
            contact.firstName = row.getCell(0).stringCellValue
            contact.lastName = row.getCell(1).stringCellValue
            contact.phoneNumber = row.getCell(2).stringCellValue

            contactList.add( contact )
        }

        return contactList
    }

}