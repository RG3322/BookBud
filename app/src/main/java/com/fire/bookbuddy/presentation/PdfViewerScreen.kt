package com.fire.bookbuddy.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.rememberVerticalPdfReaderState
import java.net.URL

@Composable
fun  PdfViewerScreen(
    url:String
){

    var isDarkMode by remember { mutableStateOf(false) }
    val pdfState = rememberVerticalPdfReaderState(resource = ResourceType.Remote(url), isZoomEnable = true)
    com.rizzi.bouquet.VerticalPdfReader(state = pdfState, isDarkMode = isDarkMode)
    //vghsdvhfidfafna buivodvnrnvkahihvdbv
    // just want ti change the filel

}