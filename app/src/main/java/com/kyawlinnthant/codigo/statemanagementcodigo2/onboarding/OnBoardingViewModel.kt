package com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Allergy
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Diet
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Health
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Vitamin
import com.kyawlinnthant.codigo.statemanagementcodigo2.repo.JsonRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val repo: JsonRepo
) : ViewModel() {

    private val vmEvent = MutableSharedFlow<OnBoardingEvent>()
    val uiEvent get() = vmEvent.asSharedFlow()


    private val vmHealths = mutableStateListOf<Health>()
    val selectedHealths get() = vmHealths
    private val vmDiets = mutableStateListOf<Diet>()
    val selectedDiets get() = vmDiets
    private val vmAllergies = mutableStateListOf<Allergy>()
    val selectedAllergies get() = vmAllergies

    private val vmFinalForm = MutableStateFlow(FinalForm())
    val dailyExposure = vmFinalForm.map(FinalForm::dailyExposure).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = vmFinalForm.value.dailyExposure()
    )
    val smoke = vmFinalForm.map(FinalForm::smoke).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = vmFinalForm.value.smoke()
    )
    val alcohol = vmFinalForm.map(FinalForm::alcohol).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = vmFinalForm.value.alcohol()
    )

    private val vmInputForm = MutableStateFlow(InputForm())
    val healths = vmInputForm.map(InputForm::healths).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = vmInputForm.value.healths()
    )
    val diets = vmInputForm.map(InputForm::diets).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = vmInputForm.value.diets()
    )
    val allergies = vmInputForm.map(InputForm::allergies).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = vmInputForm.value.allergies()
    )

    fun getInputForms() {
        viewModelScope.launch {
            val healths = repo.getHealths()
            val diets = repo.getDiets()
            val allergies = repo.getAllergies()
            vmInputForm.update {
                it.copy(
                    healths = healths,
                    diets = diets,
                    allergies = allergies
                )
            }
        }
    }

    fun onAction(action: OnBoardingAction) {
        viewModelScope.launch {
            when (action) {
                is OnBoardingAction.AddAllergy -> vmAllergies.add(action.allergy)
                is OnBoardingAction.AddDiet -> vmDiets.add(action.diet)
                is OnBoardingAction.AddHealth -> vmHealths.add(action.health)
                is OnBoardingAction.RemoveDiet -> vmDiets.remove(action.diet)
                is OnBoardingAction.RemoveHealth -> vmHealths.remove(action.health)
                is OnBoardingAction.SetAlcohol -> vmFinalForm.update {
                    it.copy(
                        alcohol = action.alcohol
                    )
                }

                is OnBoardingAction.SetDailyExposure -> vmFinalForm.update {
                    it.copy(
                        dailyExposure = action.enabled
                    )
                }

                is OnBoardingAction.SetSmoke -> vmFinalForm.update {
                    it.copy(
                        smoke = action.enabled
                    )
                }

                is OnBoardingAction.Back -> onBack(action.screen)
                is OnBoardingAction.Next -> onNext(action.screen)
            }
        }
    }

    private fun onBack(screens: OnBoardingScreens) {
        viewModelScope.launch {
            when (screens) {
                OnBoardingScreens.Allergy -> vmEvent.emit(
                    OnBoardingEvent.OnNavigate(
                        OnBoardingScreens.Diet
                    )
                )

                OnBoardingScreens.Diet -> vmEvent.emit(OnBoardingEvent.OnNavigate(OnBoardingScreens.Health))
                OnBoardingScreens.Health -> vmEvent.emit(
                    OnBoardingEvent.OnNavigate(
                        OnBoardingScreens.Start
                    )
                )

                OnBoardingScreens.Start -> Any()
                OnBoardingScreens.Vitamin -> vmEvent.emit(
                    OnBoardingEvent.OnNavigate(
                        OnBoardingScreens.Allergy
                    )
                )
            }
        }
    }

    private fun onNext(screens: OnBoardingScreens) {
        viewModelScope.launch {
            when (screens) {
                OnBoardingScreens.Diet -> {
                    if (selectedDiets.isEmpty()) {
                        vmEvent.emit(OnBoardingEvent.ShowSnack)
                    } else {
                        vmEvent.emit(OnBoardingEvent.OnNavigate(OnBoardingScreens.Allergy))
                    }
                }

                OnBoardingScreens.Health -> {
                    if (selectedHealths.isEmpty()) {
                        vmEvent.emit(OnBoardingEvent.ShowSnack)
                    } else {
                        vmEvent.emit(OnBoardingEvent.OnNavigate(OnBoardingScreens.Diet))
                    }
                }

                OnBoardingScreens.Vitamin -> {
                    summit()
                }

                OnBoardingScreens.Allergy -> vmEvent.emit(OnBoardingEvent.OnNavigate(OnBoardingScreens.Vitamin))
                OnBoardingScreens.Start -> vmEvent.emit(OnBoardingEvent.OnNavigate(OnBoardingScreens.Health))
            }
        }
    }

    private fun summit() {
        viewModelScope.launch {
            val vitamin = mapToVitamin()
            print(vitamin)
        }
    }

    private fun mapToVitamin(): Vitamin {
        return Vitamin(
            healths = vmHealths.map {
                it.toResult(priority = vmHealths.indexOf(it).plus(1))
            },
            diets = vmDiets,
            isDailyExposure = vmFinalForm.value.dailyExposure,
            isSmoke = vmFinalForm.value.smoke,
            alcohol = vmFinalForm.value.alcohol.value,
            allergies = vmAllergies
        )
    }

    private fun print(vitamin: Vitamin) {
        viewModelScope.launch {
            val result = repo.getResult(vitamin)

            Log.e("summary", result.toString())
            print(result)
        }
    }

}