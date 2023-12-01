package com.oguzhanaslann.zipfiles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.oguzhanaslann.zipfiles.ui.theme.ZipFilesTheme
import com.oguzhanaslann.zipfiles.zip.ZipFacade
import java.io.File
import java.util.zip.ZipFile

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val file1 = File(filesDir, "file1.txt")
        if (!file1.exists()) {
            file1.createNewFile()
            file1.writeText("Hello World")
        }
        val file2 = File(filesDir, "file2.txt")
        if (!file2.exists()) {
            file2.createNewFile()
        }

        val zipFile = File(filesDir, "zip.zip")
        if (!zipFile.exists()) {
            zipFile.createNewFile()
        }
        setContent {
            ZipFilesTheme { // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Button(modifier = Modifier.align(Alignment.CenterStart), onClick = {
                            ZipFacade.zip(
                                listOf(file1.absolutePath, file2.absolutePath),
                                zipFile.absolutePath
                            )
                        }) {
                            Text(text = "Zip")
                        }


                        Button(modifier = Modifier.align(Alignment.CenterEnd), onClick = {
                            val unzipDir = File(filesDir, "unzip")
                            if (!unzipDir.exists()) {
                                unzipDir.mkdir()
                            }
                            val zipFile = File(filesDir, "zip.zip")
                           ZipFacade.unZip(
                               zipFilePath = zipFile.absolutePath,
                               unzipDirectoryPath = unzipDir.absolutePath
                           )
                        }) {
                            Text(text = "Unzip")
                        }

                    }
                }
            }
        }
    }
}


