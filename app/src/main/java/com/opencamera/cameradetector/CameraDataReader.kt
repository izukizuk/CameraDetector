package com.opencamera.cameradetector

import android.content.Context
import android.util.Log
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader


class CameraDataReader(val ctx: Context) {

    val TAG = "CameraDataReader"

    companion object {
        val cameraDataList = ArrayList<CameraData>()//Creating an empty arraylist
    }

    fun read2(){ 
        val inputStream : InputStream = ctx.assets.open("NPA_TD1.csv")
        val isr = InputStreamReader(inputStream)
        val fileReader = BufferedReader(isr)

        val list = fileReader.readLines()

        for ( l in list)
        {
            Log.d(TAG, l)
        }

    }

    fun read(): Int {
        val inputStream : InputStream = ctx.assets.open("NPA_TD1.csv")
        val isr = InputStreamReader(inputStream)
        val fileReader = BufferedReader(isr)
        //val csvParser = CSVParser(fileReader,
        //    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase())
        val csvParser = CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withFirstRecordAsHeader().withIgnoreHeaderCase())
        val csvRecords = csvParser.getRecords()
        for (csvRecord in csvRecords) {
            val cd = CameraData( csvRecord.get(0), // CityName
                        csvRecord.get("RegionName"),
                        csvRecord.get("Address"),
                        csvRecord.get("DeptNm"),
                        csvRecord.get("BranchNm"),
                        csvRecord.get("Longitude"),
                        csvRecord.get("Latitude"),
                        csvRecord.get("direct"),
                        csvRecord.get("limit")
            )
            Log.d(TAG, cd.toString())

            cameraDataList.add(cd)
        }

        return csvRecords.size
    }
}
