package com.example.odev7.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.odev7.viewmodel.AnasayfaViewModel
import com.example.odev7.viewmodel.KisiDetaySayfaViewModel

class KisiDetaySayfaViewModelFactory (var application: Application): ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return KisiDetaySayfaViewModel(application) as T
    }
}