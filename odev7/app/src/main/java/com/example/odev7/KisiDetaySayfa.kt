package com.example.odev7

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.odev7.entity.Kisiler
import com.example.odev7.viewmodel.KisiDetaySayfaViewModel
import com.example.odev7.viewmodel.KisiKayitSayfaViewModel
import com.example.odev7.viewmodelfactory.KisiDetaySayfaViewModelFactory
import com.example.odev7.viewmodelfactory.KisiKayitSayfaViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KisiDetaySayfa(gelenKisi:Kisiler) {
    val tfKisiAd = remember { mutableStateOf("") }
    val tfKisiTel = remember { mutableStateOf("") }
    val localFocusManager= LocalFocusManager.current

    val context= LocalContext.current
    val viewModel: KisiDetaySayfaViewModel = viewModel(
        factory = KisiDetaySayfaViewModelFactory(context.applicationContext as Application)
    )
    LaunchedEffect(key1=true) {
        tfKisiAd.value=gelenKisi.kisi_ad
        tfKisiTel.value=gelenKisi.kisi_tel

    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Kişi Detay") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red // Burada arka plan rengini değiştirebilirsiniz
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = tfKisiAd.value,
                    onValueChange = { tfKisiAd.value = it },
                    label = { Text("Kişi Adı") }
                )
                TextField(
                    value = tfKisiTel.value,
                    onValueChange = { tfKisiTel.value = it },
                    label = { Text("Kişi Telefonu") }
                )
                Button(onClick = {
                    val kisi_ad=tfKisiAd.value
                    val kisi_tel=tfKisiTel.value
                  viewModel.guncelle(gelenKisi.kisi_id,kisi_ad,kisi_tel)

                    localFocusManager.clearFocus()

                }, modifier= Modifier.size(250.dp,50.dp)) {
                    Text(text = "Guncelle")

                }
            }
        }
    )
}
