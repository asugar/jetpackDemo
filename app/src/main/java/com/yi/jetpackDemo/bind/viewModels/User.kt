package com.yi.jetpackDemo.bind.viewModels

import androidx.databinding.ObservableField

/**
 *  可观察字段
 *  ObservalbeField
 */
data class User(var firstName: ObservableField<String>, var lastName: ObservableField<String>)