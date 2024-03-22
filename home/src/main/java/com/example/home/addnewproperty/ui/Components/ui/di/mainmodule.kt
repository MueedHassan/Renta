package com.example.home.addnewproperty.ui.Components.ui.di
import com.example.home.addnewproperty.ui.Components.ui.repo.AddNewPropertyScreen1
import com.example.home.addnewproperty.ui.Components.ui.repo.AddNewPropertyScreen1Impl
import com.example.home.addnewproperty.ui.Components.ui.repo.FirebaseImageRepository
import com.example.home.addnewproperty.ui.Components.ui.repo.FirebaseImageRepositoryImpl
import com.example.home.addnewproperty.ui.Components.ui.repo.GetRecentID
import com.example.home.addnewproperty.ui.Components.ui.vm.AddNewPropertyViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import com.example.home.addnewproperty.ui.Components.ui.repo.GetRecentIDImpl
import com.example.home.addnewproperty.ui.Components.ui.repo.UpdateDoc
import com.example.home.addnewproperty.ui.Components.ui.repo.UpdateDocImpl

val mainModule = module {
    viewModelOf(::AddNewPropertyViewModel)
}
val myModule = module {
    singleOf(::AddNewPropertyScreen1Impl) bind AddNewPropertyScreen1::class
    singleOf(::GetRecentIDImpl) bind GetRecentID::class
    singleOf(::UpdateDocImpl) bind UpdateDoc::class
    singleOf(::FirebaseImageRepositoryImpl) bind FirebaseImageRepository::class
}