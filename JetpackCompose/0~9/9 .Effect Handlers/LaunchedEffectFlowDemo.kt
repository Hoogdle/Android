import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.jetpackcompose.LaunchedEffectViewModel

@Composable
fun LaunchedEffectFlowDemo(
    viewModel: LaunchedEffectViewModel
){
    // be set key1 = true, run only once when this function started.
    LaunchedEffect(key1 = true) {
        viewModel.sharedFlow.collect{event ->
            when(event){
                is LaunchedEffectViewModel.ScreenEvents.ShowSnackbar ->{

                }
                is LaunchedEffectViewModel.ScreenEvents.Navigate -> {

                }
            }
        }
    }
}