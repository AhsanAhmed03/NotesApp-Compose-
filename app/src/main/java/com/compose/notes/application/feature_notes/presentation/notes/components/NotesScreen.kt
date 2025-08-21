package com.compose.notes.application.feature_notes.presentation.notes.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.compose.notes.application.feature_notes.presentation.notes.NotesViewModel

@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {

}